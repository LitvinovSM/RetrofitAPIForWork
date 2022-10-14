package mainLogic.DTO.userService;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mainLogic.DTO.AbstractResponse;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListUsersRs extends AbstractResponse {

    @JsonProperty("per_page")
    private int perPage;

    @JsonProperty("total")
    private int total;

    @JsonProperty("data")
    private List<User> usersList;

    @JsonProperty("page")
    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("support")
    private Support support;
}