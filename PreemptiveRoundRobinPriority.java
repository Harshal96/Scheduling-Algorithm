import java.util.Scanner;

class PreemptiveRoundRobinPriority{
	int burstTime, arrivalTime, processID, waitingTime, turnAroundTime, copyOfBurstTime, priority, flag = 0;
	double  ownQuantumTime;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Processes : ");
        int numberOfProcesses = sc.nextInt();
        double totalWaitingTime = 0, totalTurnAroundTime = 0;

        PreemptiveRoundRobinPriority processObjects[] = new PreemptiveRoundRobinPriority[numberOfProcesses + 1];
        for(int i = 1; i <= numberOfProcesses; i++)  processObjects[i] = new PreemptiveRoundRobinPriority(); 

        int timeRemaining = 0;
    	for(int i = 1; i <= numberOfProcesses; i++){
    		processObjects[i].processID = i;
    		System.out.println("\nEnter Burst Time of Process "+processObjects[i].processID+":");
    		processObjects[i].burstTime = sc.nextInt();    		
    		System.out.println("\nEnter Arrival Time of Process "+processObjects[i].processID+":");
    		processObjects[i].arrivalTime = sc.nextInt();
    		System.out.println("\nEnter Priority(1-3) of Process "+processObjects[i].processID+":");
    		processObjects[i].priority = sc.nextInt();
    		
    		while(processObjects[i].priority < 1 || processObjects[i].priority > 3){
    			System.out.println("Enter correct Priority of the Process");
    			processObjects[i].priority = sc.nextInt();
    		}
    		
    		timeRemaining += processObjects[i].burstTime;
    		processObjects[i].copyOfBurstTime = processObjects[i].burstTime;
    	}

    	System.out.println("\nEnter Time Quantum");
    	int timeQuantum = sc.nextInt();

    	for(int i = 1; i <= numberOfProcesses; i++)
    		if(processObjects[i].priority == 1)
    			processObjects[i].ownQuantumTime = timeQuantum - 200;
    		else if(processObjects[i].priority == 2)
    			processObjects[i].ownQuantumTime = timeQuantum;
    		else
    			processObjects[i].ownQuantumTime = timeQuantum + 200;

    	System.out.println("\n\nTime Quantum for High Priority Processes: "+(timeQuantum + 200)+"\nTime Quantum for Medium Priority Processes: "+(timeQuantum)+"\nTime Quantum for Low Priority Processes: "+(timeQuantum - 200)+"\n");
		
		int clockTime = 0;
		for(int i = 1; clockTime < timeRemaining; ){
			for(int j = 1; j <= numberOfProcesses; j++){
				if(processObjects[j].arrivalTime <= clockTime && processObjects[j].priority == 3 && (processObjects[j].burstTime <= 100) && processObjects[j].flag == 0){
					clockTime += processObjects[j].burstTime;
					processObjects[j].burstTime = 0;
					processObjects[j].flag = -1;
					System.out.print("| "+(processObjects[j].processID)+" ("+(clockTime)+")");
					processObjects[j].waitingTime = clockTime - processObjects[j].arrivalTime - processObjects[j].copyOfBurstTime;
					processObjects[j].turnAroundTime = clockTime - processObjects[j].arrivalTime;
				}
			}

			if(processObjects[i].priority == 3){
				if(processObjects[i].burstTime <= processObjects[i].ownQuantumTime && processObjects[i].arrivalTime <= clockTime && processObjects[i].flag == 0){
					clockTime += processObjects[i].burstTime;
					processObjects[i].burstTime = 0;
					processObjects[i].flag = 1;
					System.out.print("| "+(processObjects[i].processID)+" ("+(clockTime)+")");
				}
				else{
					if(processObjects[i].burstTime <= (processObjects[i].ownQuantumTime + processObjects[i].ownQuantumTime*0.3) && processObjects[i].arrivalTime <= clockTime && processObjects[i].flag == 0){
						clockTime += processObjects[i].burstTime;
						processObjects[i].burstTime = 0;
						processObjects[i].flag = 1;
						System.out.print("| "+(processObjects[i].processID)+" ("+(clockTime)+")");
					}
					else{
						if(processObjects[i].burstTime > 0 && processObjects[i].arrivalTime <= clockTime){
							clockTime += processObjects[i].ownQuantumTime;
							processObjects[i].burstTime -= processObjects[i].ownQuantumTime;
							System.out.print("| "+(processObjects[i].processID)+" ("+(clockTime)+")");
						}
					}
				}
			}

			else{
				if(processObjects[i].burstTime <= processObjects[i].ownQuantumTime && processObjects[i].arrivalTime <= clockTime && processObjects[i].flag == 0){
					clockTime += processObjects[i].burstTime;
					processObjects[i].burstTime = 0;
					processObjects[i].flag = 1;
					System.out.print("| "+(processObjects[i].processID)+" ("+(clockTime)+")");
				}
				else{
					if(processObjects[i].burstTime <= (processObjects[i].ownQuantumTime + processObjects[i].ownQuantumTime*0.2) && processObjects[i].arrivalTime <= clockTime && processObjects[i].flag == 0){
						clockTime += processObjects[i].burstTime;
						processObjects[i].burstTime = 0;
						processObjects[i].flag = 1;
						System.out.print("| "+(processObjects[i].processID)+" ("+(clockTime)+")");
					}
					else{
						if(processObjects[i].burstTime > 0 && processObjects[i].arrivalTime <= clockTime){
							clockTime += processObjects[i].ownQuantumTime;
							processObjects[i].burstTime -= processObjects[i].ownQuantumTime;
							System.out.print("| "+(processObjects[i].processID)+" ("+(clockTime)+")");
						}
					}
				}
			}

			if(processObjects[i].burstTime == 0 && processObjects[i].flag == 1){
				processObjects[i].waitingTime = clockTime - processObjects[i].arrivalTime - processObjects[i].copyOfBurstTime;
				processObjects[i].turnAroundTime = clockTime - processObjects[i].arrivalTime;
				processObjects[i].flag = -1;
			}

			if(i == numberOfProcesses){
				i = 1;
			}
			else{
				i++;
			}
		}
			System.out.println("\n\n\tProcess ID\t\tArrival Time\t\tBurst Time\t\tWaiting Time\t\tTurn Around Time");
			for(int i = 1; i <= numberOfProcesses; i++){
			      System.out.println("\t"+processObjects[i].processID+"\t\t\t"+processObjects[i].arrivalTime+"\t\t\t"+processObjects[i].copyOfBurstTime+"\t\t\t"+processObjects[i].waitingTime+"\t\t\t"+processObjects[i].turnAroundTime);    
				  totalTurnAroundTime += processObjects[i].turnAroundTime;
				  totalWaitingTime += processObjects[i].waitingTime;
			}

			System.out.println("\nAverage Waiting Time: "+totalWaitingTime/numberOfProcesses+"\nAverage Turnaround Time: "+totalTurnAroundTime/numberOfProcesses);
	}
}