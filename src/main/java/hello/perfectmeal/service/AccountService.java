package hello.perfectmeal.service;

import hello.perfectmeal.config.exception.ForbiddenException;
import hello.perfectmeal.config.security.provider.JwtTokenProvider;
import hello.perfectmeal.config.security.token.JwtAuthenticationToken;
import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.dto.AccountDTO;
import hello.perfectmeal.domain.account.dto.AccountLoginReqDTO;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.jwt.dto.TokenDTO;
import hello.perfectmeal.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    @Value("${jwt.refresh-token-expire-time}")
    private Long refreshTokenExpireTime;
    @Value("${jwt.access-token-expire-time}")
    private Long accessTokenExpireTime;

    @Transactional
    public TokenDTO login(AccountLoginReqDTO accountReqDTO) {
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(accountReqDTO.getEmail(), accountReqDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);
        Account account = (Account) authentication.getPrincipal();
        String email = account.getEmail();

        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        redisService.setRefreshToken(email, refreshToken, refreshTokenExpireTime);

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

    public TokenDTO reload(TokenDTO tokenDTO) throws ForbiddenException{
        int flag = jwtTokenProvider.validateToken(tokenDTO.getRefreshToken());

        if(flag == 1){
            String email = jwtTokenProvider.getEmailByToken(tokenDTO.getRefreshToken());
            String refreshToken = (String) redisService.getRefreshToken(email);

            if(redisService.hasKeyBlackList(refreshToken)){
                throw new ForbiddenException("logout user");
            }
            if(ObjectUtils.isEmpty(refreshToken)){
                throw new ForbiddenException("not exist refreshToken");
            }

            if(!refreshToken.equals(tokenDTO.getRefreshToken())){
                throw new ForbiddenException("invalid refresh token");
            }

            String reissuedAccessToken = jwtTokenProvider.createAccessToken(email);
            String reissuedRefreshToken = jwtTokenProvider.createRefreshToken(email);
            redisService.setRefreshToken(email, refreshToken, refreshTokenExpireTime);

            TokenDTO reissuedTokenDto = jwtTokenProvider.createTokenDto(reissuedAccessToken, reissuedRefreshToken);
            return reissuedTokenDto;
        }
        else {
            throw new ForbiddenException("refresh token expired");
        }
    }

    public void logout(TokenDTO tokenDTO) {
        redisService.setBlackList(tokenDTO.getAccessToken(), "accessToken", accessTokenExpireTime);
        redisService.setBlackList(tokenDTO.getRefreshToken(), "refreshToken", refreshTokenExpireTime);
    }
}
