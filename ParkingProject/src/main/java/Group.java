import java.util.LinkedList;

public class Group
{
    int    groupID    = 0;
    double price      = 0;
    LinkedList<vehicle> interestedCars = new LinkedList<vehicle>();

    public Group (int groupID,double price)
    {
        this.groupID = groupID;
        this.price = price;
    }

    public int GetGroupId ()
    {
        return this.groupID;
    }
    public void SetGroupID (int groupID)
    {
        this.groupID = groupID;
    }

    public double GetPrice()
    {
        return this.price;
    }
    public void SetPrice(double price)
    {
        this.price = price;
    }

    public void Print()
    {
        //System.out.println("groupID: " + this.groupID + " Price: " + this.price);
    }

    // Cars must register to be notified of the current price.
    public void Register(vehicle v)
    {
        if (!this.interestedCars.contains(v))
            this.interestedCars.add(v);
    }

    // Cars mu unreegister when done.
    public void UnRegister(vehicle v)
    {
        if (this.interestedCars.contains(v))
            this.interestedCars.remove(v);
    }

    // Inform all interested cars of the current price.
    void OnPriceaChanged(double newPrice)
    {
        for(int num=0; num < interestedCars.size(); num++)
        {
            vehicle v = interestedCars.get(num);
            if (v != null)
            {
                v.OnPriceChanged(newPrice);
            }
        }

    }

}
