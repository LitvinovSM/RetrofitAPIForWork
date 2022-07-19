package org.services;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.DTO.login.LoginRq;
import org.DTO.login.LoginRs;
import org.services.serviceA.LoginService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RestWrapper {

    private Retrofit readyRetrofit;
    private static final int READ_TIMEOUT = 60;
    private static final int CONNECT_TIMEOUT = 60;

    private static final String BASE_URL = "https://reqres.in/api/";
    /*
    Services
    */
    public LoginService loginService;
    public UserService userService;


    /**
     * Default constructor.
     * It initializes all services before tests
     */
    public RestWrapper(String token) {
        super();
        readyRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getPreparedHttpClient(addAdditionalHeader(token))).build();
        userService = readyRetrofit.create(UserService.class);
        loginService = readyRetrofit.create(LoginService.class);

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
    public static RestWrapper loginAs(String login, String password) throws IOException {
        LoginRq rq = LoginRq.builder().email(login).password(password).build();
        LoginService defaultLoginService = getDefaultRetrofit().create(LoginService.class);
        Call<LoginRs> rsCall = defaultLoginService.login(rq);
        LoginRs body = rsCall.execute().body();
        String token = body.getToken();
        return new RestWrapper(token);
    }

}
