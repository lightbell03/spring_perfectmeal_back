package hello.perfectmeal.domain.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Breakfast;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "breakfast_nutrient")
@Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class BreakfastNutrient {

    @Column(name = "breakfast_nutrient_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "breakfast_id")
    private Breakfast breakfast;

    @Embedded
    private Nutrient nutrient;
}
