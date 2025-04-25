import java.util.Scanner;

public class WaitForInput {
    public static void WaitForEnterInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // Program execution will pause here until Enter is pressed
        System.out.println("Continuing after Enter is pressed.");
        //scanner.close();
    }

    public static void WaitSeconds (int waitSeconds) {
        System.out.println("Start");
        try {
            Thread.sleep(3000); // Wait for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End");
    }
}

