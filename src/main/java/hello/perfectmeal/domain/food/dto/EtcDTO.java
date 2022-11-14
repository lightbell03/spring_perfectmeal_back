package hello.perfectmeal.domain.food.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class EtcDTO {
    private Long id;
    private Set<String> etc;
}
