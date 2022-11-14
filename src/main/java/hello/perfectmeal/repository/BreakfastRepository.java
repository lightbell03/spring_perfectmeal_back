package hello.perfectmeal.repository;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BreakfastRepository extends JpaRepository<Breakfast, Long> {
    Optional<Breakfast> findByAccountAndDateBetween(Account account, LocalDateTime start, LocalDateTime end);
    List<Breakfast> findByAccount(Account account);
}
