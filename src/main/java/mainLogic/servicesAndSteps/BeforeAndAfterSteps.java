package mainLogic.servicesAndSteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import retrofit2.Retrofit;

import static mainLogic.servicesAndSteps.RestWrapperAbstract.setReadyRetrofit;


public class BeforeAndAfterSteps{

    public static Retrofit readyRetrofit;

    @Before
    public static void setUp(){
        readyRetrofit = setReadyRetrofit("https://reqres.in/api/");
    }

    @After
    public static void tearDown(){

    }
}
