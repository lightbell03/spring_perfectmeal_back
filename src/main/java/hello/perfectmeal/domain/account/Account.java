package hello.perfectmeal.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Account {
    @Column(name = "account_id")
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int age;
    private Double height;
    private Double weight;

    @Builder.Default
    private String role = "ROLE_USER";
}
