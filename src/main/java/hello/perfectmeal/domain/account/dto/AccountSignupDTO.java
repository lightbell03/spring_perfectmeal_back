package hello.perfectmeal.domain.account.dto;

import hello.perfectmeal.domain.account.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class AccountSignupDTO {
    private String name;
    private String email;
    private String password;
    private int age;
    private Double weight;
    private Double height;
    private Gender gender;
}
