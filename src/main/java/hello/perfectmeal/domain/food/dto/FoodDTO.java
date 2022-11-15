package hello.perfectmeal.domain.food.dto;

import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.food.Lunch;
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

    public static FoodDTO BreakfastToFoodDTOConvertor(Breakfast breakfast){
        if(breakfast == null) return null;
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.id = breakfast.getId();
        foodDTO.foodSet = breakfast.getFoodSet();

        return foodDTO;
    }

    public static FoodDTO LunchToFoodDTOConvertor(Lunch lunch){
        if(lunch == null) return null;
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.id = lunch.getId();
        foodDTO.foodSet = lunch.getFoodSet();

        return foodDTO;
    }

    public static FoodDTO DinnerToFoodDTOConvertor(Dinner dinner){
        if(dinner == null) return null;
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.id = dinner.getId();
        foodDTO.foodSet = dinner.getFoodSet();

        return foodDTO;
    }
}
