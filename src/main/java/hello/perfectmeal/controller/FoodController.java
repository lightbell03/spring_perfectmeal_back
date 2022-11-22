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
import hello.perfectmeal.domain.nutrient.Nutrient;
import hello.perfectmeal.service.FoodService;
import hello.perfectmeal.service.NutrientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class FoodController {

    private final FoodService foodService;
    private final NutrientService nutrientService;

    @GetMapping("/api/foods")
    public ResponseEntity getFood(
            @RequestParam String date
    ) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        Breakfast breakfast = foodService.getBreakfast(account, date);
        Lunch lunch = foodService.getLunch(account, date);
        Dinner dinner = foodService.getDinner(account, date);

        Nutrient totalNutrient = nutrientService.getTotalNutrient(account, date);
        Nutrient underNutrient = nutrientService.getUnderNutrient(account, totalNutrient);

        FoodTodayDTO foodTodayDTO = FoodTodayDTO.of(breakfast, lunch, dinner, totalNutrient, underNutrient);

        return ResponseEntity.status(HttpStatus.OK).body(foodTodayDTO);
    }

    @PostMapping("/api/foods")
    public ResponseEntity saveFood(
            @RequestParam String type,
            @RequestParam String date,
            @RequestBody FoodDTO foodDTO
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        try {
            if (type.equals("breakfast")) {
                Breakfast breakfast = foodService.saveBreakfast(account, foodDTO, date);
                BreakfastNutrient breakfastNutrient = nutrientService.saveBreakfastNutrient(account, breakfast);
                breakfast.setBreakfastNutrient(breakfastNutrient);

                Nutrient totalNutrient = nutrientService.getTotalNutrient(account, date);
                Nutrient underNutrient = nutrientService.getUnderNutrient(account, totalNutrient);

                return ResponseEntity.ok().body(FoodDTO.BreakfastToFoodDTOConvertor(breakfast, totalNutrient, underNutrient));
            } else if (type.equals("lunch")) {
                Lunch lunch = foodService.saveLunch(account, foodDTO, date);
                LunchNutrient lunchNutrient = nutrientService.saveLunchNutrient(account, lunch);
                lunch.setLunchNutrient(lunchNutrient);

                Nutrient totalNutrient = nutrientService.getTotalNutrient(account, date);
                Nutrient underNutrient = nutrientService.getUnderNutrient(account, totalNutrient);

                return ResponseEntity.ok().body(FoodDTO.LunchToFoodDTOConvertor(lunch, totalNutrient, underNutrient));
            } else if (type.equals("dinner")) {
                Dinner dinner = foodService.saveDinner(account, foodDTO, date);
                DinnerNutrient dinnerNutrient = nutrientService.saveDinnerNutrient(account, dinner);
                dinner.setDinnerNutrient(dinnerNutrient);

                Nutrient totalNutrient = nutrientService.getTotalNutrient(account, date);
                Nutrient underNutrient = nutrientService.getUnderNutrient(account, totalNutrient);

                return ResponseEntity.ok().body(FoodDTO.DinnerToFoodDTOConvertor(dinner, totalNutrient, underNutrient));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
