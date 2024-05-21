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

public class App {

    static int[][] processes = new int[50][4];
    static final ArrayList<String> ganttchart = new ArrayList<String>();
    static ArrayList<Process> processes1 = new ArrayList<Process>(); // aray list of process class
    static int numOfProcesses = 0;
    int algorithm = 0;
    static int quantum;
    static int totalTurnaroundTime = 0;
    static int totalResponseTime = 0;
    static int totalWaitingTime = 0;

    static double avgTurnaroundTime = 0;
    static double avgResponseTime = 0;
    static double avgWaitingTime = 0;

    static ArrayList<Process> processesStatistics;

    // Collect process details
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter quantum time (q): ");

        quantum = input.nextInt();

        while (quantum <= 0) {
            System.out.println(
                    "Error: The quantum time must be greater than 0\n" + "Please enter the quantum time again");
            quantum = input.nextInt();
        }

        // id arrival burst priority
        Process firstprocess = new Process(1, 0, 1, 2);
        Process secondProcess = new Process(2, 1, 7, 6);
        Process thirdProcess = new Process(3, 2, 3, 3);
        Process fourthProcess = new Process(4, 3, 6, 5);
        Process fifthProcess = new Process(5, 4, 5, 4);
        processes1.add(firstprocess);
        processes1.add(secondProcess);
        processes1.add(thirdProcess);
        processes1.add(fourthProcess);
        processes1.add(fifthProcess);

        numOfProcesses = processes1.size();
        schedullingAlgo(processes1, quantum);
        displayGanttChart();
        displayStatistics();
        // displayAvg();
        // ProcessData(input);
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
        // processes.sort(Comparator.comparingInt(p -> p.getArrivalTime()));

        processes.sort(Comparator.comparingInt(p -> p.getPriority()));

        processesStatistics = new ArrayList<>(processes);
        processesStatistics.sort(Comparator.comparingInt(p -> p.getPriority()));
        // Queue for ready processes
        Queue<Process> readyQueue = new LinkedList<>();

        // Calculate Turnaround Time, Response Time, Waiting Time, and Gantt Chart
        int currentTime = 0;

        // System.out.println("second bruh" + processes.get(1).getArrivalTime());

        ganttchart.add("0");
        int i = getNextReady(processes, 0, currentTime); /* index of first ready process */

        while (processes.size() != 1) {
            Process runningProcess = processesStatistics.get(i);

            int remainingBurst = processes.get(i).getRemainingBurst();

            // if it is the first time for the process to run,
            // then add it's response time to the totalResponseTime
            if (runningProcess.getBurstTime() == remainingBurst) {
                runningProcess.setResponseTime(currentTime - runningProcess.getArrivalTime());
            }

            ganttchart.add("p" + processes.get(i).getPid());
            if (remainingBurst - quantum <= 0) {

                currentTime += remainingBurst;
                runningProcess
                        .setWaitingTime(currentTime - runningProcess.getArrivalTime() - runningProcess.getBurstTime());

                runningProcess.setTurnAroundTime(currentTime - runningProcess.getArrivalTime());

                processes.remove(i);

                ganttchart.add((currentTime) + ""); /* converting to string using concatenation */
            } else {
                processes.get(i).setRemainingBurst(remainingBurst - quantum);
                currentTime = currentTime + quantum;
                ganttchart.add((currentTime) + ""); /* converting to string using concatenation */
            }
            i = getNextReady(processes, i, currentTime);
            if (i == -1) {
                break;
            }

        }
        ganttchart.add("p" + processes.get(0).getPid());
        ganttchart.add(currentTime + processes.get(0).getRemainingBurst() + "");

    }

    public static void displayStatistics() {

        for (int i = 0; i < numOfProcesses; i++) {
            Process currentP = processesStatistics.get(i);
            boolean foundResponseTime = false;
            String pName = "p" + currentP.getPid();

            for (int j = 0; j < ganttchart.size(); j++) {

                if (ganttchart.get(j).equals(pName)) {

                    if (!foundResponseTime) {
                        currentP.setResponseTime(Integer.parseInt(ganttchart.get(j - 1)) - currentP.getArrivalTime());
                        foundResponseTime = true;
                    }

                    currentP.setTurnAroundTime(Integer.parseInt(ganttchart.get(j + 1)) - currentP.getArrivalTime());
                    currentP.setWaitingTime(currentP.getTurnAroundTime() - currentP.getBurstTime());
                }

            }
        }
        processesStatistics.sort(Comparator.comparingInt(p -> p.getPid()));

        for (Process p : processesStatistics) {
            // processes1
            System.out.println("pid: " + p.getPid());
            System.out.println("Arrival Time: " + p.getArrivalTime());
            System.out.println("Bust Time: " + p.getBurstTime());
            System.out.println("Priority: " + p.getPriority());
            System.out.println("Turnaround Time: " + p.getTurnAroundTime());
            System.out.println("Response Time: " + p.getResponseTime());
            System.out.println("Waiting Time: " + p.getWaitingTime());

            System.out.println("-----------------------");

            totalTurnaroundTime += p.getTurnAroundTime();
            totalResponseTime += p.getResponseTime();
            totalWaitingTime += p.getWaitingTime();
        }

        if (numOfProcesses > 0) {
            avgTurnaroundTime = (double) totalTurnaroundTime / (double) numOfProcesses;
            avgWaitingTime = (double) totalWaitingTime / (double) numOfProcesses;
            avgResponseTime = (double) totalResponseTime / (double) numOfProcesses;
        }

        System.out.println("average turnaround time: " + avgTurnaroundTime);
        System.out.println("average Waiting time: " + avgWaitingTime);
        System.out.println("average Response time: " + avgResponseTime);

    }

    public static void displayGanttChart() {
        int j = 0;
        while (j < ganttchart.size()) {
            if (j == ganttchart.size() - 1) {
                System.out.println(ganttchart.get(j));
            } else {
                if (j <= ganttchart.size() - 3 && ganttchart.get(j) == ganttchart.get(j + 2)) {
                    j += 2;
                }
                System.out.print(ganttchart.get(j) + "-");
            }
            j++;
        }
    }

    public static int getNextReady(ArrayList<Process> processes, int currentIndex, int currentTime) {
        /* Return first ready process, they are already sorted by priority */
        int size = processes.size();
        int indexToReturn;
        int j = 0;
        // Start from the current index and check all processes circularly
        for (int i = 0; i < size; i++) {
            int index = (j + i) % size;
            if ((currentTime >= processes.get(index).getArrivalTime())
            // && (processes.get(index).getRemainingBurst() != 0)
            ) {
                return index; //
            }
        }
        /* If still not returned, this means there is IDLE time in the CPU */
        ganttchart.add("\033[33mIDLE\033[0m");

        /* finding the minimum arrival time to choose as next process */
        int minimum = Integer.MAX_VALUE;
        for (int i = 0; i < processes.size(); i++) {
            int thisArrival = processes.get(i).getArrivalTime();
            if (thisArrival < minimum) {
                minimum = thisArrival;
            }
        }
        // ganttchart.add(currentTime + "");
        if (minimum == Integer.MAX_VALUE) {
            return -1;
        }
        return minimum;

    }
}
