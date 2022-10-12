package mainLogic.DTO.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorMessage {
    @JsonProperty("error")
    String error;
}
