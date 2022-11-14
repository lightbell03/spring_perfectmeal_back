package hello.perfectmeal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.perfectmeal.config.security.provider.JwtTokenProvider;
import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.Gender;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.food.dto.BreakfastDTO;
import hello.perfectmeal.domain.food.dto.DinnerDTO;
import hello.perfectmeal.domain.food.dto.LunchDTO;
import hello.perfectmeal.repository.AccountRepository;
import hello.perfectmeal.service.AccountService;
import hello.perfectmeal.service.FoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class FoodControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FoodService foodService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    ObjectMapper objectMapper;

    Account account;
    String accessToken;
    String refreshToken;
    Set<String> foodSet;

    @BeforeEach
    public void beforeEach() {
        String email = "email@email.com";
        accountService.signup(AccountSignupDTO.builder()
                .email(email)
                .password("pass")
                .name("name")
                .age(20)
                .gender(Gender.MAN)
                .build());
        account = accountRepository.findByEmail(email).get();

        accessToken = jwtTokenProvider.createAccessToken(email);
        refreshToken = jwtTokenProvider.createRefreshToken(email);

        foodSet = new HashSet<>();
        foodSet.add("pizza");
        foodSet.add("rice");
        foodSet.add("chicken");
    }

    @Test
    @DisplayName("save Breakfast")
    public void saveBreakfast() throws Exception {
        BreakfastDTO breakfastDTO = new BreakfastDTO();
        breakfastDTO.setBreakfast(foodSet);

        mockMvc.perform(post("/api/foods/breakfast")
                        .header("Authorization", "Bearer:" + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(breakfastDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("breakfast").exists())
                ;
    }

    @Test
    @DisplayName("save Lunch")
    public void saveLunch() throws Exception {
        LunchDTO lunchDTO = new LunchDTO();
        lunchDTO.setLunch(foodSet);

        mockMvc.perform(post("/api/foods/lunch")
                        .header("Authorization", "Bearer:" + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lunchDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("lunch").exists())
        ;
    }

    @Test
    @DisplayName("save dinner")
    public void saveDinner() throws Exception {
        DinnerDTO dinnerDTO = new DinnerDTO();
        dinnerDTO.setDinner(foodSet);

        mockMvc.perform(post("/api/foods/dinner")
                        .header("Authorization", "Bearer:" + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dinnerDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("dinner").exists())
        ;
    }

    @Test
    @DisplayName("get Today food")
    public void getTodayFood() throws Exception {
        BreakfastDTO breakfastDTO = new BreakfastDTO();
        breakfastDTO.setBreakfast(foodSet);
        foodService.saveBreakfastFood(account, breakfastDTO);

        LunchDTO lunchDTO = new LunchDTO();
        lunchDTO.setLunch(foodSet);
        foodService.saveLunch(account, lunchDTO);

        DinnerDTO dinnerDTO = new DinnerDTO();
        dinnerDTO.setDinner(foodSet);
        foodService.saveDinner(account, dinnerDTO);

        mockMvc.perform(get("/api/foods")
                .header("Authorization", "Bearer:" + accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("breakfast").exists())
                .andExpect(jsonPath("lunch").exists())
                .andExpect(jsonPath("dinner").exists())
                ;
    }

    @Test
    @DisplayName("get Today Food with lunch = null")
    public void getTodayFoodWithLunchIsNull() throws Exception {
        BreakfastDTO breakfastDTO = new BreakfastDTO();
        breakfastDTO.setBreakfast(foodSet);
        foodService.saveBreakfastFood(account, breakfastDTO);

        DinnerDTO dinnerDTO = new DinnerDTO();
        dinnerDTO.setDinner(foodSet);
        foodService.saveDinner(account, dinnerDTO);

        mockMvc.perform(get("/api/foods")
                        .header("Authorization", "Bearer:" + accessToken)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("breakfast").exists())
                .andExpect(jsonPath("lunch").doesNotExist())
                .andExpect(jsonPath("dinner").exists())
        ;
    }
}