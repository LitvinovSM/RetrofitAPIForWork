package mainLogic.servicesAndSteps.loginService;

import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import okhttp3.Headers;
import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import mainLogic.servicesAndSteps.loginService.api.LoginService;
import retrofit2.Call;

import java.io.IOException;

public class LoginServiceSteps {

    RestWrapperLoginService api;

    @Before
    public void prepareTest() throws IOException {
        api = RestWrapperLoginService.loginAs("eve.holt@reqres.in", "cityslicka");
    }

    @И("пользователь авторизуется")
    public void shouldAnswerWithTrue10() throws IOException {
        LoginRq rq = LoginRq.builder().email("eve.holt@reqres.in").password("cityslicka").build();
        LoginService service = api.loginService;
        Call<LoginRs> call = service.login(rq);

        LoginRs rs = call.execute().body();
        Headers headers = call.request().headers();
        System.out.println(rs.toString());
    }
}
