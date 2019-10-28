import java.util.Date;

public class ticket
{
    Date   ArrivalTime;
    Date   DepartureTime;
    int    TicketID;
    static int nextTicket = 0;

    public ticket(Date arrivedAt, Date departAt)
    {
        ArrivalTime = arrivedAt;
        DepartureTime = departAt;
        TicketID = nextTicket ++;
    }

    public void IsDone()
    {
        DepartureTime = new Date();
    }

    public long DurationofStay()
    {
        return (DepartureTime.getTime() - ArrivalTime.getTime())/(60 * 60 * 1000) % 24;
    }
}
