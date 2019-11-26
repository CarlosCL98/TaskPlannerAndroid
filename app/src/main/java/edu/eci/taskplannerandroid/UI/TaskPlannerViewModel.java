package edu.eci.taskplannerandroid.UI;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.eci.taskplannerandroid.Network.Data.Task;
import edu.eci.taskplannerandroid.Network.Data.User;
import edu.eci.taskplannerandroid.Network.Persistence.TaskRepository;
import edu.eci.taskplannerandroid.Network.Persistence.UserRepository;

public class TaskPlannerViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Task>> allTasks;

    public TaskPlannerViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
        allTasks = taskRepository.getAllTasks();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insert(Task task) {
        taskRepository.insert(task);
    }
}
