package hello.perfectmeal.service;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.food.dto.FoodDTO;
import hello.perfectmeal.repository.food.BreakfastRepository;
import hello.perfectmeal.repository.food.DinnerRepository;
import hello.perfectmeal.repository.food.LunchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

    private final BreakfastRepository breakfastRepository;
    private final LunchRepository lunchRepository;
    private final DinnerRepository dinnerRepository;

    public Breakfast getBreakfast(Account account, String date){
        LocalDate localDate = parseDate(date);
        return breakfastRepository.findByAccountAndDate(account, localDate).orElse(null);
    }

    public Lunch getLunch(Account account, String date) {
        LocalDate localDate = parseDate(date);
        return lunchRepository.findByAccountAndDate(account, localDate)
                .orElse(null);
    }

    public Dinner getDinner(Account account, String date) {
        LocalDate localDate = parseDate(date);
        return dinnerRepository.findByAccountAndDate(account, localDate)
                .orElse(null);
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    @Transactional
    public Breakfast saveBreakfast(Account account, FoodDTO foodDTO, String date) throws Exception {
        Breakfast breakfast = Breakfast.builder()
                .foodSet(foodDTO.getFoodSet())
                .date(parseDate(date))
                .account(account)
                .build();
        Breakfast saveBreakfast = breakfastRepository.save(breakfast);

        return saveBreakfast;
    }

    @Transactional
    public Lunch saveLunch(Account account, FoodDTO foodDTO, String date) {

        Lunch lunch = Lunch.builder()
                .foodSet(foodDTO.getFoodSet())
                .date(parseDate(date))
                .account(account)
                .build();

        return lunchRepository.save(lunch);
    }

    @Transactional
    public Dinner saveDinner(Account account, FoodDTO foodDTO, String date){
        Dinner dinner = Dinner.builder()
                .foodSet(foodDTO.getFoodSet())
                .date(parseDate(date))
                .account(account)
                .build();

        return dinnerRepository.save(dinner);
    }
}