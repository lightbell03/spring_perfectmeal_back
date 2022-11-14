package hello.perfectmeal.controller;

import hello.perfectmeal.domain.account.dto.AccountLoginReqDTO;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.jwt.dto.TokenDTO;
import hello.perfectmeal.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
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
        String email = accountService.signup(accountSignupDTO);

        return ResponseEntity.ok().build();
    }
}
