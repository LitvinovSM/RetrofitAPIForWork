package org.services.serviceA;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.DTO.login.LoginRq;
import org.DTO.login.LoginRs;
import org.services.RestWrapper;
import org.services.UserService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class RestWrapperAbstract {
    private Retrofit readyRetrofit;
    private static int READ_TIMEOUT = 60;
    private static int CONNECT_TIMEOUT = 60;
    public static String BASE_URL;
    /*
    Services
    */

    /**
     * Default constructor.
     * It initializes all services before tests
     */
    public RestWrapperAbstract(String AuthToken, String BASE_URL) {
        super();
        readyRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getPreparedHttpClient(addAdditionalHeader(AuthToken))).build();
    }
    /**
     * Preparing prior OkHttpclient with timeouts for first authorization
     */
    private static OkHttpClient getDefaultOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Preparing prior Retrofit for first authorization
     */
    private static Retrofit getDefaultRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getDefaultOkHttpClient()).build();
    }

    /**
     * Preparing ready OkHttpclient with timeouts and additional headers that will being using in auth
     */
    private OkHttpClient getPreparedHttpClient(Interceptor interceptor) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        return okHttpClient;
    }

    /**
     *Add additional header to Request */
    private Interceptor addAdditionalHeader(String token) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer" + token)
                        .build();
                return chain.proceed(newRequest);
            }
        };
        return interceptor;
    }

    /**
     * Method for creating different APIs with different permissions*/
    public static RestWrapperAbstract loginAs(String login, String password) throws IOException {
        LoginRq rq = LoginRq.builder().email(login).password(password).build();
        LoginService defaultLoginService = getDefaultRetrofit().create(LoginService.class);
        Call<LoginRs> rsCall = defaultLoginService.login(rq);
        LoginRs body = rsCall.execute().body();
        String token = body.getToken();
        return new RestWrapperAbstract(token);
    }

}
