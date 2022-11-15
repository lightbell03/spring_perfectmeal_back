package hello.perfectmeal.repository.nutrient;

import hello.perfectmeal.domain.nutrient.LunchNutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchNutrientRepository extends JpaRepository<LunchNutrient, Long> {
}
