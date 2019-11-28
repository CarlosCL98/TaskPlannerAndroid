package edu.eci.taskplannerandroid.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.eci.taskplannerandroid.Network.Data.Task;
import edu.eci.taskplannerandroid.R;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> taskList = null;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());
        holder.status.setText(task.getStatus());
        holder.dueDate.setText(task.getDueDate());
        holder.responsible_email.setText(task.getResponsible().getEmail());
    }

    @Override
    public int getItemCount() {
        return taskList != null ? taskList.size() : 0;
    }

    public void updateTasks(List<Task> tasks){
        this.taskList = tasks;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final TextView status;
        private final TextView dueDate;
        private final TextView responsible_email;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.taskTitle);
            description = itemView.findViewById(R.id.taskDescription);
            status = itemView.findViewById(R.id.taskStatus);
            dueDate = itemView.findViewById(R.id.taskDueDate);
            responsible_email = itemView.findViewById(R.id.taskResponsible);
        }
    }
}
