package hello.perfectmeal.domain.food;

import hello.perfectmeal.domain.account.Account;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "etc")
public class Etc {

    @Column(name = "etc_id")
    private Long id;

    @ManyToOne
    @JoinTable(name = "account")
    private Account account;

    @ElementCollection
    @CollectionTable(name = "etc_food",
    joinColumns = @JoinColumn(name = "etc_id"))
    private Set<String> etcSet;
}
