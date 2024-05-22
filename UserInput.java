import java.util.Scanner;

public class UserInput {

    Scanner input = new Scanner(System.in);

    // ask the user to enter only numbers
    // if the type is not a number, it will ask him again
    public int takeNumber() {
        int value;
        while (true) {
            try {
                value = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Must be a number !");
            }
        }
        return value;

    }

    public boolean isNumberValid(int number, String rule) {
        if (rule.equals("onlyPositive")) {
            return number > 0;
        } else if (rule.equals("canBeZero")) {
            return number >= 0;
        }
        return true;
    }

    public int getIntInput(String prompt, String rule) {
        // onlyPositive
        // canBeZero
        // canBeNegative
        int value;
        System.out.print(prompt);

        value = takeNumber();

        while (!isNumberValid(value, rule)) {
            System.out.println("Input is not valid");
            value = takeNumber();

        }

        return value;
    }

}
