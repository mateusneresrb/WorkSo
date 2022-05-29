package dev.mateusneres.scaling.model.algorithm;

import dev.mateusneres.scaling.model.Algorithm;
import dev.mateusneres.scaling.model.Process;
import dev.mateusneres.scaling.types.AlgorithmType;
import dev.mateusneres.scaling.types.SystemType;

import java.util.List;

import java.io.IOException;
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
