package dev.mateusneres.scaling.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Process {

    private String username;
    private String processName;
    private int processID;
    private int priority;
    private int timeSubmission;
    private int timeExecution;

}
