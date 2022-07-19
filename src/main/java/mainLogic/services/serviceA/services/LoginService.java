package mainLogic.services.serviceA.services;

import mainLogic.DTO.serviceA.login.LoginRq;
import mainLogic.DTO.serviceA.login.LoginRs;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("login")
    Call<LoginRs> login(@Body LoginRq rq);
}
