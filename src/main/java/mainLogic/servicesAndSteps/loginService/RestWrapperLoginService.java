package mainLogic.servicesAndSteps.loginService;

import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import mainLogic.servicesAndSteps.RestWrapperAbstract;
import mainLogic.servicesAndSteps.loginService.api.LoginService;
import okhttp3.Request;
import retrofit2.Response;

import static mainLogic.servicesAndSteps.BeforeAndAfterSteps.readyRetrofit;

public class RestWrapperLoginService extends RestWrapperAbstract {

    /*
    Services
    */
    public LoginService loginService = readyRetrofit.create(LoginService.class);
    protected Request request;
    protected LoginRq rqBody;
    protected Response<LoginRs> response;
    protected LoginRs rsBody;

    protected String token;
    protected final String tokenValuePrefix = "Bearer ";
    protected final String authorizationHeaderName = "Authorization";
    protected String defaultLogin =config.getCredentialConfig().getDefaultLogin();
    protected String defaultPassword=config.getCredentialConfig().getDefaultPassword();

}
