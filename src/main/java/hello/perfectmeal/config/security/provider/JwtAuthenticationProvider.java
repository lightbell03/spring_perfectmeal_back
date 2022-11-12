package hello.perfectmeal.config.security.provider;

import hello.perfectmeal.config.security.service.AccountContext;
import hello.perfectmeal.config.security.token.JwtAuthenticationToken;
import hello.perfectmeal.repository.AccountRepository;
import hello.perfectmeal.service.AccountDetailsService;
import hello.perfectmeal.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.security.auth.login.CredentialException;

@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountDetailsService accountDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        AccountContext accountContext = (AccountContext) accountDetailsService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password, accountContext.getPassword())){
            throw new CredentialsExpiredException("Not Match Password");
        }

        return new JwtAuthenticationToken(accountContext.getAccount(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
