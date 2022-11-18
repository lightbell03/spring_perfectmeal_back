package hello.perfectmeal.repository.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreakfastNutrientRepository extends JpaRepository<BreakfastNutrient, Long> {
    Optional<BreakfastNutrient> findByAccountAndBreakfast(Account account, Breakfast breakfast);
}
