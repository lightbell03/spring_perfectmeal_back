package hello.perfectmeal.domain.food;

import hello.perfectmeal.domain.account.Account;

import javax.persistence.*;
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

    @ElementCollection
    @CollectionTable(name = "etc_food",
    joinColumns = @JoinColumn(name = "etc_id"))
    private Set<String> etcSet;
}
