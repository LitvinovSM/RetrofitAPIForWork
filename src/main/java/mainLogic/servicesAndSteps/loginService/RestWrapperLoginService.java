package mainLogic.servicesAndSteps.loginService;

import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import mainLogic.servicesAndSteps.RestWrapperAbstract;
import mainLogic.servicesAndSteps.loginService.api.LoginService;
import mainLogic.utils.configs.Auth;
import okhttp3.Request;
import retrofit2.Response;

import static mainLogic.servicesAndSteps.BeforeAndAfterSteps.readyRetrofit;

public class RestWrapperLoginService extends RestWrapperAbstract {

    public LoginService loginService = readyRetrofit.create(LoginService.class);
    protected LoginRq rqBody;
    protected Response<LoginRs> response;
    protected LoginRs rsBody;

    protected String token;
    protected final String tokenValuePrefix = "Bearer ";
    protected final String authorizationHeaderName = "Authorization";

    protected Auth auth = standConfig.getAuth();
    protected String defaultLogin =auth.getDefaultAuth().getLogin();
    protected String defaultPassword=auth.getDefaultAuth().getPassword();

}
