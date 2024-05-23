import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {

    private ArrayList<Process> processes;
    private int quantum;
    ArrayList<String> ganttchart;
    int currentTime;

    public Algorithm(ArrayList<Process> processes, int quantum, ArrayList<String> ganttchart) {
        this.processes = processes;
        this.quantum = quantum;
        this.ganttchart = ganttchart;
        this.currentTime = 0;

    }

    public void schedullingAlgo() {
        // Sort processes based on arrival time and priority
        processes.sort(Comparator.comparingInt(p -> p.getArrivalTime()));

        processes.sort(Comparator.comparingInt(p -> p.getPriority()));

        // Queue for ready processes
        Queue<Process> readyQueue = new LinkedList<>();

        ganttchart.add("0");
        boolean completeQuantumTime = false;
        int i = getNextReady(processes, -1, currentTime, ganttchart,
                completeQuantumTime); /* index of first ready process */

        if (i == -1) { // if i == -1 meaning it is idle so we need to find the minimum waiting time to continue at
            ganttchart.add("\033[33mIDLE\033[0m");
            i = handleIDLE(processes);
            ganttchart.add(currentTime + "");
        }
        
        while (processes.size() != 1) {
            // Process runningProcess = processesStatistics.get(i);

            int remainingBurst = processes.get(i).getRemainingBurst();

            ganttchart.add("p" + processes.get(i).getPid());

             if (remainingBurst - quantum <= 0) {

                currentTime += remainingBurst;
                completeQuantumTime = false;

                processes.remove(i);
                if (i >= processes.size()){
                    i = processes.size()-1;
                }
                if (i == -1) { // if i == -1 meaning it is idle so we need to find the minimum waiting time to continue at
                    ganttchart.add("\033[33mIDLE\033[0m");
                    i = handleIDLE(processes);
                    ganttchart.add(currentTime + "");
                }
                
                ganttchart.add((currentTime) + ""); /* converting to string using concatenation */
            } else {
                completeQuantumTime = true;

                processes.get(i).setRemainingBurst(remainingBurst - quantum);
                currentTime = currentTime + quantum;
                ganttchart.add((currentTime) + ""); /* converting to string using concatenation */
            }
            i = getNextReady(processes, i, currentTime, ganttchart, completeQuantumTime);
            if (i == -1) { // if i == -1 meaning it is idle so we need to find the minimum waiting time to continue at
                ganttchart.add("\033[33mIDLE\033[0m");
                i = handleIDLE(processes);
                ganttchart.add(currentTime + "");

            }

            // if processes returned is ahead of current time, make CPU idle
            if (processes.get(i).getArrivalTime() > currentTime){ 
                ganttchart.add("\033[33mIDLE\033[0m");
                currentTime = processes.get(i).getArrivalTime();
                ganttchart.add(currentTime + "");
            }


        }
        ganttchart.add("p" + processes.get(0).getPid());
        ganttchart.add(currentTime + processes.get(0).getRemainingBurst() + "");

    }

    public int handleIDLE(ArrayList<Process> processes){
        int i = 0;
        int minimumArrival = Integer.MAX_VALUE;
        for (int x = 0; x < processes.size();x++){
            boolean isReady = processes.get(x).getArrivalTime() <= minimumArrival;
            if (isReady){
                i = x;
                minimumArrival = processes.get(i).getArrivalTime();
                currentTime = processes.get(i).getArrivalTime();
            } 
        }
        return i;
    }

    public int ArrivedProcessWithMorePriority(int currentIndex) {

        Process currentProcess = processes.get(currentIndex);

        for (int i = 0; i < processes.size(); i++) {
            if (i == currentIndex) {
                continue;
            }
            Process p = processes.get(i);

            boolean ariveBeforeComplete = (Math.min(currentTime + currentProcess.getBurstTime(), quantum) > p
                    .getArrivalTime());
            boolean morePriority = (currentProcess.getPriority() > p.getPriority());

            if (ariveBeforeComplete && morePriority) {
                return p.getArrivalTime() - currentTime;
            }
        }

        return -1;
    }

    public int getNextReady(ArrayList<Process> processes, int currentIndex, int currentTime,
            ArrayList<String> ganttchart, boolean completeQuantumTime) {
        /* Return first ready process, they are already sorted by priority */
        int size = processes.size();
        int indexToReturn;
        int j = 0;

        if (processes.size() == 1) {
            return 0;
        }

        if (currentIndex == -1) {
            for (int i = 0; i < processes.size(); i++) {
                boolean isArrived = processes.get(i).getArrivalTime() <= currentTime;
                if (isArrived) {
                    return i;
                }
            }
        }

        // Process currentProcess = processes.get(currentIndex);

        // System.out.println("current :" + currentIndex);
        // for (int index = 0; index < processes.size(); index++) {
        //     System.out.println(processes.get(index).getPid());  
        // }
        // System.exit(1);
        Process currentProcess = processes.get(currentIndex);

        // check for the same priority number

        // System.out.println("numOfEqualPriority: " + numOfEqualPriority);

        if (completeQuantumTime) {
            int numOfEqualPriority = 0;
            for (int k = 0; k < processes.size(); k++) {
                Process nextProcess = processes.get(k);
                boolean isArrived = nextProcess.getArrivalTime() <= currentTime;
                boolean isEqualPriority = nextProcess.getPriority() == currentProcess.getPriority();
                if (isArrived && isEqualPriority) {
                    numOfEqualPriority++;
                }
            }

            int k = (currentIndex + 1) % numOfEqualPriority;

            Process nextProSamePri = processes.get(k);
            boolean isArrivedSamePri = nextProSamePri.getArrivalTime() <= currentTime;
            boolean isEqualOrMorePriority = nextProSamePri.getPriority() <= currentProcess.getPriority();

            if (isArrivedSamePri && isEqualOrMorePriority) {
                return k;
            }
        }

        // check for less priority
        // the same code exept for isEqualOrMorePriority
        for (int i = 0; i < processes.size(); i++) {

            Process nextProcess = processes.get(i);
            boolean isArrived = nextProcess.getArrivalTime() <= currentTime;

            if (isArrived) {
                return i;
            }
        }


        // Start from the current index and check all processes circularly
        return -1;

    }
}
