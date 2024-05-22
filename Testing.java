// public class Testing {
// public static void main(String[] args) {
// // int[][] test1 = { { 1, 0, 5, 5 }, { 2, 0, 8, 3 }, { 3, 2, 1, 2 }, { 4, 10,
// 3,
// // 6 } };
// processes1.add(new Process(1, 0, 5, 5));
// processes1.add(new Process(2, 0, 8, 3));
// processes1.add(new Process(3, 2, 1, 2));
// processes1.add(new Process(4, 10, 3, 6));

// }
// }

import java.util.ArrayList;

class Testing {
    public static void main(String[] args) {

    }

    public static void runTest(ArrayList<Process> processes, int testNum) {
        switch (testNum) {

        case 0:
            // preemption test
            processes.add(new Process(1, 0, 5, 5));
            processes.add(new Process(2, 0, 8, 3));
            processes.add(new Process(3, 2, 1, 2));
            processes.add(new Process(4, 10, 3, 6));
            break;

        case 1:
            // IDEL test
            processes.add(new Process(1, 0, 1, 5));
            processes.add(new Process(2, 8, 1, 3));
            break;
        case 2:
            // start the highest priority first
            processes.add(new Process(1, 0, 5, 2));
            processes.add(new Process(2, 0, 10, 1));
            break;
        case 3:
            // check the rourn roben
            processes.add(new Process(1, 0, 10, 1));
            processes.add(new Process(2, 0, 10, 1));
            break;
        }
    }
}
