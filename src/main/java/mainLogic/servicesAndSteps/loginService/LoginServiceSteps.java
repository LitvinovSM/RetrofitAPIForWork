package mainLogic.servicesAndSteps.loginService;

import io.cucumber.java.ru.И;
import mainLogic.DTO.loginService.LoginRq;
import mainLogic.DTO.loginService.LoginRs;
import retrofit2.Call;

import java.io.IOException;


public class LoginServiceSteps extends RestWrapperLoginService {

    @И("'сервис авторизации' пользователь успешно авторизуется c параметрами по умолчанию и добавляет токен к списку заголовков")
    public void auth() throws IOException {
        authAs(defaultLogin,defaultPassword);
        checkStatusCode(200);
        putTokenToHeadersList();
    }

    @И("'сервис авторизации' пользователь авторизуется под логином {string} и паролем {string}")
    public void authAs(String login, String password) throws IOException {
        rqBody = LoginRq.builder().email(login).password(password).build();
        Call<LoginRs> call = loginService.login(headers, rqBody);
        request = call.request();
        response = call.execute();
        attachTextToAllure("Запрос и ответ при авторизации",request.toString(),response.toString());
    }

    @И("'сервис авторизации' добавляет токен авторизации к списку заголовков")
    public void putTokenToHeadersList() {
        token = response.body().getToken();
        headers.put(authorizationHeaderName, tokenValuePrefix + token);
        attachTextToAllure("Список заголовков",headers.toString());
    }

    @И("'сервис авторизации' проверяет что статус код ответа равен {int}")
    public void checkStatusCode(int expectedStatusCode) {
        compareStatusCodes(response,expectedStatusCode);
        System.out.println(response.body());
    }

    @И("'сервис авторизации' проверяет что текст ошибки в теле сообщения равен {string}")
    public void compareErrorText(String expectedErrorText) throws IOException {
        compareErrorMessageText(response,expectedErrorText);
    }
}
