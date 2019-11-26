package edu.eci.taskplannerandroid.Network.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "dueDate")
    private String dueDate;

    @NonNull
    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "responsible")
    private User responsible;

    @ColumnInfo(name = "fileUrl")
    private String fileUrl;

    @Ignore
    public Task() {
    }

    public Task(int id, String title, String description, String dueDate, String status, User responsible, String fileUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.responsible = responsible;
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return String.format(
                "Task[id=%s, title='%s', description='%s', dueDate='%s', status='%s', responsible='%s', fileUrl='%s']",
                id, title, description, dueDate, status, responsible, fileUrl);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
