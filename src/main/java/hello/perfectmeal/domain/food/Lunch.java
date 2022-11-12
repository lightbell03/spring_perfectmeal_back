package hello.perfectmeal.domain.food;

import hello.perfectmeal.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lunch")
@Getter
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
    private Set<String> foodSet = new HashSet<>();
}
