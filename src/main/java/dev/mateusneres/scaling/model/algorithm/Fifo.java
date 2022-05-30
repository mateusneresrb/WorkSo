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
        processList.sort(Comparator.comparingInt(Process::getTimeSubmission));

        int timeAllProcess = processList.stream().map(Process::getTimeExecution).reduce(0, Integer::sum);

        List<Process> processListReady = new ArrayList<>();
        List<Process> processListFinished = new ArrayList<>();
        Process processExec = null; //PROCESSO EM EXECUÇÃO

        Logger.info("RESULTADO:");
        Logger.info("SISTEMA EM LOTES");
        Logger.info("ESCALONAMENTO FIFO\n");

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


    /*
     * FIFO ALGORITHM
     *
     * */

     /*

     public static void FIFO() throws IOException{
		
		Scanner in = new Scanner(System.in);
		
		int[] bt = new int[10];//burst time 
		int[] wt = new int[10];//wainting time - tempo de espera
		int[] tat = new int[10];//turn around time - tempo de resposta
		wt[0]=0; // tempo de espera
		//n = number of processs 
		int n,avwt=0,avtat=0,i,j;// i e j são as variaveis usadas no loop for
		
		System.out.println("Digite a quantidade de processos|no maximo 10");
		n = in.nextInt();
			
		//burst values
		System.out.println(" Digite o tempo de execução");
		for(i = 0; i < n; i++) {
				System.out.println("P[" + i + 1 + "]" );
				bt[i] = in.nextInt();
			}
		
		//waiting time - tempo de espera
		for(i=1;i<n;i++){
	        wt[i]=0;
	        
	        for(j = 0; j< i; j++)
	            wt[i]+=bt[j];
	    }
		
		System.out.println("#####################################################################################################");
		System.out.println("\\nProcesso\\t      tempo de execução\\t       Tempo de resposta\\t          Tempo de espera\\n");
		System.out.println("#####################################################################################################"); 
	    
		// turnaround time - tempo de resposta
	    for(i=0;i<n;i++){
	        
	    	tat[i] = bt[i]+wt[i];
	        avwt += wt[i];
	        avtat += tat[i];
	        
	        //System.out.println("P [ " + i + i +" ] " + " \t\t\t " + bt[i] + " \t\t\t " + tat[i] + "\t\t\t" + wt[i]);
	        System.out.print("\n  " + (i + 1)+"\t\t\t" + bt[i] + "\t\t\t"+ tat [i] + "\t\t\t" + wt[i] + "\n"); 
	     	System.in.read();
	    }
	 
	    avwt/=i; //calculo do tempo medio de espera
	    avtat/=i;//calculo do tempo medio de retorno
	    
	    System.out.println("\nTempo médio de espera = " + avwt);
	    System.out.println("\nTempo médio de retorno = " + avtat);
	    
	}

     
     
      */
}
