package mainLogic.servicesAndSteps.userService;

import mainLogic.DTO.loginService.LoginRs;
import mainLogic.DTO.userService.ListUsersRs;
import mainLogic.DTO.userService.SingleUserRs;
import mainLogic.DTO.userService.Support;
import mainLogic.DTO.userService.User;
import mainLogic.servicesAndSteps.RestWrapperAbstract;
import mainLogic.servicesAndSteps.userService.api.UserService;
import retrofit2.Response;

import static mainLogic.servicesAndSteps.BeforeAndAfterSteps.readyRetrofit;

public class RestWrapperUserService extends RestWrapperAbstract {

    public UserService userService = readyRetrofit.create(UserService.class);

    protected Response<SingleUserRs> singleUserResponse;

    protected static SingleUserRs singleUserRs;
    protected static User userFromResponse;
    protected static Support supportFromResponse;

    protected Response<ListUsersRs> listUsersResponse;
}
