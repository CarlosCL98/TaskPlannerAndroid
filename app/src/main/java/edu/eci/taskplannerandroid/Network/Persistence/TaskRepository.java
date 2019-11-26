package edu.eci.taskplannerandroid.Network.Persistence;

import android.app.Application;
import androidx.lifecycle.LiveData;
import edu.eci.taskplannerandroid.Network.Dao.TaskDao;
import edu.eci.taskplannerandroid.Network.Data.Task;
import edu.eci.taskplannerandroid.Network.Room.TaskPlannerRoomDatabase;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
        TaskPlannerRoomDatabase db = TaskPlannerRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        TaskPlannerRoomDatabase.databaseWriteExecutor.execute(() -> {
            taskDao.insert(task);
        });
    }
}
