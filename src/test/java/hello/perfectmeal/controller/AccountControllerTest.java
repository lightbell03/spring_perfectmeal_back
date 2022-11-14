package hello.perfectmeal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.account.Gender;
import hello.perfectmeal.domain.account.dto.AccountLoginReqDTO;
import hello.perfectmeal.domain.account.dto.AccountSignupDTO;
import hello.perfectmeal.repository.AccountRepository;
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
}