import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ParkingManager {

    LinkedList<ParkingLot> ParkingLots = new LinkedList<ParkingLot>();
    GroupManager groupManager = new GroupManager();

    public ParkingManager() throws IOException {
        groupManager.Read();
    }

    public ParkingLot FindById(int Id)
    {
        for(int num=0; num < ParkingLots.size(); num++)
        {
            ParkingLot p = ParkingLots.get(num);
            if (p != null)
            {
                if (p.GetById() == Id)
                    return p;
            }
        }
        return null;
    }

    public void Read() throws IOException {
        int    Id       = 0;
        String location = "";
        int    groupID  = 0;
        int    capacity = 0;
        String model    = "";
        int    index    = 0;

        BufferedReader br = new BufferedReader(new FileReader("Config.txt"));
        String line = null;

        while ((line = br.readLine()) != null)
        {
            index = 0;
            String[] values = line.split(",");
            for (String str : values) {
                if (index == 0) {
                    Id = Integer.parseInt(str);
                } else if (index == 1) {
                    location = str;
                }
                else if (index == 2) {
                    groupID = Integer.parseInt(str) ;
                }
                else if (index == 3) {
                    capacity = Integer.parseInt(str) ;
                }
                else if (index == 4) {
                    model = str ;
                }

                index++;
            }

            // Find Group
            Group g = groupManager.FindById(Id);

            // Create parkingLot instance
            ParkingLot lot = new ParkingLot(Id,location,model,capacity,g,groupManager.GetLowestPrice());
            ParkingLots.add(lot);
            lot.Print();
            lot.Handle();
        }
        br.close();
    }
}
