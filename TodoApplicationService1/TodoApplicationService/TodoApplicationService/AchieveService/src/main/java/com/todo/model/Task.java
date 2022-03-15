package com.todo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Task {


    String taskId;
    String taskTitle;
    String taskDescription;
    boolean isImportant;
    String category;
    String targetDate;

}
