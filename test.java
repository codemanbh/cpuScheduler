import java.lang.Thread;

public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // Handle the exception
                e.printStackTrace();
            }
        }

    }
}
