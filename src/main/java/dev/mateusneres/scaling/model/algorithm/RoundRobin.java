package dev.mateusneres.scaling.model.algorithm;

import dev.mateusneres.scaling.model.Algorithm;

public class RoundRobin extends Algorithm {

    /* ROUND ROBIN METHODS */
    //Fiz o escalonador como metodo mas está rodando perfeitamente,basta estanciar -Leonardo
   /*
    public static void roundRobin() {
		 
			Scanner in = new Scanner(System.in); 
		
		    int n,i,quantum,count=0,temp,sq=0;  
			float tme=0,tmr=0;  
			
				int[]bt = new int[10];  
				int[]wt = new int[10];  
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
}
