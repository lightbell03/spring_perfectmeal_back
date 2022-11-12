package hello.perfectmeal.config.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principle;
    private Object credential;

    public JwtAuthenticationToken(Object principle, Object credential){
        super(null);
        this.principle = principle;
        this.credential = credential;
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(Object principle, Object credential, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.principle = principle;
        this.credential = credential;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credential;
    }

    @Override
    public Object getPrincipal() {
        return this.principle;
    }
}
