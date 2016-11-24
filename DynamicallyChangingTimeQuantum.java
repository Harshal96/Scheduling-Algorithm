import java.util.Scanner;

class DynamicallyChangingTimeQuantum{
	int burstTime, arrivalTime, processID, waitingTime, turnAroundTime, copyOfBurstTime, flag = 0;
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Processes : ");
        int numberOfProcesses = sc.nextInt();
        double totalWaitingTime = 0, totalTurnAroundTime = 0;

        DynamicallyChangingTimeQuantum processObjects[] = new DynamicallyChangingTimeQuantum[numberOfProcesses + 1];
        for(int i = 1; i <= numberOfProcesses; i++)  processObjects[i] = new DynamicallyChangingTimeQuantum(); 

        DynamicallyChangingTimeQuantum temp = new DynamicallyChangingTimeQuantum();
        int timeRemaining = 0;
    	for(int i = 1; i <= numberOfProcesses; i++){
    		processObjects[i].processID = i;
    		System.out.println("\nEnter Burst Time of Process "+processObjects[i].processID+":");
    		processObjects[i].burstTime = sc.nextInt();    		
    		System.out.println("\nEnter Arrival Time of Process "+processObjects[i].processID+":");
    		processObjects[i].arrivalTime = sc.nextInt();
    		
    		timeRemaining += processObjects[i].burstTime;
    		processObjects[i].copyOfBurstTime = processObjects[i].burstTime;
    	}

        for(int i = 1; i <= numberOfProcesses; i++)
            for(int j = i+1; j <= numberOfProcesses; j++)
                if(processObjects[i].burstTime > processObjects[j].burstTime){
                    temp = processObjects[i];
                    processObjects[i] = processObjects[j];
                    processObjects[j] = temp;
                }

        double timeQuantum = (processObjects[numberOfProcesses].burstTime + processObjects[numberOfProcesses - 1].burstTime)/2;

        int clockTime = 0;

        for(int i = 1; clockTime < timeRemaining; ){
            if(processObjects[i].burstTime <= timeQuantum && processObjects[i].arrivalTime <= clockTime && processObjects[i].flag == 0){
                    clockTime += processObjects[i].burstTime;
                    processObjects[i].burstTime = 0;
                    processObjects[i].flag = 1;
                    System.out.print("| "+(processObjects[i].processID)+" ("+(clockTime)+")");
                }
            else{
                if(processObjects[i].burstTime > 0 && processObjects[i].arrivalTime <= clockTime){
                    clockTime += timeQuantum;
                    processObjects[i].burstTime -= timeQuantum;
                    System.out.print("| "+(processObjects[i].processID)+" ("+(clockTime)+")");
                }
            }

            if(processObjects[i].burstTime == 0 && processObjects[i].flag == 1){
                processObjects[i].waitingTime = clockTime - processObjects[i].arrivalTime - processObjects[i].copyOfBurstTime;
                processObjects[i].turnAroundTime = clockTime - processObjects[i].arrivalTime;
                processObjects[i].flag = -1;
            }

            if(i == numberOfProcesses){
                i = 1;
                for(int k = 1; k <= numberOfProcesses; k++)
                    for(int l = k+1; l <= numberOfProcesses; l++)
                        if(processObjects[k].burstTime > processObjects[l].burstTime){
                            temp = processObjects[k];
                            processObjects[k] = processObjects[l];
                            processObjects[l] = temp;
                        }
                timeQuantum = (timeQuantum + processObjects[numberOfProcesses].burstTime)/2;
            }
            else{
                i++;
            }
        }

        System.out.println("\n\tProcess ID\t\tArrival Time\t\tBurst Time\t\tWaiting Time\t\tTurn Around Time");
        for(int i = 1; i <= numberOfProcesses; i++){
            System.out.println("\t"+processObjects[i].processID+"\t\t\t"+processObjects[i].arrivalTime+"\t\t\t"+processObjects[i].copyOfBurstTime+"\t\t\t"+processObjects[i].waitingTime+"\t\t\t"+processObjects[i].turnAroundTime);    
            totalTurnAroundTime += processObjects[i].turnAroundTime;
            totalWaitingTime += processObjects[i].waitingTime;
        }

        System.out.println("Average Waiting Time: "+totalWaitingTime/numberOfProcesses+"\n Average Turnaround Time: "+totalTurnAroundTime/numberOfProcesses);
    }
}