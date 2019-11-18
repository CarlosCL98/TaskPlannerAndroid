package edu.eci.taskplannerandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.eci.taskplannerandroid.Config.Token;
import edu.eci.taskplannerandroid.Model.LoginWrapper;
import edu.eci.taskplannerandroid.Services.AuthService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://taskplannerapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        authService = retrofit.create(AuthService.class);
    }

    public void loginUser(View view) {
        EditText editText = findViewById(R.id.editText);
        final String email = editText.getText().toString().trim();
        EditText editText2 = findViewById(R.id.editText2);
        final String password = editText2.getText().toString().trim();
        final LoginActivity self = this;
        if (email.equals("")) {
            editText.setError("You must enter your email.");
            return;
        }
        if (password.equals("")) {
            editText2.setError("You must enter your password.");
            return;
        }
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<Token> response = authService.loginUser(new LoginWrapper(email, password)).execute();
                    Token authToken = response.body();
                    if (authToken == null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView textViewErrorMessage = findViewById(R.id.textView4);
                                textViewErrorMessage.setText("The user does not exists or\nyou must verify the credentials.");
                            }
                        });
                        return;
                    }
                    SharedPreferences sharedPref =
                            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.token_key), authToken.getAccessToken());
                    editor.commit();
                    Intent intent = new Intent(self, MainActivity.class);
                    startActivity(intent);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
