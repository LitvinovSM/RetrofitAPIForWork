package mainLogic.DTO.serviceA.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CreateUserRq{

	@JsonProperty("name")
	private String name;

	@JsonProperty("job")
	private String job;

}