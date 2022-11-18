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

    private FoodTodayDTO(Convertor convertor){
        this.breakfast = convertor.breakfast;
        this.lunch = convertor.lunch;
        this.dinner = convertor.dinner;

        this.breakfastNutrient = convertor.breakfastNutrient;
        this.lunchNutrient = convertor.lunchNutrient;
        this.dinnerNutrient = convertor.dinnerNutrient;
    }

    public static Convertor of() {
        return new Convertor();
    }

    public static class Convertor {
        private Set<String> breakfast;
        private Set<String> lunch;
        private Set<String> dinner;
        private Nutrient breakfastNutrient;
        private Nutrient lunchNutrient;
        private Nutrient dinnerNutrient;

        public Convertor food(Breakfast breakfast, Lunch lunch, Dinner dinner) {
            this.breakfast = breakfast == null ? null : breakfast.getFoodSet();
            this.lunch= lunch == null ? null : lunch.getFoodSet();
            this.dinner = dinner == null ? null : dinner.getFoodSet();

            return this;
        }

        public Convertor nutrient(BreakfastNutrient breakfastNutrient, LunchNutrient lunchNutrient, DinnerNutrient dinnerNutrient) {
            this.breakfastNutrient = breakfastNutrient == null ? null : breakfastNutrient.getNutrient();
            this.lunchNutrient = lunchNutrient == null ? null : lunchNutrient.getNutrient();
            this.dinnerNutrient = dinnerNutrient == null ? null : dinnerNutrient.getNutrient();

            return this;
        }

        public FoodTodayDTO build() {
            return new FoodTodayDTO(this);
        }
    }

}
