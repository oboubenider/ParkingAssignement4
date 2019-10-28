public class vehicle
{
    int        vehicleID = 0;
    boolean    Notify = false;
    ticket     AssignedTicket = null;
    ParkingLot lot = null;
    DataItem   data = null;

    public vehicle(DataItem item, ParkingLot lot)
    {
        data           = item;
        vehicleID      = item.CarID;
        Notify         = item.MustNotify();
        this.lot       = lot;
        AssignedTicket = new ticket(item.ArrivedAt, item.DepartureDate);

        // when needed cars can register for current price changes.
        if (Notify)
            lot.group.Register(this);
    }

    public double pay()
    {
        AssignedTicket.IsDone();
        return AssignedTicket.DurationofStay() * lot.lowestPrice;
    }

    void InquirePrice()
    {
        System.out.println("Current Price: " + lot.GetCurrentPrice());
    }

    void Inquireavailblity()
    {
        System.out.println("Current availablity: " + lot.GetAvailability());
    }

    public void OnPriceChanged(double newPrice)
    {
        System.out.println("New Price: " + newPrice);
    }
}
