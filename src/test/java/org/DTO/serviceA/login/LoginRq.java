package org.DTO.serviceA.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRq{

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;

}