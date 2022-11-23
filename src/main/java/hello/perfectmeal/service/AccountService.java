package hello.perfectmeal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.perfectmeal.config.security.provider.JwtTokenProvider;
import hello.perfectmeal.config.security.service.AccountContext;
import hello.perfectmeal.config.security.token.JwtAuthenticationToken;
import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.dto.AccountLoginReqDTO;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.jwt.Token;
import hello.perfectmeal.domain.jwt.dto.TokenDTO;
import hello.perfectmeal.repository.AccountRepository;
import hello.perfectmeal.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountService {

    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenDTO login(AccountLoginReqDTO accountReqDTO) {
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(accountReqDTO.getEmail(), accountReqDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);
        Account account = (Account) authentication.getPrincipal();
        String email = account.getEmail();

        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        Token token = Token.builder()
                .account(account)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        tokenRepository.save(token);

        return jwtTokenProvider.createTokenDto(accessToken, refreshToken);
    }

    @Transactional
    public Account signup(AccountSignupDTO accountSignupDTO) {
        Account account = Account.builder()
                .name(accountSignupDTO.getName())
                .email(accountSignupDTO.getEmail())
                .gender(accountSignupDTO.getGender())
                .age(accountSignupDTO.getAge())
                .weight(accountSignupDTO.getWeight())
                .height(accountSignupDTO.getHeight())
                .password(passwordEncoder.encode(accountSignupDTO.getPassword()))
                .build();

        Account saveAccount = accountRepository.save(account);
        return saveAccount;
    }

    @Transactional
    public String reload(TokenDTO tokenDTO) throws Exception{
        Token token = tokenRepository.findByRefreshToken(tokenDTO.getRefreshToken()).orElseThrow(() -> new RuntimeException("no refresh token"));

        int flag = jwtTokenProvider.validateToken(token.getRefreshToken());

        if(flag == 1){
            String accessToken = jwtTokenProvider.createAccessToken(token.getAccount().getEmail());
            token.setAccessToken(accessToken);
            return accessToken;
        }
        else {
            throw new RuntimeException("refresh token expired");
        }
    }
}
