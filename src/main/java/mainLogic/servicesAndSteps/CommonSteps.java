package mainLogic.servicesAndSteps;

import io.cucumber.java.ru.И;

import static mainLogic.commonActions.DefaultActions.compareStatusCodes;

public class CommonSteps extends RestWrapperAbstract {

    @И("'универсальный шаг' добавляет к запросу заголовок {string} со значением {string}")
    public void addHeaderToRequest(String header, String value) {
        headers.put(header, value);
    }


}
