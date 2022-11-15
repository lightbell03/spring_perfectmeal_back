package hello.perfectmeal.repository.nutrient;

import hello.perfectmeal.domain.nutrient.DinnerNutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinnerNutrientRepository extends JpaRepository<DinnerNutrient, Long> {
}
