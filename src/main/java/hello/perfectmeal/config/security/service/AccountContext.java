package hello.perfectmeal.config.security.service;

import hello.perfectmeal.domain.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User {
    private Account account;

    public AccountContext(Account account, String password, Collection<? extends GrantedAuthority> authorities) {
        super(account.getEmail(), password, authorities);
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
}
