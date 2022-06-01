package dev.mateusneres.scaling.model.algorithm;

import dev.mateusneres.scaling.model.Algorithm;
import dev.mateusneres.scaling.model.Process;
import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;
import dev.mateusneres.scaling.utils.Logger;
import dev.mateusneres.scaling.utils.TableBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/*
 * INTERACTIVE ALGORITHM
 * */
@Getter
@Setter
public class Lottery extends Algorithm {

    private int quantum;

    public Lottery(AlgorithmType algorithmType, SystemType systemType, List<Process> processList) {
        super(algorithmType, systemType, processList);
    }


    public void runAlgorithm(boolean steps) {
        List<Process> processList = getProcessList();
        List<Process> processListReady = new ArrayList<>();
        List<Process> processListFinished = new ArrayList<>();

        Process processExec = null; //PROCESSO EM EXECUÇÃO

        processList.sort(Comparator.comparingInt(Process::getTimeFinalExecution));

        int timeAllProcess = processList.stream().map(Process::getTimeFinalExecution).reduce(0, Integer::sum);

        Logger.info("RESULTADO:");
        Logger.info("SISTEMA INTERATIVO");
        Logger.info("ESCALONAMENTO LOTERIA\n");

        if (steps) {
            Logger.info("TEMPO DE SUBMISSÃO:");
            getProcessList().forEach(process -> Logger.info(process.getProcessName() + "=" + process.getTimeSubmission() + "ms"));

            Logger.info("TEMPO DE EXECUCAO:");
            getProcessList().forEach(process -> Logger.info(process.getProcessName() + "=" + process.getTimeFinalExecution() + "ms"));

            Logger.info("ORDEM DE EXECUCAO:");
            Logger.info(getProcessList().stream().map(Process::getProcessName).collect(Collectors.joining("->")));
        }

        TableBuilder tableBuilder = new TableBuilder();
        tableBuilder.setBorders(TableBuilder.Borders.FRAME).frame(true);
        tableBuilder.addHeaders("Status", "Time", "Process Name (Exec)", "Process Ready", "Process Finished");

        List<List<String>> valuesTable = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        Logger.info("| Status\t|" + " Time \t|" + " Process Name (Exec)\t|" + " Process Ready\t|" + " Process Finished |");

        int time = -1;
        while (time < (timeAllProcess * 4)) {
            time++;

            for (Process process : getProcessList()) {

                /*CHEGOU NO TEMPO DE ENTRADA DE ALGUM PROCESSO*/
                if (process.getTimeSubmission() == time) {
                    if (processExec == null) {
                        processExec = process;
                        processExec.setTimeToPerform(time);
                        valuesTable.add(Arrays.asList("+", time + "s", processExec.getProcessName(), processListReady.stream().map(Process::getProcessName).collect(Collectors.joining("->")), processListFinished.stream().map(Process::getProcessName).collect(Collectors.joining(","))));
                        continue;
                    }

                    processListReady.add(process);
                    valuesTable.add(Arrays.asList("+", time + "s", processExec.getProcessName(), processListReady.stream().map(Process::getProcessName).collect(Collectors.joining("->")), processListFinished.stream().map(Process::getProcessName).collect(Collectors.joining(","))));
                    continue;
                }

                if (processExec == null) continue;

                /* SE O TEMPO QUE ELE TINHA QUE EXECUTAR - TEMPO QUE EXECUTOU FOR MENOR QUE 0*/
                if ((processExec.getTimeFinalExecution() - processExec.getExecutionDuration()) <= 0) {
                    if (processListFinished.contains(processExec)) continue;

                    processListFinished.add(processExec);
                    processExec = null;

                    if (!processListReady.isEmpty()) {
                        int random = new Random().nextInt(processListReady.size());
                        processExec = processListReady.get(random);
                        processExec.setTimeToPerform(time);

                        processListReady.remove(random);
                    }

                    valuesTable.add(Arrays.asList("-", time + "s", processExec != null ? processExec.getProcessName() : "", processListReady.stream().map(Process::getProcessName).collect(Collectors.joining("->")), processListFinished.stream().map(Process::getProcessName).collect(Collectors.joining(","))));
                    continue;
                }

                /* O QUANTUM FOI FINALIZADO */
                if ((time % quantum) == 0) {
                    if (processExec.getTimeToPerform() == time) continue;

                    processListReady.add(processExec);

                    int random = new Random().nextInt(processListReady.size());
                    processExec = processListReady.get(random);
                    processExec.setTimeToPerform(time);

                    processListReady.remove(random);
                    valuesTable.add(Arrays.asList(".", time + "s", processExec.getProcessName(), processListReady.stream().map(Process::getProcessName).collect(Collectors.joining("->")), processListFinished.stream().map(Process::getProcessName).collect(Collectors.joining(","))));

                    if (steps) {
                        scanner.nextLine();
                        Logger.info("\t\t.\t\t" + time + "s\t\t" + processExec.getProcessName() + "\t\t" + processListReady.stream().map(Process::getProcessName).collect(Collectors.joining("->")) + "\t\t" + processListFinished.stream().map(Process::getProcessName).collect(Collectors.joining(",")));
                    }
                }

            }
            /* ADICIONA UM SEGUNDO NO TEMPO DE DURAÇÃO DO PROCESSO */
            if (processExec != null) {
                processExec.setExecutionDuration(processExec.getExecutionDuration() + 1);
            }
        }

        if (!steps) {
            String[][] stringArray = valuesTable.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
            tableBuilder.setValues(stringArray);

            Logger.info("RESULTADO LOTERIA: (Quantum: " + quantum + ")\n Legenda: . Quantum | + Entrou | - Finalizou\n" + tableBuilder.build());
        }
    }
}
    

