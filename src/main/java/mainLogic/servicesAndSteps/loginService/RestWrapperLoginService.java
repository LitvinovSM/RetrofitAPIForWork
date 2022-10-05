package mainLogic.servicesAndSteps.loginService;

import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import mainLogic.servicesAndSteps.BeforeAndAfterSteps;
import mainLogic.servicesAndSteps.RestWrapperAbstract;
import mainLogic.servicesAndSteps.loginService.api.LoginService;
import mainLogic.servicesAndSteps.userService.api.UserService;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

import static mainLogic.servicesAndSteps.BeforeAndAfterSteps.readyRetrofit;

public class RestWrapperLoginService extends RestWrapperAbstract {

    /*
    Services
    */
    public LoginService loginService;
    protected Request request;
    protected LoginRq rqBody;
    protected Response<LoginRs> response;
    protected LoginRs rsBody;

    protected String token;
    protected final String tokenValuePrefix = "Bearer ";
    protected final String authorizationHeaderName = "Authorization";
    /**
     * Default constructor.
     * It initializes all services before tests
     */
    public RestWrapperLoginService() {
        loginService = readyRetrofit.create(LoginService.class);
    }

}
