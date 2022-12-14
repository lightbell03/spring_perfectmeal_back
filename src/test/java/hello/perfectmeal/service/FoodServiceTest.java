package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.Gender;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.food.dto.FoodDTO;
import hello.perfectmeal.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles({"test"})
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FoodServiceTest {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FoodService foodService;
    Account account;
    Set<String> set = new HashSet<>();
    String date;

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

        set.add("감자");
//        set.add("김치찌개");
//        set.add("햄버거");
        date = "2022-11-23";
    }

    @Test
    @DisplayName("save breakfast")
    public void saveBreakfast() throws Exception {
        FoodDTO breakfastFoodDTO = new FoodDTO();
        breakfastFoodDTO.setFoodSet(set);

        Breakfast breakfast = foodService.saveBreakfast(account, breakfastFoodDTO, date);

        assertThat(breakfast.getFoodSet()).isEqualTo(set);
    }

    @Test
    @DisplayName("get Today Breakfast food")
    public void getTodayBreakfast() throws Exception {
        FoodDTO breakfastFoodDTO = new FoodDTO();
        breakfastFoodDTO.setFoodSet(set);

        Breakfast breakfast = foodService.saveBreakfast(account, breakfastFoodDTO, date);
        String date = "2022-11-22";
        Breakfast findBreakfast = foodService.getBreakfast(account, date);

        assertThat(breakfast.getId()).isEqualTo(findBreakfast.getId());
    }

    @Test
    @DisplayName("save lunch")
    public void saveLunch() {
        FoodDTO lunchFoodDTO = new FoodDTO();
        lunchFoodDTO.setFoodSet(set);

        Lunch lunch = foodService.saveLunch(account, lunchFoodDTO, date);

        assertThat(lunch.getFoodSet()).isEqualTo(set);
    }

    @Test
    @DisplayName("get Today lunch")
    public void getTodayLunch(){
        FoodDTO lunchFoodDTO = new FoodDTO();
        lunchFoodDTO.setFoodSet(set);

        Lunch lunch = foodService.saveLunch(account, lunchFoodDTO, date);
        Lunch findLunch = foodService.getLunch(account, date);

        assertThat(lunch.getId()).isEqualTo(findLunch.getId());
    }

    @Test
    @DisplayName("save dinner")
    public void saveDinner() {
        FoodDTO dinnerFoodDTO = new FoodDTO();
        dinnerFoodDTO.setFoodSet(set);

        Dinner dinner = foodService.saveDinner(account, dinnerFoodDTO, date);

        assertThat(dinner.getFoodSet()).isEqualTo(set);
    }

    @Test
    @DisplayName("get Today dinner")
    public void getTodayDinner() {
        FoodDTO dinnerFoodDTO = new FoodDTO();
        dinnerFoodDTO.setFoodSet(set);

        Dinner dinner = foodService.saveDinner(account, dinnerFoodDTO, date);
        Dinner findDinner = foodService.getDinner(account, date);

        assertThat(dinner.getId()).isEqualTo(findDinner.getId());
    }
}