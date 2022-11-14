package hello.perfectmeal.domain.food.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hello.perfectmeal.domain.food.Breakfast;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BreakfastDTO {
    private Long id;
    private Set<String> breakfast;

    @JsonIgnore
    public static BreakfastDTO BreakfastToBreakfastDTOConvertor(Breakfast breakfast){
        if(breakfast == null) return null;

        BreakfastDTO breakfastDTO = new BreakfastDTO();
        breakfastDTO.id = breakfast.getId();
        breakfastDTO.breakfast = breakfast.getFoodSet();

        return breakfastDTO;
    }
}
