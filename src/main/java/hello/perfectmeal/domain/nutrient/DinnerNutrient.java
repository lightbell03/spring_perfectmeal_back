package hello.perfectmeal.domain.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Dinner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dinner_nutrient")
@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class DinnerNutrient {

    @Column(name = "dinner_nutrient_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "dinner_id")
    private Dinner dinner;

    @Embedded
    private Nutrient nutrient;
}
