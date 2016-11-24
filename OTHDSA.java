import java.util.*;

class OTHDSA {
    public static void main(String args[]) {
        Scanner scanner  = new Scanner(System.in);

    	int leftHead = 0, rightHead = 200, numberOfRequests, i, j, temporaryVariable, x, y, seekDistance = 0;
        int arrayOfRequests[] = new int[200];

    	System.out.println("Enter the Number of Track Requests\n");
    	numberOfRequests = scanner.nextInt();

    	System.out.println("Enter Track Positions\n");
    	for(i = 0; i < numberOfRequests; i++)
        	arrayOfRequests[i] = scanner.nextInt();

    	for(i = 0; i < numberOfRequests; i++)
        	for(j = i + 1; j < numberOfRequests; j++)
            	if(arrayOfRequests[i] > arrayOfRequests[j]){
                	temporaryVariable =  arrayOfRequests[i];
                	arrayOfRequests[i] = arrayOfRequests[j];
                	arrayOfRequests[j] = temporaryVariable;
            	}

    	x = arrayOfRequests[0] - leftHead;
    	y = rightHead - arrayOfRequests[numberOfRequests - 1];

    	if(x <= y){
        	System.out.println("Left Head is Working\n");
        	i = 0;
        	while(arrayOfRequests[i] <= 100){
            	leftHead = arrayOfRequests[i];
            	System.out.println("Left Head moves to: "+leftHead);
            	i = i + 1;
        	}

        	x = arrayOfRequests[numberOfRequests-1] - arrayOfRequests[i-1];
        	y = 200 - arrayOfRequests[i];
        	
            if(x <= y){
            	System.out.println("Left Head continues\n");
            	while(i <= numberOfRequests - 1){
                	leftHead = arrayOfRequests[i];
                	System.out.println("Left Head moves to: "+leftHead);
                	i = i + 1;
        	        }
        	    }
        	
            else{
            	j = numberOfRequests - 1;
            	System.out.println("Right Head is Working\n");
            	while(j >= i){
                	rightHead = arrayOfRequests[j];
                	System.out.println("Right Head moves to: "+rightHead);
                	j = j - 1;
        	        }
        	    }
        	}

    	else{
        	System.out.println("Right Head is Working\n");
        	i = numberOfRequests - 1;
        	while(arrayOfRequests[i] >= 100){
            	rightHead = arrayOfRequests[i];
            	System.out.println("Right Head moves to: "+rightHead);
            	i = i - 1;
        	    }

        	x = arrayOfRequests[i] - 0;
        	y = arrayOfRequests[i+1] - arrayOfRequests[0];

        	if(x < y){
            	System.out.println("Left Head is Working\n");
            	j = 0;
            	while(j <= i){
                	leftHead = arrayOfRequests[j];
                	System.out.println("Left Head moves to: "+leftHead);
                	j = j + 1;
        	        }
        	    }
        	else{
            	System.out.println("Right Head is Working\n");
            	while(i >= 0){
                	rightHead = arrayOfRequests[i];
                	System.out.println("Right Head moves to: "+rightHead);
                	i = i - 1;
        	        }
        	    }
        	}

    	seekDistance = leftHead + 200 - rightHead;
    	System.out.println("\nSeek Distance is: "+seekDistance);
    }
}