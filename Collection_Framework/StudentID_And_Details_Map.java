import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Student {
    String name;
    int age;
    String department;

    Student(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Department: " + department;
    }
}

public class StudentID_And_Details_Map {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<Integer, Student> studentMap = new TreeMap<>();

        System.out.print("Enter number of students: ");
        int n = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nStudent " + i + ":");

            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.print("Department: ");
            String dept = scanner.nextLine();

            studentMap.put(id, new Student(name, age, dept));
        }

        System.out.println("\nStudent Records (sorted by ID):");
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
        }

        scanner.close();
    }
}
