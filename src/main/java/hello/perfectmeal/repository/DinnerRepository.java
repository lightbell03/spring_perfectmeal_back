package hello.perfectmeal.repository;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Dinner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DinnerRepository extends JpaRepository<Dinner, Long> {
    Optional<Dinner> findByAccountAndDateBetween(Account account, LocalDateTime start, LocalDateTime end);
    List<Dinner> findByAccount(Account account);
}
