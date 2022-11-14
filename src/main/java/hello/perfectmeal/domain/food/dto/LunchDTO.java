package hello.perfectmeal.domain.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hello.perfectmeal.domain.food.Lunch;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class LunchDTO {
    private Long id;
    private Set<String> lunch;

    @JsonIgnore
    public static LunchDTO LunchToLunchDTOConvertor(Lunch lunch){
        if(lunch == null) return null;
        LunchDTO lunchDTO = new LunchDTO();
        lunchDTO.id = lunch.getId();
        lunchDTO.lunch = lunch.getFoodSet();

        return lunchDTO;
    }
}
