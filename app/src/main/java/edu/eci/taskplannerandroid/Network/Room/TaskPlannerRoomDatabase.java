package edu.eci.taskplannerandroid.Network.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.TypeConverters;
import edu.eci.taskplannerandroid.Network.Dao.TaskDao;
import edu.eci.taskplannerandroid.Network.Dao.UserDao;
import edu.eci.taskplannerandroid.Network.Data.Task;
import edu.eci.taskplannerandroid.Network.Data.User;

@Database(entities = {User.class, Task.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class TaskPlannerRoomDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract TaskDao taskDao();

    private static volatile TaskPlannerRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TaskPlannerRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskPlannerRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskPlannerRoomDatabase.class, "taskPlanner_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
