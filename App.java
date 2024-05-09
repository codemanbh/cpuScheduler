import java.util.Scanner;

class App {

    int[][] processes = new int[4][50];
    int numOfProcesses = 0;
    Scanner input = new Scanner(System.in);
    int algorithm = 0;

    public void entryPrompt() {
        System.out.println("what type of scheduling do you want to chose ?\n" +
                "1) Priority Scheduling with Round Robin\n" +
                "2) Shortest Job First with Round Robin");

        try {
            algorithm = input.nextInt();

            if (algorithm == 1) {
                // do PriorityWithRoundRobin
            } else if (algorithm == 2) {
                // do Shortest Job First with Round Robin

            } else {
                System.out.println("Please enter a valid algrithm number");
            }
        } catch (Exception e) {

        }

        // TODO: ask the user to enter the process information, and add it to the
        // processes array

    }

    public void PriorityWithRoundRobin() {
        for (int i = 0; i < numOfProcesses; i++) {

        }
    }

    public static void main(String[] args) {

    }
}