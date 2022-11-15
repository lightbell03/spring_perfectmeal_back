package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.Gender;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.dto.FoodDTO;
import hello.perfectmeal.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        foodSet.add("김치찌개");

        FoodDTO foodDTO = FoodDTO.builder()
                .foodSet(foodSet)
                .build();

        Breakfast breakfast = foodService.saveBreakfastFood(account, foodDTO);
        nutrientService.saveBreakfastNutrient(account, breakfast);
    }

}