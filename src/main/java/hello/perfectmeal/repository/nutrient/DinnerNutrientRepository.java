package hello.perfectmeal.repository.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Dinner;
import hello.perfectmeal.domain.nutrient.DinnerNutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DinnerNutrientRepository extends JpaRepository<DinnerNutrient, Long> {
    Optional<DinnerNutrient> findByAccountAndDinner(Account account, Dinner dinner);
}
