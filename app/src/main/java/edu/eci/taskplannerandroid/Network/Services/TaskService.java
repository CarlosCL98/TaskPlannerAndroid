package edu.eci.taskplannerandroid.Network.Services;

import java.util.List;

import edu.eci.taskplannerandroid.Network.Data.Task;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskService {

    @GET("taskPlanner/v1/tasks")
    Call<List<Task>> getTasks();
}
