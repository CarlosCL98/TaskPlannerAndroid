package edu.eci.taskplannerandroid.Network.Persistence;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.eci.taskplannerandroid.Network.Dao.UserDao;
import edu.eci.taskplannerandroid.Network.Data.User;
import edu.eci.taskplannerandroid.Network.Room.TaskPlannerRoomDatabase;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<User> user;

    public UserRepository(Application application) {
        TaskPlannerRoomDatabase db = TaskPlannerRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        allUsers = userDao.getUsers();
        user = userDao.getUserByEmail("c@m.com"); //Example
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    
    public LiveData<User> getUser() {
        return user;
    }

    public void insert(User user) {
        TaskPlannerRoomDatabase.databaseWriteExecutor.execute(() -> {
            userDao.insert(user);
        });
    }
}
