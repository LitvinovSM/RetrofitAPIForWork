package org.services.serviceA;

import org.DTO.login.LoginRq;
import org.DTO.login.LoginRs;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<LoginRs> login(@Body LoginRq rq);
}
