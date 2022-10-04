package mainLogic.servicesAndSteps.userService;

import mainLogic.servicesAndSteps.RestWrapperAbstract;
import mainLogic.servicesAndSteps.userService.api.UserService;

import static mainLogic.servicesAndSteps.BeforeAndAfterSteps.readyRetrofit;

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
        userService = readyRetrofit.create(UserService.class);
    }


}
