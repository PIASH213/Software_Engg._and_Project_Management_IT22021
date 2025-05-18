package student.main;

import java.util.Scanner;
import student.services.StudentValidator;
import student.exceptions.UnderAgeException;
import student.exceptions.InvalidDepartmentException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        try {
            StudentValidator.validate(age, department);
            System.out.println("Valid");
        } catch (UnderAgeException | InvalidDepartmentException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        scanner.close();
    }
}
