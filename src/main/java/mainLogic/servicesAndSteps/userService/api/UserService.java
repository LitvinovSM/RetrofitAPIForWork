package mainLogic.servicesAndSteps.userService.api;

import mainLogic.DTO.userService.CreateUserRq;
import mainLogic.DTO.userService.CreateUserRs;
import mainLogic.DTO.userService.ListUsers;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @GET("users")
    Call<ListUsers> listUsers();

    @GET("users")
    Call<ListUsers> listUsersDelay(@Query("delay") String timeDelay);

    @POST("users")
    Call<CreateUserRs> createUser(@Body CreateUserRq rq);


}
