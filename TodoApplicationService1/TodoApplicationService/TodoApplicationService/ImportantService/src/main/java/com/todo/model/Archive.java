package com.todo.model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Archive {
    String taskId;
    String taskTitle;
    String taskDescription;
    boolean isImportant;
    String category;
    Date targetDate;

    public Archive(){}

    public Archive(String taskId, String taskTitle, String taskDescription, boolean isImportant, String category, String targetDate) throws ParseException {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.isImportant = isImportant;
        this.category = category;
        this.targetDate = new SimpleDateFormat("dd/MM/yyyy").parse(targetDate);
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) throws ParseException {
        this.targetDate = new SimpleDateFormat("dd/MM/yyyy").parse(targetDate);
    }

    @Override
    public String toString() {
        return "Archive{" +
                "taskId='" + taskId + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", isImportant=" + isImportant +
                ", category='" + category + '\'' +
                ", targetDate=" + targetDate +
                '}';
    }
}
