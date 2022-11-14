package hello.perfectmeal.domain.account.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountLoginReqDTO {
    private String email;
    private String password;
}
