package mainLogic.DTO.loginService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRs {

    @JsonProperty("token")
    private String token;

}