package mainLogic.servicesAndSteps;

import io.qameta.allure.Attachment;
import mainLogic.utils.configs.TestConfigFactory;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class RestWrapperAbstract {

    private static final TestConfigFactory config = TestConfigFactory.getInstance();

    private static final String BASE_URL = config.getGeneralConfig().getBaseURL();
    protected static int READ_TIMEOUT = config.getGeneralConfig().getREAD_TIMEOUT();
    protected static int CONNECT_TIMEOUT = config.getGeneralConfig().getCONNECT_TIMEOUT();
    public static Map<String, String> headers = new HashMap<>();
    protected Headers requestHeaders;
    protected Headers responseHeaders;
    /*
    Services
    */


    /**
     * Default constructor.
     * It initializes all services before tests
     */
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

    @Attachment(type = "application/json")
    public static String attachTextToAllure(String... attachedText) {
        String fullAttach = "";
        for (String attachment : attachedText) {
            fullAttach = fullAttach.concat(attachment).concat("\n\r");
        }
        return fullAttach;
    }
}

