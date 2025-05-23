import java.util.LinkedList;

public class LinkedListEquality {
    public static boolean areEqual(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        // Check if both lists are null or have the same size and elements
        if (list1 == null || list2 == null) return false;

        if (list1.size() != list2.size())
            return false;


        // Compare elements one by one
        for (int i = 0; i < list1.size(); i++)
            if (!list1.get(i).equals(list2.get(i)))
                return false;



        return true;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();

        // Example values
        list1.add(10);
        list1.add(20);
        list1.add(30);

        list2.add(10);
        list2.add(20);
        list2.add(40);

        if (areEqual(list1, list2))
            System.out.println("The LinkedLists are equal.");
        else
            System.out.println("The LinkedLists are not equal.");

    }
}