package hello.perfectmeal.config.security.provider;

import hello.perfectmeal.config.security.service.AccountContext;
import hello.perfectmeal.config.security.token.JwtAuthenticationToken;
import hello.perfectmeal.domain.jwt.dto.TokenDTO;
import hello.perfectmeal.service.AccountDetailsService;
import hello.perfectmeal.service.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Autowired
    AccountDetailsService accountDetailsService;
    private final Long ACCESS_TOKEN_EXPIRE_TIME;
    private final Long REFRESH_TOKEN_EXPIRE_TIME;
    private final Key key;

    public JwtTokenProvider(
        @Value("${jwt.secret}") String secretKey,
        @Value("${jwt.access-token-expire-time}") Long accessTime,
        @Value("${jwt.refresh-token-expire-time}") Long refreshTime
    ){
        this.ACCESS_TOKEN_EXPIRE_TIME = accessTime;
        this.REFRESH_TOKEN_EXPIRE_TIME = refreshTime;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    protected String createToken(String email, long tokenTime){
        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenTime))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String createAccessToken(String email){
        return createToken(email, ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String createRefreshToken(String email){
        return createToken(email, REFRESH_TOKEN_EXPIRE_TIME);
    }

    public String getEmailByToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token).getBody();

        String email = claims.getSubject();
        return email;
    }

    public int validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return 1;
        } catch (ExpiredJwtException exception){
            return 2;
        } catch (Exception exception){
            return -1;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        AccountContext accountContext = (AccountContext) accountDetailsService.loadUserByUsername(claims.getSubject());

        AccountContext principle = new AccountContext(accountContext.getAccount(), "", null);

        return new JwtAuthenticationToken(principle, "");
    }

    public TokenDTO createTokenDto(String accessToken, String refreshToken){
        return TokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
