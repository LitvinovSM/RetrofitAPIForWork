package mainLogic.servicesAndSteps.loginService.api;

import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.util.Map;

public interface LoginService {
    @POST("login")
    Call<LoginRs> login(@HeaderMap Map<String, String> headers, @Body LoginRq rq);
}
