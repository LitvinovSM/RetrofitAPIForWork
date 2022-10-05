package mainLogic.servicesAndSteps;

import okhttp3.*;
import okhttp3.internal.http2.Header;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class RestWrapperAbstract {
    protected static int READ_TIMEOUT = 60;
    protected static int CONNECT_TIMEOUT = 60;
    public static Map<String, String> headers = new HashMap<>();
    /*
    Services
    */


    /**
     * Default constructor.
     * It initializes all services before tests
     */
    public static Retrofit setReadyRetrofit(OkHttpClient httpClient) {
        return new Retrofit.Builder().baseUrl("https://reqres.in/api/")
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

    /**
     * Preparing ready OkHttpclient with timeouts and additional headers that will being using in auth
     */
    public static OkHttpClient getPreparedHttpClient(Interceptor interceptor) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        return okHttpClient;
    }

    /**
     * Preparing prior Retrofit for first authorization
     */
    protected static Retrofit setDefaultRetrofit(String BASE_URL) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getDefaultOkHttpClient()).build();
    }


//    /**
//     * Add additional header to Request
//     */
//    protected Interceptor addAuthHeader(String token) {
//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest = chain.request()
//                        .newBuilder()
//                        .addHeader("Authorization", "Bearer" + token)
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        };
//        return interceptor;
//    }
//
//    public static Interceptor addAdditionalHeader(String header, String value) {
//
//
//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest = chain.request()
//                        .newBuilder()
//                        .addHeader(header, value)
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        };
//        return interceptor;
//    }
//
//    public static Interceptor addListHeaders(Map<String, String> headers) {
//        Headers headersBuild = Headers.of(headers);
//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest = chain.request()
//                        .newBuilder().url("https://reqres.in/api/")
//                        .headers(headersBuild)
//                        .build();
//                return chain.proceed(newRequest);
//            }
//        };
//        return interceptor;
//    }
}

