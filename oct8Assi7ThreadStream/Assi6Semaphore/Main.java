package oct8Assi7ThreadStream.Assi6Semaphore;


import java.util.concurrent.Semaphore;

class ParkingLot {
    // creating 3 slots only -----
    private final Semaphore spots = new Semaphore(3);

    public void parkCar(int carName) {
        try {
            System.out.println("Car - " + carName + " is trying to park...");
            spots.acquire();
            System.out.println("Car - " + carName + " parked successfully...");


            Thread.sleep(2000);
            System.out.println();
            System.out.println("car - " + carName + " is leaving the parking lot...");
            spots.release(); // give back the spot
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot();

        for (int i = 1; i <= 6; i++) {
            int carName = i;
            new Thread(() -> parkingLot.parkCar(carName)).start();
        }
    }
}

