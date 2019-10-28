import java.io.IOException;
import java.util.LinkedList;

class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            // create instance ParkingLotManager
            ParkingManager manager = new ParkingManager();
            manager.Read();

          //  ParkingLot _lot = new ParkingLot(args[0], 10);
            //_lot.Handle();
        }
        else {
            System.out.println("Usage: exe Config.txt");
        }
    }
}
