import java.util.LinkedList;
import java.util.Queue;

public class ParkingPool {
    private final Queue<RegisterParking> orderQueue = new LinkedList<>();

    public synchronized void addOrder(RegisterParking order) {
        orderQueue.add(order);
        notify();  // Notify waiting threads that a new order is available
    }

    public synchronized RegisterParking getOrder() throws InterruptedException {
        while (orderQueue.isEmpty()) {
            wait();  // Wait until an order is available
        }
        return orderQueue.poll();
    }
}