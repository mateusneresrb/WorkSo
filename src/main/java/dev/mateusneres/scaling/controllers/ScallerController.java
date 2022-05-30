package dev.mateusneres.scaling.controllers;

import dev.mateusneres.scaling.model.Process;
import dev.mateusneres.scaling.model.Scaller;
import dev.mateusneres.scaling.model.algorithm.*;
import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ScallerController {

    private Scaller scaller;
    private AlgorithmType algorithmType;
    private SystemType systemType;
    private int quantum;
    private List<Process> processList;


    public void start() {
        /* BATCH */
        if (algorithmType == AlgorithmType.FIFO) {
            Fifo fifo = new Fifo(algorithmType, systemType, processList);
            fifo.runAlgorithm(scaller.isSteps());
            return;
        }

        if (algorithmType == AlgorithmType.SJF) {
            Sjf sjf = new Sjf(algorithmType, systemType, processList);
            sjf.runAlgorithm(scaller.isSteps());
            return;
        }

        /* INTERACTIVE */
        if (algorithmType == AlgorithmType.ROUNDROBIN) {
            RoundRobin roundRobin = new RoundRobin(algorithmType, systemType, processList);
            roundRobin.setQuantum(quantum);

            return;
        }

        if (algorithmType == AlgorithmType.LOTERIA) {
            Lottery lottery = new Lottery(algorithmType, systemType, processList);
            lottery.setQuantum(quantum);

            return;
        }

        Guaranteed guaranteed = new Guaranteed(algorithmType, systemType, processList);
        guaranteed.setQuantum(quantum);


    }

}
