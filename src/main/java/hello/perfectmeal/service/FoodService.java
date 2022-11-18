package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.food.dto.FoodDTO;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import hello.perfectmeal.repository.food.BreakfastRepository;
import hello.perfectmeal.repository.food.DinnerRepository;
import hello.perfectmeal.repository.food.LunchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequiredArgsConstructor
@Transactional
public class FoodService {

    private final BreakfastRepository breakfastRepository;
    private final LunchRepository lunchRepository;
    private final DinnerRepository dinnerRepository;
    private final NutrientService nutrientService;


    public Breakfast getTodayBreakfast(Account account) {
        return breakfastRepository.findByAccountAndDateBetween(account, getStartTime(), getEndTime())
                .orElse(null);
    }

    public Lunch getTodayLunch(Account account) {
        return lunchRepository.findByAccountAndDateBetween(account, getStartTime(), getEndTime())
                .orElse(null);
    }

    public Dinner getTodayDinner(Account account) {
        return dinnerRepository.findByAccountAndDateBetween(account, getStartTime(), getEndTime())
                .orElse(null);
    }

    private LocalDateTime getStartTime(){
        return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0, 0, 0));
    }
    private LocalDateTime getEndTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
    }

    @Transactional
    public Breakfast saveBreakfastFood(Account account, FoodDTO foodDTO) throws Exception {
        Breakfast breakfast = Breakfast.builder()
                .foodSet(foodDTO.getFoodSet())
                .date(LocalDateTime.now())
                .account(account)
                .build();
        Breakfast saveBreakfast = breakfastRepository.save(breakfast);

        BreakfastNutrient breakfastNutrient = nutrientService.saveBreakfastNutrient(account, saveBreakfast);

        return saveBreakfast;
    }

    @Transactional
    public Lunch saveLunch(Account account, FoodDTO foodDTO) {

        Lunch lunch = Lunch.builder()
                .foodSet(foodDTO.getFoodSet())
                .date(LocalDateTime.now())
                .account(account)
                .build();

        return lunchRepository.save(lunch);
    }

    @Transactional
    public Dinner saveDinner(Account account, FoodDTO foodDTO){
        Dinner dinner = Dinner.builder()
                .foodSet(foodDTO.getFoodSet())
                .date(LocalDateTime.now())
                .account(account)
                .build();

        return dinnerRepository.save(dinner);
    }
}