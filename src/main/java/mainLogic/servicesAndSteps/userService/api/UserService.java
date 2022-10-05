package mainLogic.servicesAndSteps.userService.api;

import mainLogic.DTO.userService.CreateUserRq;
import mainLogic.DTO.userService.CreateUserRs;
import mainLogic.DTO.userService.ListUsers;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface UserService {

    @GET("users")
    Call<ListUsers> listUsers(@HeaderMap Map<String,String> headers);

    @GET("users")
    Call<ListUsers> listUsersDelay(@HeaderMap Map<String,String> headers, @Query("delay") String timeDelay);

    @POST("users")
    Call<CreateUserRs> createUser(@HeaderMap Map<String,String> headers, @Body CreateUserRq rq);


}
