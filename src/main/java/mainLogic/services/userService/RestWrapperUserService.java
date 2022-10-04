package mainLogic.services.userService;

import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import mainLogic.services.RestWrapperAbstract;
import mainLogic.services.loginService.api.LoginService;
import mainLogic.services.userService.api.UserService;
import retrofit2.Call;

import java.io.IOException;

public class RestWrapperUserService extends RestWrapperAbstract {

    private static final String BASE_URL = "https://reqres.in/api/";
    /*
    Services
    */
    public UserService userService;


    /**
     * Default constructor.
     * It initializes all services before tests
     */
    public RestWrapperUserService(String authToken) {
        super(authToken,BASE_URL);
        userService = readyRetrofit.create(UserService.class);
    }


}
