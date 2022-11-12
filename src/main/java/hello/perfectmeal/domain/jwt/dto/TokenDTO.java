package hello.perfectmeal.domain.jwt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenDTO {

    private String accessToken;
    private String refreshToken;
}
