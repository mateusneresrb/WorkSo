package dev.mateusneres.scaling.model;

import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import dev.mateusneres.scaling.utils.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Algorithm {

    private AlgorithmType algorithmType;
    private SystemType systemType;
    private List<Process> processList;

    public void headerAlgoritghm() {
        Logger.info("-------------------------------------------");
        Logger.info(" SISTEMA " + getSystemType().name());
        Logger.info(" ALGORITMO " + getAlgorithmType().name());
        Logger.info(" Nº PROCESSOS: " + getProcessList().size());
        Logger.info("-------------------------------------------");
    }

}
