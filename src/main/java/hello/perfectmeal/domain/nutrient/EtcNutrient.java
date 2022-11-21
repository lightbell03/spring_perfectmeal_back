package hello.perfectmeal.domain.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Etc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter
@Table(name = "etc_nutrient")
public class EtcNutrient {
    @Column(name = "etc_nutrient_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "etc_id")
    private Etc etc;

    @Embedded
    private Nutrient nutrient;
}
