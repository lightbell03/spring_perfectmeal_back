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

    @ManyToOne
    @JoinTable(name = "account")
    private Account account;

    @ManyToOne
    @JoinTable(name = "etc")
    private Etc etc;

    @Embedded
    private Nutrient nutrient;
}
