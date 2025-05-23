import java.util.*;

public class KthSmallest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();

        // Input number of elements
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        // Input the elements
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }

        // Input k
        System.out.print("Enter k: ");
        int k = scanner.nextInt();

        // Check if k is valid
        if (k > 0 && k <= list.size()) {
            Collections.sort(list); // Sort the list
            System.out.println("The " + k + "th smallest element is: " + list.get(k - 1));
        } else {
            System.out.println("Invalid value of k!");
        }

        scanner.close();
    }
}
