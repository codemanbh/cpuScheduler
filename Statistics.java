import java.util.Comparator;
import java.util.ArrayList;

public class Statistics {

    private int totalTurnaroundTime = 0;
    private int totalResponseTime = 0;
    private int totalWaitingTime = 0;

    private double avgTurnaroundTime = 0;
    private double avgResponseTime = 0;
    private double avgWaitingTime = 0;

    public void displayStatistics(ArrayList<Process> processesStatistics, ArrayList<String> ganttchart) {
        int numOfProcesses = processesStatistics.size();
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
        System.out.println("-----------------------");

        for (Process p : processesStatistics) {
            // processes1
            p.printProcessInfo();

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

    public static void displayGanttChart(ArrayList<String> ganttchart) {
        int j = 0;
        while (j < ganttchart.size()) {
            if (j == ganttchart.size() - 1) {
                System.out.println(ganttchart.get(j));
            } else {
                String current = ganttchart.get(j);
                if (current.startsWith("p")) {
                    int start = Integer.parseInt(ganttchart.get(j - 1));
                    int end = Integer.parseInt(ganttchart.get(j + 1));
                    j += 2;

                    while (j < ganttchart.size() && ganttchart.get(j).equals(current)) {
                        end = Integer.parseInt(ganttchart.get(j + 1));
                        j += 2;
                    }

                    System.out.print(current + "-" + end);
                    if (j < ganttchart.size() - 1) {
                        System.out.print("-");
                    }
                } else {
                    if (j == 0 || !ganttchart.get(j - 1).equals(current)) {
                        System.out.print(current);
                        if (j < ganttchart.size() - 1) {
                            System.out.print("-");
                        }
                    }
                    j++;
                }
            }
        }
        System.out.println(); // Add a new line at the end for better readability
    }

    // public void displayGanttChartSimple(ArrayList<String> ganttchart) {
    // for (int i = 0; i < ganttchart.size(); i++) {
    // System.out.print(ganttchart.get(i) + "-");
    // }
    // System.out.println();
    // }
}
