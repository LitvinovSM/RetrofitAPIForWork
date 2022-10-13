package mainLogic.servicesAndSteps;

import io.cucumber.java.ru.И;
import retrofit2.Response;

public class CommonSteps extends RestWrapperAbstract{

    @И("'универсальный шаг' добавляет к запросу заголовок {string} со значением {string}")
    public void addHeaderToRequest(String header, String value) {
        headers.put(header, value);
    }

    @И("'универсальный шаг' проверяет что статус код ответа равен {int}")
    public void checkStatusCode(int expectedStatusCode) {
        Response<?> response = storedValues.get(RESPONSE_KEY);
        compareStatusCodes(response,expectedStatusCode);
    }
}
