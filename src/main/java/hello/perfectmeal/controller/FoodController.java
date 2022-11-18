package hello.perfectmeal.controller;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.food.dto.FoodDTO;
import hello.perfectmeal.domain.food.dto.FoodTodayDTO;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import hello.perfectmeal.domain.nutrient.DinnerNutrient;
import hello.perfectmeal.domain.nutrient.LunchNutrient;
import hello.perfectmeal.service.FoodService;
import hello.perfectmeal.service.NutrientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final NutrientService nutrientService;

    @GetMapping("/api/foods")
    public ResponseEntity getFood() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        Breakfast breakfast = foodService.getTodayBreakfast(account);
        BreakfastNutrient breakfastNutrient = nutrientService.getBreakfastNutrient(account, breakfast);
        Lunch lunch = foodService.getTodayLunch(account);
        LunchNutrient lunchNutrient = nutrientService.getLunchNutrient(account, lunch);
        Dinner dinner = foodService.getTodayDinner(account);
        DinnerNutrient dinnerNutrient = nutrientService.getDinnerNutrient(account, dinner);

        FoodTodayDTO foodTodayDTO = FoodTodayDTO.of()
                .food(breakfast, lunch, dinner)
                .nutrient(breakfastNutrient, lunchNutrient, dinnerNutrient)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(foodTodayDTO);
    }

    @PostMapping("/api/foods")
    public ResponseEntity saveBreakfast(
            @RequestParam String type,
            @RequestBody FoodDTO foodDto
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        if(type.equals("breakfast")) {
            try {
                Breakfast breakfast = foodService.saveBreakfastFood(account, foodDto);

                return ResponseEntity.ok().body(FoodDTO.BreakfastToFoodDTOConvertor(breakfast));
            } catch (Exception e){
                return ResponseEntity.internalServerError().build();
            }
        } else if(type.equals("lunch")){
            Lunch lunch = foodService.saveLunch(account, foodDto);

            return ResponseEntity.ok().body(FoodDTO.LunchToFoodDTOConvertor(lunch));
        } else if(type.equals("dinner")){
            Dinner dinner = foodService.saveDinner(account, foodDto);

            return ResponseEntity.ok().body(FoodDTO.DinnerToFoodDTOConvertor(dinner));
        }

        return ResponseEntity.badRequest().build();
    }
}
