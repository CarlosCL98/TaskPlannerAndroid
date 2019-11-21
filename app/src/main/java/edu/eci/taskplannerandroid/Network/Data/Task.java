package edu.eci.taskplannerandroid.Network.Data;

public class Task {

    private int id;
    private String title;
    private String description;
    private String dueDate;
    private Status status;
    private User responsible;
    private String fileUrl;

    public Task() {
    }

    public Task(int id, String title, String description, String dueDate, Status status, User responsible, String fileUrl) {
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
                "Customer[id=%s, title='%s', description='%s', dueDate='%s', status='%s', responsible='%s', fileUrl='%s']",
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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
