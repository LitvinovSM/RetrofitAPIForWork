package mainLogic.servicesAndSteps.loginService;

import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import okhttp3.Headers;
import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import mainLogic.servicesAndSteps.loginService.api.LoginService;
import retrofit2.Call;

import java.io.IOException;

public class LoginServiceSteps extends RestWrapperLoginService{

    @И("'сервис авторизации' пользователь успешно авторизуется и добавляет токен к списку заголовков")
    public void auth() throws IOException {
        rqBody = LoginRq.builder().email("eve.holt@reqres.in").password("cityslicka").build();
        Call<LoginRs> call = loginService.login(headers,rqBody);
        request = call.request();
        response = call.execute();
        rsBody = response.body();
        Headers requestHeaders = request.headers();
        Headers responseHeaders = response.headers();
        token = rsBody.getToken();
        System.out.println(String.format("Запрос %s",request));
        System.out.println(String.format("Ответ %s",response));
        System.out.println(String.format("Хидеры запроса %s",requestHeaders));
        System.out.println(String.format("Хидеры ответа %s",responseHeaders));
        System.out.println(String.format("Токен ответа %s",token));
        headers.put(authorizationHeaderName,tokenValuePrefix+token);
    }

}
