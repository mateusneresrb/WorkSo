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
 * [ BATCH ALGORITHM ]
 * Shortest Job First
 * */
public class Sjf extends Algorithm {


    public Sjf(AlgorithmType algorithmType, SystemType systemType, List<Process> processList) {
        super(algorithmType, systemType, processList);
    }

    public void runAlgorithm(boolean steps) {
        List<Process> processList = getProcessList();
        processList.sort(Comparator.comparingInt(Process::getTimeExecution));

        int timeAllProcess = processList.stream().map(Process::getTimeExecution).reduce(0, Integer::sum);

        List<Process> processListReady = new ArrayList<>();
        List<Process> processListFinished = new ArrayList<>();
        Process processExec = null; //PROCESSO EM EXECUÇÃO


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
                        processListReady.sort(Comparator.comparingInt(Process::getTimeExecution));
                        continue;
                    }

                    /* NÃO TEM NENHUM PROCESSO EM EXECUCAO */
                    if (processExec == null) {
                        if (processListReady.isEmpty()) continue;

                        processExec = processListReady.get(0);
                        processExec.setTimeReady(time);

                        processListReady.remove(0);
                        continue;
                    }

                    /* ACABOU O TEMPO DE EXECUCAO DO PROCESSO */
                    if ((processExec.getTimeReady() + processExec.getTimeExecution()) == time) {
                        scanner.nextLine();
                        processListFinished.add(processExec);

                        stringBuilder.append("--").append(processExec.getProcessName()).append("--").append(processExec.getTimeReady() + processExec.getTimeExecution());
                        Logger.info(stringBuilder.toString());
                        processExec = null;
                    }
                }
            }

            int totalExecutionTime = processListFinished.stream().map(process -> (process.getTimeReady() + process.getTimeExecution())).reduce(0, Integer::sum);

            int tempoMedioRetorno = totalExecutionTime / getProcessList().size();
            int tempoMedioRetornoSeg = tempoMedioRetorno / 1000;
            int tempoMedioRetornoMin = tempoMedioRetornoSeg / 60;

            Logger.info("TEMPO MÉDIO DE RETORNO:");
            Logger.info(tempoMedioRetorno + "ms->" + tempoMedioRetornoSeg + "s->" + tempoMedioRetornoMin + "m");
            return;
        }

        /* O PROXIMO DA FILE É BASEADO PELO TEMPO DE EXECUCAO */

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
                    processListReady.sort(Comparator.comparingInt(Process::getTimeExecution));
                    continue;
                }

                /* NÃO TEM NENHUM PROCESSO EM EXECUCAO */
                if (processExec == null) {
                    if (processListReady.isEmpty()) continue;

                    processExec = processListReady.get(0);
                    processExec.setTimeReady(time);

                    processListReady.remove(0);
                    continue;
                }

                /* ACABOU O TEMPO DE EXECUCAO DO PROCESSO */
                if ((processExec.getTimeReady() + processExec.getTimeExecution()) == time) {

                    processListFinished.add(processExec);

                    stringBuilder.append("--").append(processExec.getProcessName()).append("--").append(processExec.getTimeReady() + processExec.getTimeExecution());
                    processExec = null;
                }
            }
        }

        Logger.info(stringBuilder.toString());

        int totalExecutionTime = processListFinished.stream().map(process -> (process.getTimeReady() + process.getTimeExecution())).reduce(0, Integer::sum);

        int tempoMedioRetorno = totalExecutionTime / getProcessList().size();
        int tempoMedioRetornoSeg = tempoMedioRetorno / 1000;
        int tempoMedioRetornoMin = tempoMedioRetornoSeg / 60;

        Logger.info("TEMPO MÉDIO DE RETORNO:");
        Logger.info(tempoMedioRetorno + "ms->" + tempoMedioRetornoSeg + "s->" + tempoMedioRetornoMin + "m");
    }

}
