public class RegisterParking {
    private final int carId;

    public RegisterParking(int carId) {
        this.carId = carId;
    }

    public int getCarId() {
        return carId;
    }

    public void park() {
        System.out.println("Car " + carId + " is parked.");
    }
}