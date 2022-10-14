package mainLogic.DTO.userService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mainLogic.DTO.AbstractResponse;


@Data
@EqualsAndHashCode(callSuper = false)
public class SingleUserRs extends AbstractResponse {

    @JsonProperty("data")
    private User user;

    @JsonProperty("support")
    private Support support;
}
