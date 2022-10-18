package mainLogic.servicesAndSteps;

import io.qameta.allure.Attachment;
import mainLogic.commonActions.DefaultActions;
import mainLogic.utils.configs.MainConf;
import mainLogic.utils.configs.StandConfig;
import mainLogic.utils.configs.TestConfigFactory;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class RestWrapperAbstract implements DefaultActions {
//Часть для реальной работы
    protected static final TestConfigFactory configFactory = TestConfigFactory.getInstance();
    protected static final MainConf mainConf = configFactory.getMainConfig();
    protected static final StandConfig standConfig = configFactory.getStandConfig();
    protected static final String BASE_URL = standConfig.getBaseURL();
    protected static int READ_TIMEOUT = mainConf.getREAD_TIMEOUT();
    protected static int CONNECT_TIMEOUT = mainConf.getCONNECT_TIMEOUT();
    public static Map<String, String> headers = new HashMap<>();
    public static Map<String, String> queryParams = new HashMap<>();
    public Request request;


    public static Retrofit setReadyRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient).build();
    }

    /**
     * Preparing prior OkHttpclient with timeouts for first authorization
     */
    public static OkHttpClient getDefaultOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    @Attachment(value = "{0}",type = "application/json")
    public static String attachTextToAllure(String reason, String... attachedText) {
        String fullAttach = "";
        for (String attachment : attachedText) {
            fullAttach = fullAttach.concat(attachment).concat("\n\r");
        }
        return fullAttach;
    }
}

