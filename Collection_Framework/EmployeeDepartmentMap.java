import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeDepartmentMap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> employeeMap = new HashMap<>();

        System.out.print("Enter the number of employees: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for employee " + (i + 1) + ":");

            System.out.print("Employee ID (integer): ");
            int empId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Department: ");
            String department = scanner.nextLine();

            employeeMap.put(empId, department);
        }

        // Display all mappings
        System.out.println("\nEmployee ID to Department Mappings:");
        for (Map.Entry<Integer, String> entry : employeeMap.entrySet())
            System.out.println("Employee ID: " + entry.getKey() + " -> Department: " + entry.getValue());


        scanner.close();
    }
}