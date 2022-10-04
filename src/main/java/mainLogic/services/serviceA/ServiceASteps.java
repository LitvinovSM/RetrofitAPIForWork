package mainLogic.services.serviceA;

import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import okhttp3.Headers;
import mainLogic.DTO.serviceA.login.LoginRq;
import mainLogic.DTO.serviceA.login.LoginRs;
import mainLogic.services.serviceA.services.LoginService;
import retrofit2.Call;

import java.io.IOException;

public class ServiceASteps {

    RestWrapperServiceA api;

    @Before
    public void prepareTest() throws IOException {
        api = RestWrapperServiceA.loginAs("eve.holt@reqres.in", "cityslicka");
    }

    @И("открывает телеграмм веб")
    public void shouldAnswerWithTrue10() throws IOException, InterruptedException {
        LoginRq rq = LoginRq.builder().email("eve.holt@reqres.in").password("cityslicka").build();
        LoginService service = api.loginService;
        Call<LoginRs> call = service.login(rq);

        LoginRs rs = call.execute().body();
        Headers headers = call.request().headers();
        System.out.println(rs.toString());
    }
}
