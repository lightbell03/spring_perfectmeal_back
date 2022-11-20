package hello.perfectmeal.repository.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Lunch;
import hello.perfectmeal.domain.nutrient.LunchNutrient;
import hello.perfectmeal.domain.nutrient.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface LunchNutrientRepository extends JpaRepository<LunchNutrient, Long> {
    Optional<LunchNutrient> findByAccountAndLunch(Account account, Lunch lunch);
}
