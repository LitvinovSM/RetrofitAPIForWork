package mainLogic.DTO.loginService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mainLogic.DTO.AbstractResponse;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginRs extends AbstractResponse {

    @JsonProperty("token")
    private String token;

}