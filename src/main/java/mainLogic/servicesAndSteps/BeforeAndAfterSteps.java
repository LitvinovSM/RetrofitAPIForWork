package mainLogic.servicesAndSteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static mainLogic.servicesAndSteps.RestWrapperAbstract.getDefaultOkHttpClient;
import static mainLogic.servicesAndSteps.RestWrapperAbstract.setReadyRetrofit;


public class BeforeAndAfterSteps {

    public static Retrofit readyRetrofit;
    public static OkHttpClient okHttpClient;

    @Before
    public static void setUp() {
        okHttpClient = getDefaultOkHttpClient();
        readyRetrofit = setReadyRetrofit(okHttpClient);

    }

    @After
    public static void tearDown() {

    }
}
