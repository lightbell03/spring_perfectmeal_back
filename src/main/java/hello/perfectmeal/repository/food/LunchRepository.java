package hello.perfectmeal.repository.food;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import hello.perfectmeal.domain.food.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LunchRepository extends JpaRepository<Lunch, Long> {
//    Optional<Lunch> findByAccountAndDateBetween(Account account, LocalDateTime start, LocalDateTime end);
    Optional<Lunch> findByAccountAndDate(Account account, LocalDate date);
    List<Lunch> findByAccount(Account account);
}
