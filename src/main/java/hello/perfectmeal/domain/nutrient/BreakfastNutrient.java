package hello.perfectmeal.domain.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "breakfast_nutrient")
@Getter
@NoArgsConstructor @AllArgsConstructor
public class BreakfastNutrient {

    @Column(name = "breakfast_nutrient_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinTable(name = "account")
    private Account account;

    @ManyToOne
    @JoinTable(name = "breakfast")
    private Breakfast breakfast;

    @Embedded
    private Nutrient nutrient;
}
