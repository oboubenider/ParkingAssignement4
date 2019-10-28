import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.CancellationException;

public class ParkingLot {
    int Id = 0;
    String location = "";
    int capacity = 0;
    String path = "";
    int CurrentCount = 0;
    int AvailableSlots = 0;
    double lowestPrice = 0;
    DataModel model = null;
    Group group = null;
    LinkedList<vehicle> vehicles = new LinkedList<vehicle>();

    public int GetById(){
        return this.Id;
    }

    public ParkingLot(int Id, String location,String path, int capacity, Group group, double lowestPrice) throws IOException {
        this.Id          = Id;
        this.location    = location;
        this.capacity    = capacity;
        this.group       = group;
        this.lowestPrice = lowestPrice;
        this.path        = path;
        model            = new DataModel(path,group);
        model.Read();
    }

    public void Handle()
    {
        for(int num=0; num<model.items.size(); num++)
        {
            DataItem item = model.items.get(num);
            if (item != null)
            {
                //item.Print();
                Process((item));
            }
        }
    }

    void Process(DataItem item)
    {
        if (item == null)
            return;

        if (item.IsArriving == 1) {
            vehicle v = new vehicle(item, this);
            Add(v);
        }
        else {
            remove(item.CarID);
        }
    }

    void Add(vehicle v)
    {
        if (CanAdd())
        {
            System.out.println("vehicle " + v.vehicleID + " ENTERING" );
            vehicles.add(v);
        }
    }

    void remove(int id)
    {
        vehicle v = FindById(id);
        if (v != null) {
            System.out.println("vehicle " + v.vehicleID + " LEAVINGS" );
            v.pay();
            vehicles.remove(v);
        }
    }

    vehicle FindById(int id)
    {
        for(int num=0; num < vehicles.size(); num++)
        {
            vehicle v = vehicles.get(num);
            if (v != null)
            {
                if (v.vehicleID == id)
                    return v;
            }
        }
        return null;
    }

    boolean IsEmpty()
    {
        return vehicles.size() == 0;
    }

    boolean IsFull()
    {
        return vehicles.size() >= capacity;
    }

    boolean CanAdd()
    {
        return !IsFull();
    }

    public int GetAvailability()
    {
        return this.capacity - vehicles.size();
    }

    public  double GetCurrentPrice()
    {
        return this.group.GetPrice();
    }

    public void Print()
    {
        //System.out.println("ID: " + this.Id + " location: " + this.location + " capacity: " + this.capacity + " model: " + this.path + " group: " + this.group.GetGroupId() + " Price: " + this.group.GetPrice());
    }
}
