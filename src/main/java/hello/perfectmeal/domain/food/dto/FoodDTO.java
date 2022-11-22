package hello.perfectmeal.domain.food.dto;

import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.nutrient.Nutrient;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FoodDTO {

    private Long id;
    private Set<String> foodSet;
    private Nutrient nutrient;
    private Nutrient totalNutrient;
    private Nutrient underNutrient;

    public static FoodDTO BreakfastToFoodDTOConvertor(Breakfast breakfast, Nutrient totalNutrient, Nutrient underNutrient){
        if(breakfast == null) return null;

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.id = breakfast.getId();
        foodDTO.foodSet = breakfast.getFoodSet();
        foodDTO.nutrient = breakfast.getBreakfastNutrient().getNutrient();
        foodDTO.totalNutrient = totalNutrient;
        foodDTO.underNutrient = underNutrient;

        return foodDTO;
    }

    public static FoodDTO LunchToFoodDTOConvertor(Lunch lunch, Nutrient totalNutrient, Nutrient underNutrient){
        if(lunch == null) return null;
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.id = lunch.getId();
        foodDTO.foodSet = lunch.getFoodSet();
        foodDTO.nutrient = lunch.getLunchNutrient().getNutrient();
        foodDTO.totalNutrient = totalNutrient;
        foodDTO.underNutrient = underNutrient;

        return foodDTO;
    }

    public static FoodDTO DinnerToFoodDTOConvertor(Dinner dinner, Nutrient totalNutrient, Nutrient underNutrient){
        if(dinner == null) return null;
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.id = dinner.getId();
        foodDTO.foodSet = dinner.getFoodSet();
        foodDTO.nutrient = dinner.getDinnerNutrient().getNutrient();
        foodDTO.totalNutrient = totalNutrient;
        foodDTO.underNutrient = underNutrient;

        return foodDTO;
    }
}
