package hello.perfectmeal.domain.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Dinner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dinner_nutrient")
@Getter
@AllArgsConstructor @NoArgsConstructor
public class DinnerNutrient {

    @Column(name = "dinner_nutrient_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinTable(name = "account")
    private Account account;

    @ManyToOne
    @JoinTable(name = "dinner")
    private Dinner dinner;

    @Embedded
    private Nutrient nutrient;
}
