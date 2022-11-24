package hello.perfectmeal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.perfectmeal.config.security.provider.JwtTokenProvider;
import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.Gender;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.food.dto.FoodDTO;
import hello.perfectmeal.domain.food.dto.PhotoDTO;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import hello.perfectmeal.domain.nutrient.DinnerNutrient;
import hello.perfectmeal.domain.nutrient.LunchNutrient;
import hello.perfectmeal.domain.nutrient.Nutrient;
import hello.perfectmeal.repository.AccountRepository;
import hello.perfectmeal.service.AccountService;
import hello.perfectmeal.service.FoodService;
import hello.perfectmeal.service.NutrientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
    NutrientService nutrientService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    ObjectMapper objectMapper;

    Account account;
    String accessToken;
    String refreshToken;
    Set<String> foodSet;
    String date;

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
        date = "2022-11-23";
    }

    @Test
    @DisplayName("save Breakfast")
    public void saveBreakfast() throws Exception {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setFoodSet(foodSet);

        mockMvc.perform(post("/api/foods")
                        .header("Authorization", "Bearer:" + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("type", "breakfast")
                        .content(objectMapper.writeValueAsString(foodDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("foodSet").exists())
        ;
    }

    @Test
    @DisplayName("save Lunch")
    public void saveLunch() throws Exception {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setFoodSet(foodSet);

        mockMvc.perform(post("/api/foods")
                        .header("Authorization", "Bearer:" + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("type", "lunch")
                        .content(objectMapper.writeValueAsString(foodDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("foodSet").exists())
        ;
    }

    @Test
    @DisplayName("save dinner")
    public void saveDinner() throws Exception {
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setFoodSet(foodSet);

        mockMvc.perform(post("/api/foods")
                        .header("Authorization", "Bearer:" + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("type", "dinner")
                        .content(objectMapper.writeValueAsString(foodDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("foodSet").exists())
        ;
    }

    @Test
    @DisplayName("get Today food")
    public void getTodayFood() throws Exception {
        FoodDTO breakfastFoodDTO = new FoodDTO();
        breakfastFoodDTO.setFoodSet(foodSet);

        foodService.saveBreakfast(account, breakfastFoodDTO, date);

        FoodDTO lunchFoodDTO = new FoodDTO();
        lunchFoodDTO.setFoodSet(foodSet);
        foodService.saveLunch(account, lunchFoodDTO, date);

        FoodDTO dinnerFoodDTO = new FoodDTO();
        dinnerFoodDTO.setFoodSet(foodSet);
        foodService.saveDinner(account, dinnerFoodDTO, date);

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
        FoodDTO breakfastFoodDTO = new FoodDTO();
        breakfastFoodDTO.setFoodSet(foodSet);
        foodService.saveBreakfast(account, breakfastFoodDTO, date);

        FoodDTO dinnerFoodDTO = new FoodDTO();
        dinnerFoodDTO.setFoodSet(foodSet);
        foodService.saveDinner(account, dinnerFoodDTO, date);

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

    @Test
    public void threadTest() throws IOException {
        File file = new File(System.getProperty("user.dir") + "/testimage/image.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        String base64String = Base64.getEncoder().encodeToString(bytes);

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setImgsource("," + base64String);

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                try {
                    mockMvc.perform(post("/api/photo")
                                    .header("Authorization", "Bearer:" + accessToken)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(objectMapper.writeValueAsString(photoDTO)))
                            .andExpect(status().isOk());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                try {
                    mockMvc.perform(post("/api/photo")
                                    .header("Authorization", "Bearer:" + accessToken)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(objectMapper.writeValueAsString(photoDTO)))
                            .andExpect(status().isOk());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };

        runnable1.run();
        runnable2.run();
    }

    @Test
    public void test() throws Exception {
        Set<String> foodSet = new HashSet<>();
        foodSet.add("감자");

        FoodDTO foodDTO = FoodDTO.builder()
                .foodSet(foodSet)
                .build();

        mockMvc.perform(post("/api/foods")
                        .param("type", "breakfast")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer:" + accessToken)
                        .content(objectMapper.writeValueAsString(foodDTO)))
                .andExpect(status().isOk());
    }
}