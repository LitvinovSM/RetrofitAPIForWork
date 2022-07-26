package mainLogic.DTO.userService;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mainLogic.DTO.AbstractResponse;
import mainLogic.utils.deserializeStrategies.StringToLocalDateTime;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateUserRs extends AbstractResponse {

    @JsonProperty("createdAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    @JsonDeserialize(using = StringToLocalDateTime.class)
    private LocalDateTime createdAt;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("job")
    private String job;

}