import java.util.ArrayList;
import java.util.Optional;

public class PRR {
    private ArrayList<Process> processes;
    private int currentTime = 0;

    public static void main(String[] args) {
        ArrayList<Process> pro = new ArrayList<Process>();

        pro.add(new Process(1, 0, 1, 2));
        pro.add(new Process(2, 1, 7, 6));
        pro.add(new Process(3, 2, 3, 3));
        pro.add(new Process(4, 3, 6, 5));
        pro.add(new Process(5, 4, 5, 4));

        PRR prr = new PRR(pro);

        System.out.println(

                prr.getCurrentProcess());

    }

    PRR(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public boolean isAllCompleted() {

        boolean completed = processes.stream().anyMatch(obj -> obj.getRemainingBurst() > 0);
        return completed;
    }

    public int getCurrentProcess() {
        int next = 0;

        Optional<Integer> pro = processes.stream().filter(p -> p.isArrived(currentTime)).map(Process::getPriority)
                .max(Integer::compare);
        if (pro.isPresent()) {
            return pro.getPid();
        } else {
            return -1;

        }

    }

    public void AhmedAlgo() {
        int running;

        while (!isAllCompleted()) {

            for (Process p : processes) {

            }

            currentTime++;
        }
    }
}
