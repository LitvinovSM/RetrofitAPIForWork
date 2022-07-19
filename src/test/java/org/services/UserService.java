package org.services;

import org.DTO.users.CreateUserRq;
import org.DTO.users.CreateUserRs;
import org.DTO.users.ListUsers;
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
