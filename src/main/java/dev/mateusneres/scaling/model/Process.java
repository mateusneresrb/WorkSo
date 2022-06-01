package dev.mateusneres.scaling.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Process {

    private final String username;
    private final String processName;
    private final int processID;
    private final int priority;
    private final int timeSubmission;
    private int timeToPerform; //Tempo que entrou para executar
    private int executionDuration; //Tempo que o processo passou executando;
    private final int timeFinalExecution; //Tempo final de execucao do processo


}
