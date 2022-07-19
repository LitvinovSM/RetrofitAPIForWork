package org.services.serviceA;

import org.DTO.serviceA.login.LoginRq;
import org.DTO.serviceA.login.LoginRs;
import org.services.RestWrapperAbstract;
import org.services.serviceA.services.LoginService;
import org.services.serviceA.services.UserService;
import retrofit2.Call;

import java.io.IOException;

public class RestWrapper extends RestWrapperAbstract {

    private static final String BASE_URL = "https://reqres.in/api/";
    /*
    Services
    */
    public LoginService loginService;
    public UserService userService;


    /**
     * Default constructor.
     * It initializes all services before tests
     */
    public RestWrapper(String authToken) {
        super(authToken,BASE_URL);
        userService = readyRetrofit.create(UserService.class);
        loginService = readyRetrofit.create(LoginService.class);

    }
    /**
     * Method for creating different APIs with different permissions*/
    public static RestWrapper loginAs(String login, String password) throws IOException {
        LoginRq rq = LoginRq.builder().email(login).password(password).build();
        LoginService defaultLoginService = getDefaultRetrofit(BASE_URL).create(LoginService.class);
        Call<LoginRs> rsCall = defaultLoginService.login(rq);
        LoginRs body = rsCall.execute().body();
        String token = body.getToken();
        return new RestWrapper(token);
    }

}
