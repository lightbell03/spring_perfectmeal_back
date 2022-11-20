package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.Gender;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.food.dto.FoodDTO;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import hello.perfectmeal.domain.nutrient.DinnerNutrient;
import hello.perfectmeal.domain.nutrient.LunchNutrient;
import hello.perfectmeal.domain.nutrient.Nutrient;
import hello.perfectmeal.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class NutrientServiceTest {

    @Autowired
    NutrientService nutrientService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FoodService foodService;
    @Autowired
    AccountService accountService;

    Account account;

    @BeforeEach
    public void beforeEach() {
        String email = "email@email.com";

        AccountSignupDTO accountSignupDTO = AccountSignupDTO.builder()
                .email(email)
                .password("pass")
                .age(20)
                .name("name")
                .gender(Gender.MAN)
                .build();

        accountService.signup(accountSignupDTO);
        account = accountRepository.findByEmail(email).get();
    }


    @Test
    @DisplayName("save breakfast nutrient")
    public void saveBreakfastNutrient() throws Exception {
        Set<String> foodSet = new HashSet<>();
        foodSet.add("감자");

        FoodDTO foodDTO = FoodDTO.builder()
                .foodSet(foodSet)
                .build();

        Breakfast breakfast = foodService.saveBreakfastFood(account, foodDTO);
        BreakfastNutrient breakfastNutrient = nutrientService.saveBreakfastNutrient(account, breakfast);

        assertThat(breakfastNutrient.getBreakfast().getId()).isEqualTo(breakfast.getId());
    }

    @Test
    @DisplayName("get total today nutrient")
    public void getTodayTotalNutrient() throws Exception {
        Set<String> foodSet = new HashSet<>();
        foodSet.add("감자");

        FoodDTO foodDTO = FoodDTO.builder()
                .foodSet(foodSet)
                .build();

        Breakfast breakfast = foodService.saveBreakfastFood(account, foodDTO);
        BreakfastNutrient breakfastNutrient = nutrientService.saveBreakfastNutrient(account, breakfast);
        breakfast.setBreakfastNutrient(breakfastNutrient);

        Lunch lunch = foodService.saveLunch(account, foodDTO);
        LunchNutrient lunchNutrient = nutrientService.saveLunchNutrient(account, lunch);
        lunch.setLunchNutrient(lunchNutrient);

        Dinner dinner = foodService.saveDinner(account, foodDTO);
        DinnerNutrient dinnerNutrient = nutrientService.saveDinnerNutrient(account, dinner);
        dinner.setDinnerNutrient(dinnerNutrient);

        Nutrient nutrient = nutrientService.getTodayTotalNutrient(account);

        double sum = breakfastNutrient.getNutrient().getFood_Weight() +
                lunchNutrient.getNutrient().getFood_Weight() +
                dinnerNutrient.getNutrient().getFood_Weight();

        assertThat(nutrient.getFood_Weight()).isEqualTo(sum);
    }
}