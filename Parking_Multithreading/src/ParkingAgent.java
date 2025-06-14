public class ParkingAgent extends Thread {
    private final ParkingPool pool;
    private final int agentId;

    public ParkingAgent(ParkingPool pool, int agentId) {
        this.pool = pool;
        this.agentId = agentId;
    }

    @Override
    public void run() {
        try {
            while(true) {
                RegisterParking order = pool.getOrder();
                System.out.println("Agent " + agentId + " is processing car " + order.getCarId());
                Thread.sleep(100);  // Simulate time taken to park
                order.park();
            }
        } catch (InterruptedException e) {
            System.out.println("Agent " + agentId + " interrupted.");
        }
    }
}