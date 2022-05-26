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

        int pressEnter = 1;
        List<Process> processList = getProcessList();
        int[] timeExecution = new int[processList.size() + 1];
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

            Scanner scanner = new Scanner(System.in);
            Logger.info("0"); //DE ONDE VEM O NUMERO 0 DO EXEMPLO;

            do {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("0");

                int x = 0;
                for (Process process : getProcessList()) {
                    if (x >= pressEnter) break;
                    stringBuilder.append("--").append(process.getProcessName()).append("--").append(process.getTimeExecution() + timeExecution[x]);

                    timeExecution[pressEnter] = process.getTimeExecution() + timeExecution[(pressEnter - 1)];
                    x++;
                }

                scanner.nextLine();
                pressEnter++;
                Logger.info(stringBuilder.toString());
            } while (pressEnter <= getProcessList().size());

            int totalExecutionTime = 0;
            for (int i : timeExecution) {
                totalExecutionTime += i;
            }

            int tempoMedioRetorno = totalExecutionTime / getProcessList().size();
            int tempoMedioRetornoSeg = tempoMedioRetorno / 1000;
            int tempoMedioRetornoMin = tempoMedioRetornoSeg / 60;

            Logger.info("TEMPO MÉDIO DE RETORNO:");
            Logger.info(tempoMedioRetorno + "ms->" + tempoMedioRetornoSeg + "s->" + tempoMedioRetornoMin + "m");
            return;
        }


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0");

        int count = 1;
        for (Process process : getProcessList()) {
            stringBuilder.append("--").append(process.getProcessName()).append("--").append(process.getTimeExecution() + timeExecution[count - 1]);
            timeExecution[count] = process.getTimeExecution() + timeExecution[count - 1];
            count++;
        }

        Logger.info(stringBuilder.toString());

        int totalExecutionTime = 0;
        for (int i : timeExecution) {
            totalExecutionTime += i;
        }

        int tempoMedioRetorno = totalExecutionTime / getProcessList().size();
        int tempoMedioRetornoSeg = tempoMedioRetorno / 1000;
        int tempoMedioRetornoMin = tempoMedioRetornoSeg / 60;

        Logger.info("TEMPO MÉDIO DE RETORNO:");
        Logger.info(tempoMedioRetorno + "ms->" + tempoMedioRetornoSeg + "s->" + tempoMedioRetornoMin + "m");
    }

}
