package hello.perfectmeal.domain.food.dto;

import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import hello.perfectmeal.domain.nutrient.DinnerNutrient;
import hello.perfectmeal.domain.nutrient.LunchNutrient;
import hello.perfectmeal.domain.nutrient.Nutrient;
import lombok.Getter;

import java.util.Set;

@Getter
public class FoodTodayDTO {
    private Set<String> breakfast;
    private Set<String> lunch;
    private Set<String> dinner;
    private Nutrient breakfastNutrient;
    private Nutrient lunchNutrient;
    private Nutrient dinnerNutrient;

    public static FoodTodayDTO of(Breakfast breakfast, Lunch lunch, Dinner dinner) {
        FoodTodayDTO foodTodayDTO = new FoodTodayDTO();
        foodTodayDTO.breakfast = breakfast == null ? null : breakfast.getFoodSet();
        foodTodayDTO.breakfastNutrient = breakfast == null ? null : breakfast.getBreakfastNutrient().getNutrient();

        foodTodayDTO.lunch = lunch == null ? null : lunch.getFoodSet();
        foodTodayDTO.lunchNutrient = lunch == null ? null : lunch.getLunchNutrient().getNutrient();

        foodTodayDTO.dinner = dinner == null ? null : dinner.getFoodSet();
        foodTodayDTO.dinnerNutrient = dinner == null ? null : dinner.getDinnerNutrient().getNutrient();

        return foodTodayDTO;
    }

}
