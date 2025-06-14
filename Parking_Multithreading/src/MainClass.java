import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        ParkingPool pool = new ParkingPool();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of cars: ");
        int carCount = scanner.nextInt();

        System.out.print("Enter number of agents: ");
        int agentCount = scanner.nextInt();

        // Start parking agents
        for (int i = 1; i <= agentCount; i++) {
            new ParkingAgent(pool, i).start();
        }

        // Simulate adding parking orders
        for (int i = 1; i <= carCount; i++) {
            RegisterParking order = new RegisterParking(i);
            pool.addOrder(order);
            Thread.sleep(50); // Simulate car arriving at different times
        }

        scanner.close();
    }
}