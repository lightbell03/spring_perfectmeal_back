package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.Gender;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.food.dto.BreakfastDTO;
import hello.perfectmeal.domain.food.dto.DinnerDTO;
import hello.perfectmeal.domain.food.dto.LunchDTO;
import hello.perfectmeal.repository.AccountRepository;
import hello.perfectmeal.repository.BreakfastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles({"test"})
@SpringBootTest
class FoodServiceTest {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FoodService foodService;
    @Autowired
    BreakfastRepository breakfastRepository;
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
    @DisplayName("save breakfast")
    public void saveBreakfast() {
        Set<String> set = new HashSet<>();
        set.add("chicken");
        set.add("rice");
        set.add("pizza");

        BreakfastDTO breakfastDTO = new BreakfastDTO();
        breakfastDTO.setBreakfast(set);

        Breakfast breakfast = foodService.saveBreakfastFood(account, breakfastDTO);

        assertThat(breakfast.getFoodSet()).isEqualTo(set);
    }

    @Test
    @DisplayName("get Today Breakfast food")
    public void getTodayBreakfast() {
        Set<String> set = new HashSet<>();
        set.add("chicken");
        set.add("rice");
        set.add("pizza");

        BreakfastDTO breakfastDTO = new BreakfastDTO();
        breakfastDTO.setBreakfast(set);

        Breakfast breakfast = foodService.saveBreakfastFood(account, breakfastDTO);

        Breakfast findBreakfast = foodService.getTodayBreakfast(account);

        assertThat(breakfast.getId()).isEqualTo(findBreakfast.getId());
    }

    @Test
    @DisplayName("save lunch")
    public void saveLunch() {
        Set<String> set = new HashSet<>();
        set.add("chicken");
        set.add("rice");
        set.add("pizza");

        LunchDTO lunchDTO = new LunchDTO();
        lunchDTO.setLunch(set);

        Lunch lunch = foodService.saveLunch(account, lunchDTO);

        assertThat(lunch.getFoodSet()).isEqualTo(set);
    }

    @Test
    @DisplayName("get Today lunch")
    public void getTodayLunch(){
        Set<String> set = new HashSet<>();
        set.add("chicken");
        set.add("rice");
        set.add("pizza");

        LunchDTO lunchDTO = new LunchDTO();
        lunchDTO.setLunch(set);

        Lunch lunch = foodService.saveLunch(account, lunchDTO);
        Lunch findLunch = foodService.getTodayLunch(account);

        assertThat(lunch.getId()).isEqualTo(findLunch.getId());
    }

    @Test
    @DisplayName("save dinner")
    public void saveDinner() {
        Set<String> set = new HashSet<>();
        set.add("chicken");
        set.add("rice");
        set.add("pizza");

        DinnerDTO dinnerDTO = new DinnerDTO();
        dinnerDTO.setDinner(set);

        Dinner dinner = foodService.saveDinner(account, dinnerDTO);

        assertThat(dinner.getFoodSet()).isEqualTo(set);
    }

    @Test
    @DisplayName("get Today dinner")
    public void getTodayDinner() {
        Set<String> set = new HashSet<>();
        set.add("chicken");
        set.add("rice");
        set.add("pizza");

        DinnerDTO dinnerDTO = new DinnerDTO();
        dinnerDTO.setDinner(set);

        Dinner dinner = foodService.saveDinner(account, dinnerDTO);
        Dinner findDinner = foodService.getTodayDinner(account);

        assertThat(dinner.getId()).isEqualTo(findDinner.getId());
    }
}