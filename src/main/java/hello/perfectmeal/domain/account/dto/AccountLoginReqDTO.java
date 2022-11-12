package hello.perfectmeal.domain.account.dto;

import lombok.Getter;

@Getter
public class AccountLoginReqDTO {
    private String email;
    private String password;
}
