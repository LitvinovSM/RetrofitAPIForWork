package mainLogic.DTO.userService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class SingleUserRs {

    @JsonProperty("data")
    private User user;

    @JsonProperty("support")
    private Support support;
}
