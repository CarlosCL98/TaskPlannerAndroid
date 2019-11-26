package edu.eci.taskplannerandroid.Network.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.eci.taskplannerandroid.Network.Data.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getUsers();

    @Query("SELECT * FROM user_table WHERE email = :email")
    LiveData<User> getUserByEmail(String email);
}
