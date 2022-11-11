package hello.perfectmeal.domain.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Lunch;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "lunch_nutrient")
@NoArgsConstructor @AllArgsConstructor
@Getter
public class LunchNutrient {

    @Column(name = "lunch_nutrient_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinTable(name = "account")
    private Account account;

    @ManyToOne
    @JoinTable
    private Lunch lunch;

    @Embedded
    private Nutrient nutrient;
}
