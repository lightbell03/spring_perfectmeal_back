package hello.perfectmeal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.perfectmeal.config.security.provider.JwtTokenProvider;
import hello.perfectmeal.config.security.service.AccountContext;
import hello.perfectmeal.config.security.token.JwtAuthenticationToken;
import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.dto.AccountLoginReqDTO;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.jwt.dto.TokenDTO;
import hello.perfectmeal.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenDTO login(AccountLoginReqDTO accountReqDTO) {
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(accountReqDTO.getEmail(), accountReqDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);
        Account account = (Account) authentication.getPrincipal();
        String email = account.getEmail();

        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        return jwtTokenProvider.createTokenDto(accessToken, refreshToken);
    }

    public String signup(AccountSignupDTO accountSignupDTO) {
        Account account = Account.builder()
                .name(accountSignupDTO.getName())
                .email(accountSignupDTO.getEmail())
                .gender(accountSignupDTO.getGender())
                .age(accountSignupDTO.getAge())
                .password(passwordEncoder.encode(accountSignupDTO.getPassword()))
                .build();

        Account saveAccount = accountRepository.save(account);
        return saveAccount.getEmail();
    }
}
