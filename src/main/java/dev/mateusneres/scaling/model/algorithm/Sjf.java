package dev.mateusneres.scaling.model.algorithm;

import dev.mateusneres.scaling.model.Algorithm;
import dev.mateusneres.scaling.model.Process;
import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import dev.mateusneres.scaling.utils.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * [ BATCH ALGORITHM ]
 * Shortest Job First
 * */
public class Sjf extends Algorithm {


    public Sjf(AlgorithmType algorithmType, SystemType systemType, List<Process> processList) {
        super(algorithmType, systemType, processList);
    }

    public void runAlgorithm(boolean steps) {
        headerAlgoritghm();

        int timeExecution = 0;
        int pressEnter = 0;
        List<Process> processList = getProcessList();
        processList.sort(Comparator.comparingInt(Process::getTimeExecution));

        Logger.info("RESULTADO:");
        Logger.info("SISTEMA EM LOTES");
        Logger.info("ESCALONAMENTO TAREFA MAIS CURTA PRIMEIRO\n");

        if (steps) {
            Logger.info("TEMPO DE SUBMISSÃO:");
            getProcessList().forEach(process -> Logger.info(process.getProcessName() + "=" + process.getTimeSubmission() + "ms"));

            Logger.info("TEMPO DE EXECUCAO:");
            getProcessList().forEach(process -> Logger.info(process.getProcessName() + "=" + process.getTimeExecution() + "ms"));

            Logger.info("ORDEM DE EXECUCAO:");
            Logger.info(getProcessList().stream().map(Process::getProcessName).collect(Collectors.joining("->")));

            //DE ONDE VEM O NUMERO 0 DO EXEMPLO;
            Scanner scanner = new Scanner(System.in);
            Logger.info("0");

            do {
                scanner.nextLine();
                pressEnter++;

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("0");
                for (int i = 0; i < pressEnter; i++) {
                    stringBuilder.append("--").append(getProcessList().get(i).getProcessName()).append("--").append(getProcessList().get(i).getTimeExecution() + timeExecution);
                    timeExecution += getProcessList().get(i).getTimeExecution();
                }
                Logger.info(stringBuilder.toString());
            } while (pressEnter < getProcessList().size());

            int tempoMedioRetorno = timeExecution / getProcessList().size();
            int tempoMedioRetornoSeg = tempoMedioRetorno / 1000;
            int tempoMedioRetornoMin = tempoMedioRetornoSeg / 60;

            Logger.info("TEMPO MÉDIO DE RETORNO:");
            Logger.info(tempoMedioRetorno + "ms->" + tempoMedioRetornoSeg + "s->" + tempoMedioRetornoMin + "m");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0");

        for (Process process : getProcessList()) {
            stringBuilder.append("--").append(process.getProcessName()).append("--").append(process.getTimeExecution() + timeExecution);
            timeExecution += process.getTimeExecution();
        }

        Logger.info(stringBuilder.toString());

        int tempoMedioRetorno = timeExecution / getProcessList().size();
        int tempoMedioRetornoSeg = tempoMedioRetorno / 1000;
        int tempoMedioRetornoMin = tempoMedioRetornoSeg / 60;

        /* CORRIGIR TEMPO MEDIO DE RETORNO */
        Logger.info("TEMPO MÉDIO DE RETORNO:");
        Logger.info(tempoMedioRetorno + "ms->" + tempoMedioRetornoSeg + "s->" + tempoMedioRetornoMin + "m");
    }

}
