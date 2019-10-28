import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class DataModel
{
    String path;
    Group group = null;
   public  LinkedList<DataItem> items = new LinkedList<DataItem>();

    public DataModel(String path, Group group)
    {
        this.path = path;
        this.group = group;
    }

    public void Read() throws IOException
    {
        int    CarID         = 0;
        int    IsArriving    = 0;
        Date   ArrivedAt     = new Date();
        Date   DepartureDate = new Date();
        double Price         = 0;
        int    Notify        = 0;

        int index = 0;

        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;

        while ((line = br.readLine()) != null) {
            index = 0;
            String[] values = line.split(",");
            for (String str : values) {
                // System.out.println(str);
                if(index == 0)
                {
                    CarID = Integer.parseInt(str);
                }
                else if(index == 1)
                {
                  IsArriving = Integer.parseInt(str);
                }
                else if(index == 2)
                {
                   ArrivedAt = new Date(str);
                }
                else if(index == 3)
                {
                   DepartureDate = new Date(str);
                }
                else if(index == 4)
                {
                    Price = Double.parseDouble(str);
                }
                else if (index == 5)
                {
                    Notify = Integer.parseInt(str);
                }

                index++;
              //  System.out.println(str);
            }

            DataItem newitem = new DataItem(CarID, IsArriving,ArrivedAt,DepartureDate,this.group.GetPrice(),Notify);
            // newitem.Print();
            items.add(newitem);
///////////////////////////
        }
        br.close();
    }
}

// CarID arriving or leaving, ArrivalTIme, Departure Time, price
// 1, 1, 10/20/2019 2:10:00, 10/20/2019 2:10:00, 1.2
// 2, 1, 10/20/2019 2:10:00, 10/20/2019 2:10:00, 1.2
// 2, 0, 10/20/2019 2:10:00, 10/20/2019 3:10:00, 1.2
// 1, 0, 10/20/2019 2:10:00, 10/20/2019 3:10:00, 1.2
