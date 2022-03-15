package com.todo.rabbitmq.domain;


public class ImpServiceDTO {

    private String userId;
    private String taskId;

    public ImpServiceDTO() {}

    public ImpServiceDTO(String userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "ImpServiceDTO{" +
                "userId='" + userId + '\'' +
                ", taskId=" + taskId +
                '}';
    }
}
