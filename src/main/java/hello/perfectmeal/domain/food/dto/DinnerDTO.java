package hello.perfectmeal.domain.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hello.perfectmeal.domain.food.Dinner;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DinnerDTO {
    private Long id;
    private Set<String> dinner;

    @JsonIgnore
    public static DinnerDTO DinnerToDinnerDTOConvertor(Dinner dinner){
        if(dinner == null) return null;
        DinnerDTO dinnerDTO = new DinnerDTO();
        dinnerDTO.id = dinner.getId();
        dinnerDTO.dinner = dinner.getFoodSet();

        return dinnerDTO;
    }
}
