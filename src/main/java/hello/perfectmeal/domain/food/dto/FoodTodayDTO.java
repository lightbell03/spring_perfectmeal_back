package hello.perfectmeal.domain.food.dto;

import hello.perfectmeal.domain.food.Breakfast;
import lombok.Getter;

import java.util.List;

@Getter
public class FoodTodayDTO {
    private BreakfastDTO breakfast;
    private LunchDTO lunch;
    private DinnerDTO dinner;

    public static FoodTodayDTO of(BreakfastDTO breakfast, LunchDTO lunch, DinnerDTO dinner){
        FoodTodayDTO foodTodayDTO = new FoodTodayDTO();
        foodTodayDTO.breakfast = breakfast;
        foodTodayDTO.lunch = lunch;
        foodTodayDTO.dinner = dinner;

        return foodTodayDTO;
    }
}
