package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.food.dto.BreakfastDTO;
import hello.perfectmeal.domain.food.dto.DinnerDTO;
import hello.perfectmeal.domain.food.dto.LunchDTO;
import hello.perfectmeal.repository.BreakfastRepository;
import hello.perfectmeal.repository.DinnerRepository;
import hello.perfectmeal.repository.LunchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final BreakfastRepository breakfastRepository;
    private final LunchRepository lunchRepository;
    private final DinnerRepository dinnerRepository;


    public Breakfast getTodayBreakfast(Account account) {
        return breakfastRepository.findByAccountAndDateBetween(account, getStartTime(), getEndTime()).orElse(null);
    }

    public Lunch getTodayLunch(Account account) {
        return lunchRepository.findByAccountAndDateBetween(account, getStartTime(), getEndTime()).orElse(null);
    }

    public Dinner getTodayDinner(Account account) {
        return dinnerRepository.findByAccountAndDateBetween(account, getStartTime(), getEndTime()).orElse(null);
    }

    private LocalDateTime getStartTime(){
        return LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0, 0, 0));
    }
    private LocalDateTime getEndTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
    }

    @Transactional
    public Breakfast saveBreakfastFood(Account account, BreakfastDTO breakfastDTO) {
        Breakfast breakfast = Breakfast.builder()
                .foodSet(breakfastDTO.getBreakfast())
                .date(LocalDateTime.now())
                .account(account)
                .build();

        return breakfastRepository.save(breakfast);
    }

    @Transactional
    public Lunch saveLunch(Account account, LunchDTO lunchDTO) {

        Lunch lunch = Lunch.builder()
                .foodSet(lunchDTO.getLunch())
                .date(LocalDateTime.now())
                .account(account)
                .build();

        return lunchRepository.save(lunch);
    }

    @Transactional
    public Dinner saveDinner(Account account, DinnerDTO dinnerDTO){
        Dinner dinner = Dinner.builder()
                .foodSet(dinnerDTO.getDinner())
                .date(LocalDateTime.now())
                .account(account)
                .build();

        return dinnerRepository.save(dinner);
    }
}