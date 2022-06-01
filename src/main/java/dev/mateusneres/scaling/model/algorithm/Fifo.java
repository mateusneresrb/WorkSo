package dev.mateusneres.scaling.model.algorithm;

import dev.mateusneres.scaling.model.Algorithm;
import dev.mateusneres.scaling.model.Process;
import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import dev.mateusneres.scaling.utils.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * BATCH ALGORITHM
 * */
public class Fifo extends Algorithm {

    public Fifo(AlgorithmType algorithmType, SystemType systemType, List<Process> processList) {
        super(algorithmType, systemType, processList);
    }

    public void runAlgorithm(boolean steps) {
        List<Process> processList = getProcessList();
        List<Process> processListReady = new ArrayList<>();
        List<Process> processListFinished = new ArrayList<>();

        Process processExec = null; //PROCESSO EM EXECUÇÃO

        processList.sort(Comparator.comparingInt(Process::getTimeSubmission));

        int timeAllProcess = processList.stream().map(Process::getTimeFinalExecution).reduce(0, Integer::sum);

        Logger.info("RESULTADO:");
        Logger.info("SISTEMA EM LOTES");
        Logger.info("ESCALONAMENTO FIFO\n");

        if (steps) {
            Logger.info("TEMPO DE SUBMISSÃO:");
            getProcessList().forEach(process -> Logger.info(process.getProcessName() + "=" + process.getTimeSubmission() + "ms"));

            Logger.info("TEMPO DE EXECUCAO:");
            getProcessList().forEach(process -> Logger.info(process.getProcessName() + "=" + process.getTimeFinalExecution() + "ms"));

            Logger.info("ORDEM DE EXECUCAO:");
            Logger.info(getProcessList().stream().map(Process::getProcessName).collect(Collectors.joining("->")));
        }

        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0");

        int time = -1;
        while (time < timeAllProcess) {
            time++;

            for (Process process : getProcessList()) {

                /*CHEGOU NO TEMPO DE ENTRADA DE ALGUM PROCESSO*/
                if (process.getTimeSubmission() == time) {
                    if (processExec == null) {
                        processExec = process;
                        continue;
                    }

                    processListReady.add(process);
                    continue;
                }

                /* NÃO TEM NENHUM PROCESSO EM EXECUCAO */
                if (processExec == null) {
                    if (processListReady.isEmpty()) continue;

                    processExec = processListReady.get(0);
                    processExec.setTimeToPerform(time);

                    processListReady.remove(0);
                    continue;
                }

                /* ACABOU O TEMPO DE EXECUCAO DO PROCESSO */
                if ((processExec.getTimeToPerform() + processExec.getTimeFinalExecution()) == time) {
                    processListFinished.add(processExec);

                    stringBuilder.append("--").append(processExec.getProcessName()).append("--").append(processExec.getTimeToPerform() + processExec.getTimeFinalExecution());

                    if (steps) {
                        scanner.nextLine();
                        Logger.info(stringBuilder.toString());
                    }

                    processExec = null;
                }
            }
        }

        if (!steps) {
            Logger.info(stringBuilder.toString());
        }

        int totalExecutionTime = processListFinished.stream().map(process -> (process.getTimeToPerform() + process.getTimeFinalExecution())).reduce(0, Integer::sum);

        int tempoMedioRetorno = totalExecutionTime / getProcessList().size();
        int tempoMedioRetornoSeg = tempoMedioRetorno / 1000;
        int tempoMedioRetornoMin = tempoMedioRetornoSeg / 60;

        Logger.info("TEMPO MÉDIO DE RETORNO:");
        Logger.info(tempoMedioRetorno + "ms->" + tempoMedioRetornoSeg + "s->" + tempoMedioRetornoMin + "m");
    }
    
}
