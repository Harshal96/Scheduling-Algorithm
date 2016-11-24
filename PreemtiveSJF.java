import java.util.*;
class PreemtiveSJF{
    int burstTime, arrivalTime, process, turnaroundTime, waitingTime, copyOfBurstTime;
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Number of Processes : ");
        int numberOfProcesses=scanner.nextInt();
        
        PreemtiveSJF object[] = new PreemtiveSJF[numberOfProcesses+1];
        for(int i = 0; i <= numberOfProcesses; i++)
            object[i]= new PreemtiveSJF();
        
        int sum=0;
        for(int m = 1; m <= numberOfProcesses ; m++){
            object[m].process = m;
            System.out.println("Enter Burst Time and Arrival Time of Process "+m);
            object[m].burstTime = scanner.nextInt();
            object[m].arrivalTime = scanner.nextInt();
            sum+=object[m].burstTime;
            object[m].copyOfBurstTime = object[m].burstTime;
            System.out.println(object[m].copyOfBurstTime);
            }

        System.out.println("\n\nProcess \t Arrival Time \t CPU Burst");
        for(int i = 1; i <= numberOfProcesses; i++){
            System.out.println(""+(i)+" \t\t "+object[i].arrivalTime+" \t\t "+object[i].burstTime);
            }
        
        System.out.println();
        
        for(int clockTime = 1; clockTime <= sum; clockTime++){
            int min = 9999, index = 0;    
            for(int j = 1; j <= numberOfProcesses; j++){
                if(object[j].arrivalTime < clockTime && object[j].burstTime < min && object[j].burstTime>0){
                    min=object[j].burstTime;
                    index=j;
                    }
                }
            object[index].burstTime -= 1;
            if(object[index].burstTime == 0){
                object[index].turnaroundTime=clockTime-object[index].arrivalTime;
                object[index].waitingTime=object[index].turnaroundTime-object[index].copyOfBurstTime;
                }
            System.out.print("| "+object[index].process);
            }

        int totalTurnaroundTime = 0, totalWaitingTime = 0;    
        for(int i = 1; i <= numberOfProcesses; i++){
            totalTurnaroundTime += object[i].turnaroundTime;
            totalWaitingTime += object[i].waitingTime;
            }
            
        float averageTurnaroundTime = (float)totalTurnaroundTime/numberOfProcesses;
        float averageWaitingTime = (float)totalWaitingTime/numberOfProcesses;
        System.out.println("\n\nProcess \t Arrival Time \t Waiting Time \t Turn Around Time");
        
        for(int i = 1; i <= numberOfProcesses; i++){
            System.out.println(""+(i)+" \t\t "+object[i].arrivalTime+"\t\t"+object[i].waitingTime+" \t\t "+object[i].turnaroundTime);
            }
            
        System.out.println("\n\nAverage Turn Around Time: "+averageTurnaroundTime);
        System.out.println("Average Waiting Time: "+averageWaitingTime);
        }
    }
