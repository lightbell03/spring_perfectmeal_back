package hello.perfectmeal.domain.food.dto;

import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import hello.perfectmeal.domain.nutrient.DinnerNutrient;
import hello.perfectmeal.domain.nutrient.LunchNutrient;
import hello.perfectmeal.domain.nutrient.Nutrient;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class FoodTodayDTO {
    private Set<String> breakfast;
    private Set<String> lunch;
    private Set<String> dinner;
    private Nutrient breakfastNutrient;
    private Nutrient lunchNutrient;
    private Nutrient dinnerNutrient;
    private Nutrient totalNutrient;
    private Nutrient underNutrient;

    public static FoodTodayDTO of(Breakfast breakfast, Lunch lunch, Dinner dinner, Nutrient totalNutrient, Nutrient underNutrient) {
        FoodTodayDTO foodTodayDTO = new FoodTodayDTO();
        foodTodayDTO.breakfast = breakfast == null ? new HashSet<>() : breakfast.getFoodSet();
        foodTodayDTO.breakfastNutrient = breakfast == null ? new Nutrient() : breakfast.getBreakfastNutrient().getNutrient();

        foodTodayDTO.lunch = lunch == null ? new HashSet<>() : lunch.getFoodSet();
        foodTodayDTO.lunchNutrient = lunch == null ? new Nutrient() : lunch.getLunchNutrient().getNutrient();

        foodTodayDTO.dinner = dinner == null ? new HashSet<>() : dinner.getFoodSet();
        foodTodayDTO.dinnerNutrient = dinner == null ? new Nutrient() : dinner.getDinnerNutrient().getNutrient();

        foodTodayDTO.totalNutrient = totalNutrient;
        foodTodayDTO.underNutrient = underNutrient;

        return foodTodayDTO;
    }

}
