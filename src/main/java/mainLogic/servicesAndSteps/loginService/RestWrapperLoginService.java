package mainLogic.servicesAndSteps.loginService;

import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import mainLogic.servicesAndSteps.RestWrapperAbstract;
import mainLogic.servicesAndSteps.loginService.api.LoginService;
import mainLogic.servicesAndSteps.userService.api.UserService;
import retrofit2.Call;

import java.io.IOException;

import static mainLogic.servicesAndSteps.BeforeAndAfterSteps.readyRetrofit;

public class RestWrapperLoginService extends RestWrapperAbstract {

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
    public RestWrapperLoginService(String authToken) {
        loginService = readyRetrofit.create(LoginService.class);

    }
    /**
     * Method for creating different APIs with different permissions*/
    public static RestWrapperLoginService loginAs(String login, String password) throws IOException {
        LoginRq rq = LoginRq.builder().email(login).password(password).build();
        LoginService defaultLoginService = getDefaultRetrofit(BASE_URL).create(LoginService.class);
        Call<LoginRs> rsCall = defaultLoginService.login(rq);
        LoginRs body = rsCall.execute().body();
        String token = body.getToken();
        return new RestWrapperLoginService(token);
    }

}
