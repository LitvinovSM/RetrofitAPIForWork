package mainLogic.servicesAndSteps.userService.api;

import mainLogic.DTO.userService.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface UserService {

    @GET("users")
    Call<ListUsersRs> getListUsers(@HeaderMap Map<String, String> headers);

    @GET("users")
    Call<ListUsersRs> getListUsersDelay(@HeaderMap Map<String, String> headers,
                                        @Query("delay") String timeDelay);

    @GET("users/{id}")
    Call<SingleUserRs> getUser(@HeaderMap Map<String, String> headers,
                               @Path(value = "id", encoded = true) int id);

    @POST("users")
    Call<CreateUserRs> createUser(@HeaderMap Map<String, String> headers,
                                  @Body CreateUserRq rq);


}
