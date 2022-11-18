package hello.perfectmeal.domain.food;

import hello.perfectmeal.domain.account.Account;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "etc_food",
    joinColumns = @JoinColumn(name = "etc_id"))
    private Set<String> etcSet;

//    @ElementCollection
//    @CollectionTable(name = "breakfast_food_photo_path",
//            joinColumns = @JoinColumn(name = "breakfast_id"))
//    private String imagePath;

    private LocalDateTime date;
}
