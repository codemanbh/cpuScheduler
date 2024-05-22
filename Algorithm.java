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
        // processes.sort(Comparator.comparingInt(p -> p.getArrivalTime()));

        processes.sort(Comparator.comparingInt(p -> p.getPriority()));

        // Queue for ready processes
        Queue<Process> readyQueue = new LinkedList<>();

        ganttchart.add("0");
        int i = getNextReady(processes, 0, currentTime, ganttchart); /* index of first ready process */

        while (processes.size() != 1) {
            // Process runningProcess = processesStatistics.get(i);

            int remainingBurst = processes.get(i).getRemainingBurst();

            ganttchart.add("p" + processes.get(i).getPid());

            int timeUntilMorePriority = ArrivedProcessWithMorePriority(i);

            if (timeUntilMorePriority > 0) {
                currentTime += timeUntilMorePriority;

                processes.get(i).setRemainingBurst(remainingBurst - timeUntilMorePriority);
                ganttchart.add((currentTime) + "");

            } else if (remainingBurst - quantum <= 0) {

                currentTime += remainingBurst;

                processes.remove(i);

                ganttchart.add((currentTime) + ""); /* converting to string using concatenation */
            } else {
                processes.get(i).setRemainingBurst(remainingBurst - quantum);
                currentTime = currentTime + quantum;
                ganttchart.add((currentTime) + ""); /* converting to string using concatenation */
            }
            i = getNextReady(processes, i, currentTime, ganttchart);
            if (i == -1) {
                break;
            }

        }
        ganttchart.add("p" + processes.get(0).getPid());
        ganttchart.add(currentTime + processes.get(0).getRemainingBurst() + "");

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
            ArrayList<String> ganttchart) {
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
