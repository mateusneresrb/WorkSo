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
public class RoundRobin extends Algorithm {

    private int quantum;

    public RoundRobin(AlgorithmType algorithmType, SystemType systemType, List<Process> processList) {
        super(algorithmType, systemType, processList);
    }

    /* ROUND ROBIN METHODS */
    //Fiz o escalonador como metodo mas está rodando perfeitamente,basta estanciar -Leonardo
   /*
    public static void roundRobin() {
		 
			Scanner in = new Scanner(System.in); 
		
		    int n,i,quantum,count=0,temp,sq=0;  
			float tme=0,tmr=0;  
			
				int[]bt = new int[10]; burst time 
				int[]wt = new int[10];   wainting time
				int[]tat = new int[10];  
				int[]rem_bt = new int[10];  
			
			System.out.println("Digite o nome do processo");
			String processo = in.nextLine();
			
			System.out.print("Digite a quantidade de processos |no maximo 10 processos = ");  
				n = in.nextInt();  
			
			System.out.print("Digite o tempo de execução do processo\n");  
				
				for (i=0 ; i<n; i++) {  
						System.out.print(processo + i + " = ");   
					
					bt[i] = in.nextInt();  
					rem_bt[i] = bt[i];  
				}		  

			System.out.print("Digite o quantum: ");  
				quantum = in.nextInt();  

			while(true) {  
				for (i = 0 ,count = 0 ; i<n ;i++){  
						temp = quantum;  
					if(rem_bt[i] == 0){  
						count++;  
					continue;  
					}  
					 if(rem_bt[i] > quantum)  
						rem_bt[i]= rem_bt[i] - quantum;  
					 else  
					
				    if(rem_bt[i]>=0){  
							temp = rem_bt[i];  
							rem_bt[i] = 0;  
						}  
							sq = sq + temp;  
							tat[i] = sq;  
					}  
					
					if(n == count) {
						break;
						}
						
					}  

			
			System.out.print("###################################################################################################");  
			System.out.print(" \nProcesso\t      tempo de execução\t       Tempo de resposta\t          Tempo de espera\n ");  
			System.out.print("###################################################################################################");  

			for(i = 0 ; i<n ; i++){  
				
					wt[i] = tat[i] - bt[i];  
					tme = tme + wt[i];  
					tmr = tmr + tat[i];  
					
					System.out.print("\n  " + (i + 1)+"\t\t\t" + bt[i] + "\t\t\t"+ tat [i] + "\t\t\t" + wt[i] + "\n");  
				}  

					tme = tme/n;  
					tmr = tmr/n;  
					
					System.out.println("\nTempo médio de espera = "+ tme);  
					System.out.println("\nTempo médio de retorno = " + tmr);  
				}  

*/
    //Deixei comentado para não causar nenhum erro


//codigo não reforado abaixo

/*
public class RoundRobin  {  
public static void main(String args[])  {  
int n,i,qt,count=0,temp,sq=0,bt[],wt[],tat[],rem_bt[]; float awt=0,atat=0;  
	
bt = new int[10];  
wt = new int[10];  
tat = new int[10];  
rem_bt = new int[10];  

Scanner s=new Scanner(System.in);  
	
System.out.print("Enter the number of process (maximum 10) = ");  
n = s.nextInt();  
	
System.out.print("Enter the burst time of the process\n");  
	for (i=0;i<n;i++) {  
System.out.print("P"+i+" = ");   
bt[i] = s.nextInt();  
rem_bt[i] = bt[i];  
		}		  

	System.out.print("Enter the quantum time: ");  
	qt = s.nextInt();  

while(true) {  
	for (i=0,count=0;i<n;i++){  
		temp = qt;  
		if(rem_bt[i] == 0){  
		count++;  
		continue;  
}  
if(rem_bt[i]>qt)  
rem_bt[i]= rem_bt[i] - qt;  
else  
if(rem_bt[i]>=0)  
{  
temp = rem_bt[i];  
rem_bt[i] = 0;  
}  
sq = sq + temp;  
tat[i] = sq;  
}  
if(n == count)  
break;  
}  
System.out.print("--------------------------------------------------------------------------------");  
System.out.print("\nProcess\t      Burst Time\t       Turnaround Time\t          Waiting Time\n");  
System.out.print("--------------------------------------------------------------------------------");  
for(i=0;i<n;i++)  
{  
wt[i]=tat[i]-bt[i];  
awt=awt+wt[i];  
atat=atat+tat[i];  
System.out.print("\n "+(i+1)+"\t "+bt[i]+"\t\t "+tat[i]+"\t\t "+wt[i]+"\n");  
}  
awt=awt/n;  
atat=atat/n;  
System.out.println("\nAverage waiting Time = "+awt+"\n");  
System.out.println("Average turnaround time = "+atat);  
}  
}  
*/

}
