package hello.perfectmeal.domain.food;

import hello.perfectmeal.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "dinner")
@NoArgsConstructor @AllArgsConstructor
public class Dinner {

    @Column(name = "dinner_id")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinTable(name = "account")
    private Account account;

    @ElementCollection
    @CollectionTable(name = "dinner_food",
    joinColumns = @JoinColumn(name = "dinner_id"))
    private Set<String> foodSet = new HashSet<>();

}
