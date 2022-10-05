package mainLogic.servicesAndSteps;

import io.cucumber.java.ru.И;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.internal.http2.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static mainLogic.servicesAndSteps.BeforeAndAfterSteps.okHttpClient;
import static mainLogic.servicesAndSteps.BeforeAndAfterSteps.readyRetrofit;
import static mainLogic.servicesAndSteps.RestWrapperAbstract.*;

public class CommonSteps extends RestWrapperAbstract{

    @И("'универсальный шаг' добавляет к запросу заголовок {string} со значением {string}")
    public void addHeaderToRequest(String header, String value) {
        headers.put(header,value);
    }

}
