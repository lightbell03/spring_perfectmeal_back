package hello.perfectmeal.domain.account.dto;

import hello.perfectmeal.domain.account.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountSignupDTO {
    private String name;
    private String email;
    private String password;
    private int age;
    private Gender gender;
}
