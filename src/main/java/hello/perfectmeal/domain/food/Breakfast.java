package hello.perfectmeal.domain.food;

import hello.perfectmeal.domain.account.Account;
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
@Table(name = "breakfast")
@NoArgsConstructor
@AllArgsConstructor
public class Breakfast {

    @Column(name = "breakfast_id")
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "breakfast_food",
            joinColumns = @JoinColumn(name = "breakfast_id"))
    private Set<String> foodSet = new HashSet<>();

    private LocalDateTime date;
}
