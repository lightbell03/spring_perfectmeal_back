package hello.perfectmeal.domain.food;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.nutrient.LunchNutrient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lunch")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lunch {

    @Column(name = "lunch_id")
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ElementCollection
    @CollectionTable(name = "lunch_food",
            joinColumns = @JoinColumn(name = "lunch_id"))
    @Builder.Default
    private Set<String> foodSet = new HashSet<>();

    @OneToOne(mappedBy = "lunch", cascade = CascadeType.ALL, orphanRemoval = true)
    private LunchNutrient lunchNutrient;

    private LocalDateTime date;

    public void setLunchNutrient(LunchNutrient lunchNutrient){
        this.lunchNutrient = lunchNutrient;
    }
}
