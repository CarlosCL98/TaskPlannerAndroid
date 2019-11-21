package edu.eci.taskplannerandroid.Network;

import java.io.IOException;

import edu.eci.taskplannerandroid.Network.Services.AuthService;
import edu.eci.taskplannerandroid.Network.Services.TaskService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetwork {

    private final String BASE_URL = "https://taskplannerapi.herokuapp.com/";
    private AuthService authService;
    private TaskService taskService;

    public RetrofitNetwork() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        authService = retrofit.create(AuthService.class);
    }

    public RetrofitNetwork(final String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization","Bearer " + token)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        taskService = retrofit.create(TaskService.class);
    }

    public AuthService getAuthService() {
        return authService;
    }

    public TaskService getTaskService() {
        return taskService;
    }
}
