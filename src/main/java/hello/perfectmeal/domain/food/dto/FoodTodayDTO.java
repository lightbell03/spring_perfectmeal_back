package hello.perfectmeal.domain.food.dto;

import hello.perfectmeal.domain.food.Breakfast;
import lombok.Getter;

@Getter
public class FoodTodayDTO {
    private FoodDTO breakfast;
    private FoodDTO lunch;
    private FoodDTO dinner;

    public static FoodTodayDTO of(FoodDTO breakfastFoodDTO, FoodDTO lunchFoodDTO, FoodDTO dinnerFoodDTO){
        FoodTodayDTO foodTodayDTO = new FoodTodayDTO();
        foodTodayDTO.breakfast = breakfastFoodDTO;
        foodTodayDTO.lunch = lunchFoodDTO;
        foodTodayDTO.dinner = dinnerFoodDTO;

        return foodTodayDTO;
    }
}
