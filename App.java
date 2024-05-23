import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class App {

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

        System.out.print("Do you like to use preentered process (yes/no)?");

        String var = input.nextLine();
        if (var.equalsIgnoreCase("yes")) {
            // id arrival burst priority

            int testNum = kpd.getIntInput("enter test num (0-6): ", "canBeZero");
            Testing.runTest(processes1, testNum);

            processesStatistics = new ArrayList<>(processes1);

        } else if (var.equalsIgnoreCase("no")) {
            ProcessData();
            processesStatistics = new ArrayList<>(processes1);

        }

        Algorithm algo = new Algorithm(processes1, quantum, ganttchart);

        algo.schedullingAlgo();

        Statistics statistics = new Statistics();
        statistics.displayGanttChart(ganttchart);
        // statistics.displayGanttChartSimple(ganttchart);

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

        while (true) {
            processID = kpd.getIntInput("Enter Process ID: ", "canBeNegative");

            // the process id must be uniqe
            while (!isTheIdUniqe(processID)) {
                System.out.println("Sorry the ID " + processID + " is already taken, choose another one");
                processID = kpd.getIntInput("Enter Process ID: ", "canBeNegative");
            }
            // take the input from the user and validate it using the custom validation
            // with the getIntInput class
            arrivalTime = kpd.getIntInput("Enter Time Arrival: ", "canBeZero");
            burstTime = kpd.getIntInput("Enter Burst Time: ", "canBeZero");
            priority = kpd.getIntInput("Enter process Priority: ", "canBeZero");

            if (processID == 0 && arrivalTime == 0 && burstTime == 0 && priority == 0) {
                break;
            }
            // add the process to the processes1 array
            Process process = new Process(processID, arrivalTime, burstTime, priority);
            processes1.add(process);
            System.out.println("-------------");
        }
        numOfProcesses = processes1.size();
    }

}
