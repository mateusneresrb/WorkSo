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
public class Lottery extends Algorithm {

    private int quantum;

    public Lottery(AlgorithmType algorithmType, SystemType systemType, List<Process> processList) {
        super(algorithmType, systemType, processList);
    }
    

    /*
     *  LOTERRY ALGORITHM
feito por min
    public static void lottery(){
	
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		int n,i,j,k, temp = 65, flag = 0,q = 0,pos;
		int time = 0, quantum =1, tbt = 0, z = 0;
		
		char[] process = new char[10];
		int[] bt = new int[10];// burst
		int[] priority = new int[10];
		int[] lottery = new int[10][10];
		int[] ticket = new int[10];
		
		System.out.println("Digite a quantidade de processos| maximo 10");
		n = in.nextIn();

	
		for(i=0;i<n;i++){
			
			process[i] = temp;
			temp += 1;
		}
	
		
		for(i=0;i<n;i++){
		
		System.out.println("\n Enter The Brust Time For Process " + process[i]);
		bt[i] = in.nextInt();
		
		printf("\n Digite a prioridade para o processo" + process[i] + n);
		priority = in.nextInt();
		
	}
	

    //Olha o sorteio
	for(i=0;i<n;i++){
        
		pos = i;
        
		for(j=i+1;j<n;j++){
            
			if(priority[j] < priority[pos])
                pos=j;
        }
 
        temp = process[i];
        process[i] = process[pos];
        process[pos] = temp;
 
        temp = bt[i];
        bt[i] = bt[pos];
        bt[pos] = temp;
 
        temp = priority[i];
        priority[i] = priority[pos];
        priority[pos] = temp;
        
        if(bt[i]<0){
			flag = 1;
		}
    }
    Sytem.out.println("###########################################################");
    System.out.println(" Prioridade \t\t\t  Processo  \t\t\t  execução ");
	
	for(i = 0; i < n; i++){
		System.out.println(priority[i]  +"\t\t\t"  +process[i] +"\t\t\t"+bt[i]);
		tbt = tbt + bt[i];
	}
	
	System.out.println("\n##########  Quantum = 1  ###############\n");
	
	// atribui mais de um numero
	int p=1,m_ticket=0;
	System.out.println(" Prioridade \t\t\t  Processo  \t\t\t  execução \t\t\t loteria \t\t\t ticket ");
	
	for(i=0;i<n;i++){
		
		lottery[i] = (bt[i]/quantum) + (n-priority[i]);
		for (z=0;z<lottery[i];z++){
			
            ticket[i][z] = p++;
            m_ticket = p;
        
		}
      			
		System.out.println(priority[i] + "\t\t\t" + process[i] + "\t\t \t " + bt[i] + "\t\t\t " + lottery[i]);
		
		for(z=0 ; z<lottery[i]; z++){
			
			if(ticket[i][z]<10){
				
				System.out.println(ticket[i][z]);
			
			}else{
			
				System.out.println(ticket[i][z]);
			
			}
    	}   
	}
	
	while(time != tbt){
	
	int winner = (rand()%m_ticket-1)+ 1;
    for(i =0;i<n;i++)
        for(z=0;z<lottery[i];z++)
            if(ticket[i][z]==winner)
                q=i;
                
    System.out.println("\n##########################################");
    System.out.println("vencedor " + winner);
    System.out.println("\n############################################");
	
	if ((bt[q]>0)){
		
        bt[q] -= quantum;
        
        if (bt[q]>0) {
			
            time += quantum;
			
        } 
		else {
            time += (bt[q] + quantum);
        }
		
		if(bt[q]<0) {
                bt[q]=0;
        }
		
	    System.out.println("\n O processo que esta rodando " + process[q]);
		System.out.println("\n########################################################################################");
		System.out.println(" Tempo total " + time + " Tempo restante de execução " + bt[q] + " processo " + process[q] );
       
	}
    else{
    	System.out.println("Ocorreu algum problema");
	}
    
 }



     * */

    
}
