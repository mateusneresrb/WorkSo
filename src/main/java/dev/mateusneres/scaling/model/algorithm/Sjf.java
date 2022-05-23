package dev.mateusneres.scaling.model.algorithm;

import dev.mateusneres.scaling.model.Algorithm;

public class Sjf extends Algorithm {

/*
* SJF ALGORITHM
*
* */

public static void TrabalhoMaisCurtoPrimeiro(){
				
		Scanner in = new Scanner(System.in);
		System.out.println ("Digite a quantum:");

		int n = in.nextInt();
		int pid[] = new int[n];
		int at[] = new int[n]; //  arrival time
		int bt[] = new int[n]; // bt  burst time
		int ct[] = new int[n]; // ct complete time
		int ta[] = new int[n]; // ta  turn around time
		int wt[] = new int[n];  //wt  waiting time
		int f[] = new int[n];  // f means it is flag it checks process is completed or not
		int st=0, tot=0;
		
		float avgwt=0, avgta=0;
 
		for(int i=0;i<n;i++){

			System.out.println ("enter process " + (i+1) + " arrival time:");
			at[i] = in.nextInt();

			System.out.println ("enter process " + (i+1) + " brust time:");
			bt[i] = in.nextInt();
			pid[i] = i+1;
			f[i] = 0;
		}
		
		boolean a = true;
		
		while(true){
			int c=n, min=999;
			if (tot == n) // total no of process = completed process loop will be terminated
				break;
			for (int i=0; i<n; i++){
/*
* If i'th process arrival time <= system time and its flag=0 and burst<min
* That process will be executed first
*/
				if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min)){
					
					min=bt[i];
					c=i;
				}
}
/* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
			if (c==n)
				st++;
				else{
					ct[c]=st+bt[c];
					st+=bt[c];
					ta[c]=ct[c]-at[c];
					wt[c]=ta[c]-bt[c];
					f[c]=1;
					tot++;
				}
}

		System.out.println("\npid  arrival brust  complete turn waiting");

		for(int i=0;i<n;i++){
			
			avgwt+= wt[i];
			avgta+= ta[i];

			System.out.println(pid[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+ta[i]+"\t"+wt[i]);
}
		System.out.println ("\naverage tat is "+ (float)(avgta/n));
		System.out.println ("average wt is "+ (float)(avgwt/n));
		in.close();
	}



}
