package hello.perfectmeal.controller;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.dto.AccountDTO;
import hello.perfectmeal.domain.account.dto.AccountLoginReqDTO;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.jwt.dto.TokenDTO;
import hello.perfectmeal.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/api/auth/login")
    public ResponseEntity login(
            @RequestBody AccountLoginReqDTO accountReqDTO
    ) {
        TokenDTO tokenDTO = accountService.login(accountReqDTO);

        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity signup(
            @RequestBody AccountSignupDTO accountSignupDTO
    ) {
        Account account = accountService.signup(accountSignupDTO);

        return ResponseEntity.ok(AccountDTO.AccountToAccountDTO(account));
    }

    @PostMapping("/api/auth/reload")
    public ResponseEntity reload(
        @RequestBody TokenDTO tokenDTO
    ){
        log.info("refresh Token = {}", tokenDTO.getRefreshToken());
        try {
            String accessToken = accountService.reload(tokenDTO);
            TokenDTO reloadTokenDTO = TokenDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(tokenDTO.getRefreshToken())
                    .build();

            return ResponseEntity.ok().body(reloadTokenDTO);
        }
        catch (Exception exception){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
