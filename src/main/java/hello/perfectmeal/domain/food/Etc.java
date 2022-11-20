package hello.perfectmeal.domain.food;

import hello.perfectmeal.domain.account.Account;
import hello.perfectmeal.domain.nutrient.BreakfastNutrient;
import hello.perfectmeal.domain.nutrient.EtcNutrient;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "etc")
public class Etc {

    @Column(name = "etc_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "etc_food",
    joinColumns = @JoinColumn(name = "etc_id"))
    private Set<String> etcSet;

    @OneToOne(mappedBy = "etc", cascade = CascadeType.ALL, orphanRemoval = true)
    private EtcNutrient etcNutrient;

    private LocalDateTime date;

    public void setEtcNutrient(EtcNutrient etcNutrient) {
        this.etcNutrient = etcNutrient;
    }
}
