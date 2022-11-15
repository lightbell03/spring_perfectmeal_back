package hello.perfectmeal.repository.nutrient;

import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreakfastNutrientRepository extends JpaRepository<BreakfastNutrient, Long> {
}
