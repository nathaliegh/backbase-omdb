package com.backbase.omdb.client.security;


import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author NG
 * @version 0.0.1
 * <p>
 * OMDB interceptors clients
 */
public class OMDBClientInstance {

    private static final OkHttpClient httpClient = getHttpClientInterceptor();

    private static Retrofit retrofit;

    private static Retrofit retrofitImg;


    /**
     * get retrofit instance
     *
     * @return
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(APIConstant.API_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    /**
     * get retrofit image instance
     *
     * @return
     */
    public static Retrofit getRetrofitImgInstance() {
        if (retrofitImg == null) {
            retrofitImg = new retrofit2.Retrofit.Builder()
                    .baseUrl(APIConstant.IMG_API_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitImg;
    }

    /**
     * get http client interceptor
     *
     * @return OkHttpClient
     */
    public static OkHttpClient getHttpClientInterceptor() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                var originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", APIConstant.API_KEY)
                        .build();
                // Request customization: add request headers
                var requestBuilder = original.newBuilder().url(url);
                var request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.build();
    }

    private class APIConstant {

        static final String API_KEY = "25ca3eb4";

        static final String API_URL = "http://www.omdbapi.com/";

        static final String IMG_API_URL = "http://img.omdbapi.com/";
    }
}
