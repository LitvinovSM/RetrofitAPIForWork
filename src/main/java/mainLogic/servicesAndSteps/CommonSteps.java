package mainLogic.servicesAndSteps;

import io.cucumber.java.ru.И;

public class CommonSteps extends RestWrapperAbstract {

    @И("'универсальный шаг' добавляет к запросу заголовок {string} со значением {string}")
    public void addHeaderToRequest(String header, String value) {
        headers.put(header, value);
    }

}
