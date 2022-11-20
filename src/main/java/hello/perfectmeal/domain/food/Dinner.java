package hello.perfectmeal.domain.food;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import hello.perfectmeal.domain.nutrient.DinnerNutrient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@Table(name = "dinner")
@NoArgsConstructor @AllArgsConstructor
public class Dinner {

    @Column(name = "dinner_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "dinner_food",
    joinColumns = @JoinColumn(name = "dinner_id"))
    private Set<String> foodSet = new HashSet<>();

    @OneToOne(mappedBy = "dinner", cascade = CascadeType.ALL, orphanRemoval = true)
    private DinnerNutrient dinnerNutrient;

    private LocalDateTime date;

    public void setDinnerNutrient(DinnerNutrient dinnerNutrient){
        this.dinnerNutrient = dinnerNutrient;
    }
}
