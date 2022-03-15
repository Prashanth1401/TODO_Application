package com.todo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Archive {


    String taskId;
    String taskTitle;
    String taskDescription;
    boolean isImportant;
    String category;
    String targetDate;

}
