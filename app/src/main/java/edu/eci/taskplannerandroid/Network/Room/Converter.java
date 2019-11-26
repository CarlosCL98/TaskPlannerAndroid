package edu.eci.taskplannerandroid.Network.Room;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.eci.taskplannerandroid.Network.Data.User;

import java.lang.reflect.Type;

public class Converter {

    @TypeConverter
    public static User fromString(String value) {
        Type type = new TypeToken<User>() {}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromUser(User user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        return json;
    }
}
