package hello.perfectmeal.controller;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.food.dto.BreakfastDTO;
import hello.perfectmeal.domain.food.dto.DinnerDTO;
import hello.perfectmeal.domain.food.dto.FoodTodayDTO;
import hello.perfectmeal.domain.food.dto.LunchDTO;
import hello.perfectmeal.service.FoodService;
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

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/api/foods")
    public ResponseEntity getFood() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        Breakfast breakfast = foodService.getTodayBreakfast(account);
        Lunch lunch = foodService.getTodayLunch(account);
        Dinner dinner = foodService.getTodayDinner(account);

        FoodTodayDTO foodTodayDTO = FoodTodayDTO.of(BreakfastDTO.BreakfastToBreakfastDTOConvertor(breakfast), LunchDTO.LunchToLunchDTOConvertor(lunch), DinnerDTO.DinnerToDinnerDTOConvertor(dinner));

        return ResponseEntity.status(HttpStatus.OK).body(foodTodayDTO);
    }

    @PostMapping("/api/foods/breakfast")
    public ResponseEntity saveBreakfast(
            @RequestBody BreakfastDTO breakfastDTO
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();
        Breakfast breakfast = foodService.saveBreakfastFood(account, breakfastDTO);

        return ResponseEntity.status(HttpStatus.OK).body(BreakfastDTO.BreakfastToBreakfastDTOConvertor(breakfast));
    }

    @PostMapping("/api/foods/lunch")
    public ResponseEntity saveLunch(
            @RequestBody LunchDTO lunchDTO
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        Lunch lunch = foodService.saveLunch(account, lunchDTO);

        return ResponseEntity.status(HttpStatus.OK).body(LunchDTO.LunchToLunchDTOConvertor(lunch));
    }

    @PostMapping("/api/foods/dinner")
    public ResponseEntity saveDinner(
            @RequestBody DinnerDTO dinnerDTO
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();

        Dinner dinner = foodService.saveDinner(account, dinnerDTO);

        return ResponseEntity.status(HttpStatus.OK).body(DinnerDTO.DinnerToDinnerDTOConvertor(dinner));
    }
}
