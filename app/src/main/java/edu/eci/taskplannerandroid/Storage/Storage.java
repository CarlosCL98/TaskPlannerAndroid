package edu.eci.taskplannerandroid.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import edu.eci.taskplannerandroid.Network.Data.Token;
import edu.eci.taskplannerandroid.R;

public class Storage {

    private final String TOKEN_KEY = "TOKEN_KEY";

    private final SharedPreferences sharedPreferences;

    public Storage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public void saveToken(Token authToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, authToken.getAccessToken());
        editor.commit();
    }

    public String getToken() {
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

    public boolean containsToken() {
        return sharedPreferences.contains(TOKEN_KEY);
    }

    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(TOKEN_KEY);
        editor.commit();
    }
}
