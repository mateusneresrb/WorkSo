package dev.mateusneres.scaling.model;

import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Algorithm {

    private AlgorithmType algorithmType;
    private SystemType systemType;
    private List<Process> processList;

}
