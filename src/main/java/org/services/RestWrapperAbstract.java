package org.services;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class RestWrapperAbstract {
    protected Retrofit readyRetrofit;
    protected static int READ_TIMEOUT = 60;
    protected static int CONNECT_TIMEOUT = 60;
    /*
    Services
    */

    /**
     * Default constructor.
     * It initializes all services before tests
     */
    public RestWrapperAbstract(String authToken, String BASE_URL) {
        readyRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getPreparedHttpClient(addAdditionalHeader(authToken))).build();
    }

    /**
     * Preparing prior OkHttpclient with timeouts for first authorization
     */
    protected static OkHttpClient getDefaultOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Preparing prior Retrofit for first authorization
     */
    protected static Retrofit getDefaultRetrofit(String BASE_URL) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getDefaultOkHttpClient()).build();
    }

    /**
     * Preparing ready OkHttpclient with timeouts and additional headers that will being using in auth
     */
    protected OkHttpClient getPreparedHttpClient(Interceptor interceptor) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        return okHttpClient;
    }

    /**
     * Add additional header to Request
     */
    protected Interceptor addAdditionalHeader(String token) {
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
}
