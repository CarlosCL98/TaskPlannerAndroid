package edu.eci.taskplannerandroid.Network.Services;

import edu.eci.taskplannerandroid.Network.Data.Token;
import edu.eci.taskplannerandroid.Network.Data.LoginWrapper;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("taskPlanner/v1/user/login")
    Call<Token> loginUser(@Body LoginWrapper userLogin);
}
