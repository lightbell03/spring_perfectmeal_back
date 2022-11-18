package hello.perfectmeal.domain.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Etc;

import javax.persistence.*;

@Entity
@Table(name = "etc_nutrient")
public class EtcNutrient {
    @Column(name = "etc_nutrient_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "etc_id")
    private Etc etc;

    @Embedded
    private Nutrient nutrient;
}
