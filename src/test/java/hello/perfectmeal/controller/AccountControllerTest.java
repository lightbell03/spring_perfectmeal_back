package hello.perfectmeal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.Gender;
import hello.perfectmeal.domain.account.dto.AccountLoginReqDTO;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.domain.jwt.Token;
import hello.perfectmeal.domain.jwt.dto.TokenDTO;
import hello.perfectmeal.repository.AccountRepository;
import hello.perfectmeal.repository.TokenRepository;
import hello.perfectmeal.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test"})
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TokenRepository tokenRepository;

    @Test
    @DisplayName("sign up")
    public void signUp() throws Exception {
        String email = "email@email.com";

        AccountSignupDTO accountSignupDTO = AccountSignupDTO.builder()
                .email(email)
                .password("pass")
                .name("name")
                .age(20)
                .gender(Gender.MAN)
                .build();

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountSignupDTO)))
                .andExpect(status().isOk());

        Account account = accountRepository.findByEmail(email).get();

        assertThat(account.getName()).isEqualTo("name");
    }

    @Test
    @DisplayName("login")
    public void login() throws Exception {
        String email = "email@email.com";
        String pass = "pass";
        AccountSignupDTO accountSignupDTO = AccountSignupDTO.builder()
                .email(email)
                .password(pass)
                .name("name")
                .age(20)
                .gender(Gender.MAN)
                .build();
        accountService.signup(accountSignupDTO);

        AccountLoginReqDTO accountLoginReqDTO = AccountLoginReqDTO.builder()
                .email(email)
                .password(pass)
                .build();

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountLoginReqDTO)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("access token refresh")
    public void accessTokenRefresh() throws Exception {
        String email = "email@email.com";
        String pass = "pass";
        AccountSignupDTO accountSignupDTO = AccountSignupDTO.builder()
                .email(email)
                .password(pass)
                .name("name")
                .age(20)
                .weight(70.0)
                .height((170.0))
                .gender(Gender.MAN)
                .build();
        Account account = accountService.signup(accountSignupDTO);

        AccountLoginReqDTO accountLoginReqDTO = AccountLoginReqDTO.builder()
                .email(email)
                .password(pass)
                .build();

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountLoginReqDTO)));

        Token token = tokenRepository.findByAccount(account).orElse(null);
        System.out.println("access token = " + token.getAccessToken());
        System.out.println("refresh token = " + token.getRefreshToken());
        Thread.sleep(5000);
        mockMvc.perform(get("/api/foods")
                        .param("date", "2022-11-23")
                        .header("Authorization", "Bearer:" + token.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        TokenDTO tokenDTO = TokenDTO.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();

        mockMvc.perform(post("/api/auth/reload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tokenDTO.getRefreshToken())))
                .andExpect(status().isOk());

        Token reToken = tokenRepository.findByAccount(account).get();

        assertThat(token.getAccessToken()).isNotEqualTo(reToken.getAccessToken());
    }

    @Test
    @DisplayName("refresh token expire")
    public void refreshTokenExpire() throws Exception {
        String email = "email@email.com";
        String pass = "pass";
        AccountSignupDTO accountSignupDTO = AccountSignupDTO.builder()
                .email(email)
                .password(pass)
                .name("name")
                .age(20)
                .weight(70.0)
                .height((170.0))
                .gender(Gender.MAN)
                .build();
        Account account = accountService.signup(accountSignupDTO);

        AccountLoginReqDTO accountLoginReqDTO = AccountLoginReqDTO.builder()
                .email(email)
                .password(pass)
                .build();

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountLoginReqDTO)));

        Token token = tokenRepository.findByAccount(account).orElse(null);
        System.out.println("access token = " + token.getAccessToken());
        System.out.println("refresh token = " + token.getRefreshToken());
        Thread.sleep(7000);

        TokenDTO tokenDTO = TokenDTO.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();

        mockMvc.perform(post("/api/auth/reload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tokenDTO)))
                .andExpect(status().isUnauthorized());
    }
}