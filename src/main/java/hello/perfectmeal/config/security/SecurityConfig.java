package hello.perfectmeal.config.security;

import hello.perfectmeal.config.security.filter.JwtAuthenticationFilter;
import hello.perfectmeal.config.security.provider.JwtAuthenticationProvider;
import hello.perfectmeal.config.security.provider.JwtTokenProvider;
import hello.perfectmeal.config.security.service.AccountDetailsService;
import hello.perfectmeal.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountDetailsService accountDetailsService;
    private final RedisService redisService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/foods").permitAll()
                .antMatchers("/api/foods/*").hasRole("USER")
                .anyRequest().authenticated()

                .and()
                .authenticationProvider(new JwtAuthenticationProvider(passwordEncoder(), accountDetailsService))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
        ;


        http.csrf().disable();

        return http.build();
    }
}
