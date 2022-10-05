package mainLogic.servicesAndSteps.userService;

import mainLogic.servicesAndSteps.RestWrapperAbstract;
import mainLogic.servicesAndSteps.userService.api.UserService;

import static mainLogic.servicesAndSteps.BeforeAndAfterSteps.readyRetrofit;

public class RestWrapperUserService extends RestWrapperAbstract {

    /*
    Services
    */
    public UserService userService = readyRetrofit.create(UserService.class);

}
