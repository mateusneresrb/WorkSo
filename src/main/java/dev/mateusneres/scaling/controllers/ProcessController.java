package dev.mateusneres.scaling.controllers;

import dev.mateusneres.scaling.model.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProcessController {

    public static List<Process> getProcess(Stream<String> fileLines) {
        List<Process> processList = new ArrayList<>();

        fileLines.forEach(line -> {
            if (line == null) return;
            String[] lineSplit = line.split(";");

            if (lineSplit.length != 6) return;

            String username = lineSplit[0];
            String processName = lineSplit[1];
            int processID;
            int priority;
            int timeSubmission;
            int timeExecution;

            try {
                processID = Integer.parseInt(lineSplit[2]);
                priority = Integer.parseInt(lineSplit[3]);
                timeSubmission = Integer.parseInt(lineSplit[4]);
                timeExecution = Integer.parseInt(lineSplit[5]);
            } catch (NumberFormatException e) {
                return;
            }

            if (priority < 0) priority = 0;
            if (priority > 100) priority = 100;

            processList.add(new Process(username, processName, processID, priority, timeSubmission, timeExecution));
        });

        return processList;
    }
}
