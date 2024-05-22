import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class App {

    static int[][] processes = new int[50][4];
    static final ArrayList<String> ganttchart = new ArrayList<String>();
    static ArrayList<Process> processes1 = new ArrayList<Process>(); // aray list of process class
    static int numOfProcesses = 0;
    int algorithm = 0;
    static int quantum;

    static ArrayList<Process> processesStatistics;

    // Collect process details
    public static void main(String[] args) {
        // Scanner input = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        UserInput kpd = new UserInput();
        quantum = kpd.getIntInput("Enter quantum time (q): ", "onlyPositive");

        System.out.println("Do you like to use preentered process (yes/no)?");

        String var = input.nextLine();
        if (var.equalsIgnoreCase("yes")) {
            // id arrival burst priority
            processes1.add(new Process(1, 0, 1, 2));
            processes1.add(new Process(2, 1, 7, 6));
            processes1.add(new Process(3, 2, 3, 3));
            processes1.add(new Process(4, 3, 6, 5));
            processes1.add(new Process(5, 4, 5, 4));

            ArrayList<Process> processesStatistics = new ArrayList<>(processes1);

            Algorithm.schedullingAlgo(processes1, quantum, ganttchart);

        } else {
            ProcessData();
        }

        Statistics statistics = new Statistics();
        statistics.displayGanttChart(ganttchart);
        statistics.displayStatistics(processesStatistics, ganttchart);
        // displayAvg();
    }

    public static boolean isTheIdUniqe(int id) {
        for (Process p : processes1) {
            if (p.getPid() == id) {
                return false;
            }
        }
        return true;
    }

    // collecting Process data
    public static void ProcessData() {
        int processID = 0;
        int arrivalTime = 0;
        int burstTime = 0;
        int priority = 0;
        UserInput kpd = new UserInput();
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < processes.length; i++) {
            processID = kpd.getIntInput("Enter Process ID: ", "canBeNegative");

            // isTheIdUniqe is not working because we are using a sigle Array list, not 2D
            // array
            while (!isTheIdUniqe(processID)) {
                System.out.println("Sorry the ID " + processID + " is already taken, choose another one");
                processID = kpd.getIntInput("Enter Process ID: ", "canBeNegative");
            }

            arrivalTime = kpd.getIntInput("Enter Time Arrival: ", "canBeZero");
            burstTime = kpd.getIntInput("Enter Burst Time: ", "canBeZero");
            priority = kpd.getIntInput("Enter process Priority: ", "canBeZero");

            // TODO: how can we stop the program, if we enterd a process ID that is zero
            // before ???
            if (processID == 0 && arrivalTime == 0 && burstTime == 0 && priority == 0) {
                break;
            }

            Process process = new Process(processID, arrivalTime, burstTime, priority);
            processes1.add(process);
            System.out.println("-------------");
        }
        Algorithm.schedullingAlgo(processes1, quantum, ganttchart);
        numOfProcesses = processes1.size();
    }

}
