import java.util.Scanner;

class App {

    static int[][] processes = new int[50][4];


    int numOfProcesses = 0; 
    int algorithm = 0;

     // Collect process details
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ProcessData(input);
     }


     //collecting Process data
        public static void ProcessData(Scanner input) {

       for(int i=0; i<processes.length;i++){
        System.out.println("Enter Process ID" +i);
        processes[i][0] = input.nextInt();
 
        System.out.println("Enter Brust Time" +i);
        processes[i][1] = input.nextInt();

        System.out.println("Enter Time Arrival" +i);
        processes[i][2] = input.nextInt();

        System.out.println("Enter procsess Priorty" +i);
        processes[i][3] = input.nextInt();
             
    }
}


    

    public void PriorityWithRoundRobin() {
        for (int i = 0; i < numOfProcesses; i++) {

        }
    }

  
}
