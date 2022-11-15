package hello.perfectmeal.config.security.filter;

import hello.perfectmeal.config.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().startsWith("/api/auth/")){
            log.info("path = {}", request.getRequestURI());
            filterChain.doFilter(request, response);
        } else {
            String token = resolveHeader(request);

            if(StringUtils.hasText(token)) {
                int flag = jwtTokenProvider.validateToken(token);

                if (flag == 1) {
                    this.setAuthentication(token);

                    filterChain.doFilter(request, response);
                } else if (flag == 2) {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    PrintWriter out = response.getWriter();
                    out.println("expired access token");
                } else {
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    response.setStatus(HttpStatus.FORBIDDEN.value());

                    PrintWriter out = response.getWriter();
                    out.println("wrong token");
                }
            }
            else {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.FORBIDDEN.value());

                PrintWriter out = response.getWriter();
                out.println("no token");
            }
        }
    }

    private void setAuthentication(String token) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String resolveHeader(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");

        if(StringUtils.hasText(auth) && auth.startsWith("Bearer")){
            return auth.substring(7);
        }
        else {
            throw new RuntimeException("Not Fount Authorization Header");
        }
    }
}
