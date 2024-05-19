import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Process {
    private int pid;
    private int arrivalTime;
    private int burstTime;
    private int turnAroundTime;
    private int responseTime;
    private int waitingTime;
    private int remainingBurst;
    private int priority;

    Process(int processID, int arrivalTime, int burstTime, int priority) {
        this.pid = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingBurst = burstTime;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getRemainingBurst() {
        return remainingBurst;
    }

    public void setRemainingBurst(int remainingBurst) {
        this.remainingBurst = remainingBurst;
    }

}

class App {

    static int[][] processes = new int[50][4];
    static final ArrayList<String> ganttchart = new ArrayList<String>();
    static ArrayList<Process> processes1 = new ArrayList<Process>(); // aray list of process class
    int numOfProcesses = 0;
    int algorithm = 0;
    static int quantum;

    // Collect process details
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter quantum time (q): ");
        quantum = input.nextInt();
        ProcessData(input);
    }

    // collecting Process data
    public static void ProcessData(Scanner input) {
        Process Process = null;
        for (int i = 0; i < processes.length; i++) {
            System.out.println("Enter Process ID: " + i);
            processes[i][0] = input.nextInt();

            System.out.println("Enter Brust Time: " + i);
            processes[i][1] = input.nextInt();

            System.out.println("Enter Time Arrival: " + i);
            processes[i][2] = input.nextInt();

            System.out.println("Enter process Priorty: " + i);
            processes[i][3] = input.nextInt();

            if (processes[i][0] == 0 && processes[i][1] == 0 && processes[i][2] == 0 && processes[i][3] == 0) {
                break;
            }
            Process = new Process(processes[i][0], processes[i][2], processes[i][1], processes[i][3]);

            for (Process p : processes1) {
                if (processes[i][0] == (p.getPid())) {
                    System.out.println("Sorry the pid \"" + processes[i][0] + "\" is already taken by another process");
                    break;
                }
            }
            processes1.add(Process); // add every process to the process array list

        }

        schedullingAlgo(processes1, quantum);

    }

    public static void schedullingAlgo(ArrayList<Process> processes, int quantum) {
        // Sort processes based on arrival time and priority
        processes.sort(Comparator.comparingInt(p -> p.getArrivalTime()));
        processes.sort(Comparator.comparingInt(p -> p.getPriority()));

        // Queue for ready processes
        Queue<Process> readyQueue = new LinkedList<>();

        // Calculate Turnaround Time, Response Time, Waiting Time, and Gantt Chart
        int currentTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int totalWaitingTime = 0;

        ganttchart.add("0");
        int i = 0; /* index */
        while(processes.size() != 0){
            int remainingBurst = processes.get(i).getRemainingBurst();
            ganttchart.add("p" + processes.get(i).getPid());
            if (remainingBurst-quantum >= 0) {
                currentTime += remainingBurst;
                processes.remove(i);
            } else {
                processes.get(i).setRemainingBurst(remainingBurst-quantum);
                currentTime = currentTime+quantum;
                i++;
                if (i >= processes.size()){ /* If i == size, then it reached the end, must go back to first process in waiting */
                    i = 0;
                }
            }
            ganttchart.add((currentTime) + ""); /*  converting to string using concatenation */
        }
    }

    public void PriorityWithRoundRobin() {
        for (int i = 0; i < numOfProcesses; i++) {

        }
    }

}
