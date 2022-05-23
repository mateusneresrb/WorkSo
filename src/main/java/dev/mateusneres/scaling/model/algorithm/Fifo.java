package dev.mateusneres.scaling.model.algorithm;

import dev.mateusneres.scaling.model.Algorithm;
import dev.mateusneres.scaling.model.Process;
import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;

import java.util.List;

/*
 * BATCH ALGORITHM
 * */
public class Fifo extends Algorithm {

    public Fifo(AlgorithmType algorithmType, SystemType systemType, List<Process> processList) {
        super(algorithmType, systemType, processList);
    }


    /*
     * FIFO ALGORITHM
     *
     * */
}
