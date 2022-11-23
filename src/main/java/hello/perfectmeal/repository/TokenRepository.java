package hello.perfectmeal.repository;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.jwt.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByRefreshToken(String refreshToken);
    Optional<Token> findByAccount(Account account);
}
