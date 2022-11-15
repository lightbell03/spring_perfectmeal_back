package hello.perfectmeal.config.security.provider;

import hello.perfectmeal.config.security.service.AccountContext;
import hello.perfectmeal.config.security.token.JwtAuthenticationToken;
import hello.perfectmeal.config.security.service.AccountDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final AccountDetailsService accountDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        AccountContext accountContext = (AccountContext) accountDetailsService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password, accountContext.getPassword())){
            throw new CredentialsExpiredException("Not Match Password");
        }

        return new JwtAuthenticationToken(accountContext.getAccount(), "", accountContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
