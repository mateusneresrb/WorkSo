package dev.mateusneres.scaling.model.algorithm;

import dev.mateusneres.scaling.model.Algorithm;
import dev.mateusneres.scaling.model.Process;
import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
 * INTERACTIVE ALGORITHM
 * */

@Getter
@Setter
public class Guaranteed extends Algorithm {

    private int quantum;

    public Guaranteed(AlgorithmType algorithmType, SystemType systemType, List<Process> processList) {
        super(algorithmType, systemType, processList);
    }

}
