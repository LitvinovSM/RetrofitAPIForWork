package org.DTO.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User{

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("id")
	private int id;

	@JsonProperty("avatar")
	private String avatar;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("email")
	private String email;

}