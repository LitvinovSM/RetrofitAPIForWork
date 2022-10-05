package mainLogic.servicesAndSteps.loginService;

import io.cucumber.java.ru.И;
import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import retrofit2.Call;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoginServiceSteps extends RestWrapperLoginService {

    @И("'сервис авторизации' пользователь успешно авторизуется и добавляет токен к списку заголовков")
    public void auth() throws IOException {
        rqBody = LoginRq.builder().email("eve.holt@reqres.in").password("cityslicka").build();
        Call<LoginRs> call = loginService.login(headers, rqBody);
        request = call.request();
        response = call.execute();
        rsBody = response.body();
        requestHeaders = request.headers();
        responseHeaders = response.headers();
        token = rsBody.getToken();
        assertNotEquals(null, token);
        attachTextToAllure(request.toString());
        attachTextToAllure(response.toString(), rsBody.toString(), responseHeaders.toString());
        headers.put(authorizationHeaderName, tokenValuePrefix + token);
    }

}
