package edu.eci.taskplannerandroid.UI.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.eci.taskplannerandroid.Network.Data.Token;
import edu.eci.taskplannerandroid.Network.Data.LoginWrapper;
import edu.eci.taskplannerandroid.Network.RetrofitNetwork;
import edu.eci.taskplannerandroid.R;
import edu.eci.taskplannerandroid.Storage.Storage;
import edu.eci.taskplannerandroid.UI.Utils.StringUtils;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private final RetrofitNetwork retrofitNetwork = new RetrofitNetwork();
    private Storage storage;
    private EditText email;
    private EditText password;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        storage = new Storage(this);
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        loading = findViewById(R.id.loading);
    }

    public void loginUser(final View view) {
        loading(true);
        final LoginWrapper loginWrapper = checkInputFields();
        final LoginActivity self = this;
        if (loginWrapper != null) {
            view.setEnabled(false);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response<Token> response = retrofitNetwork.getAuthService()
                                .loginUser(loginWrapper)
                                .execute();
                        if (response.isSuccessful()) {
                            Token authToken = response.body();
                            storage.saveToken(authToken);
                            Intent intent = new Intent(self, MainActivity.class);
                            startActivity(intent);
                            loading(false);
                            finish();
                        } else {
                            showErrorMessages(view, "The user doesn't exists or you must verify your credentials.");
                            loading(false);
                        }
                    } catch (IOException ex) {
                        showErrorMessages(view, ex.getMessage());
                        loading(false);
                    }
                }
            });
        }

    }

    private LoginWrapper checkInputFields() {
        boolean isOk = true;
        final String emailText = email.getText().toString();
        final String passwordText = password.getText().toString();
        if (emailText.equals("")) {
            email.setError(getString(R.string.error_input_email_empty));
            isOk = isOk && false;
        } else {
            if (!StringUtils.isValidEmail(emailText)) {
                email.setError(getString(R.string.error_input_email_valid));
                isOk = isOk && false;
            } else {
                email.setError(null);
                if (passwordText.equals("")) {
                    password.setError(getString(R.string.error_input_password_empty));
                    isOk = isOk && false;
                } else {
                    password.setError(null);
                }
            }
        }
        LoginWrapper loginWrapper = null;
        if (isOk) {
            loginWrapper = new LoginWrapper(emailText, passwordText);
        }
        return loginWrapper;
    }

    private void showErrorMessages(final View view, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void loading(boolean isLoading) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isLoading) {
                    loading.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.GONE);
                }
            }
        });
    }
}
