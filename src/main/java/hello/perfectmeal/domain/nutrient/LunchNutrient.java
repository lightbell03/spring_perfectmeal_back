package hello.perfectmeal.domain.nutrient;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.food.Lunch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Entity
@Table(name = "lunch_nutrient")
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class LunchNutrient {

    @Column(name = "lunch_nutrient_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lunch_id")
    private Lunch lunch;

    @Embedded
    private Nutrient nutrient;
}
