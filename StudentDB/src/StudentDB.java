import java.sql.*;
import java.util.Scanner;

public class StudentDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/school";
        String user = "root"; // Replace with your DB username
        String password = "hamidpiash"; // Replace with your DB password

        Scanner scanner = new Scanner(System.in);

        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            while (true) {
                // Show menu
                System.out.println("\nMenu:");
                System.out.println("1. Add student");
                System.out.println("2. View all students");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline

                if (choice == 1) {
                    // Add student
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    String insertQuery = "INSERT INTO students (name, age) VALUES (?, ?)";
                    PreparedStatement ps = conn.prepareStatement(insertQuery);
                    ps.setString(1, name);
                    ps.setInt(2, age);
                    ps.executeUpdate();

                    System.out.println("Student added successfully!");

                } else if (choice == 2) {
                    // View all students
                    System.out.println("\nAll students:");
                    ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");

                    System.out.println("ID\tName\t\tAge");
                    System.out.println("-----------------------------");
                    while (rs.next()) {
                        System.out.printf("%d\t%-10s\t%d\n",
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("age"));
                    }

                } else if (choice == 3) {
                    // Exit
                    System.out.println("Exiting...");
                    conn.close();
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
