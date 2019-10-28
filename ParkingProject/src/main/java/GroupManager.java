import java.io.*;
import java.util.LinkedList;

public class GroupManager {
    // read from Group.txt
    LinkedList<Group> Groups = new LinkedList<Group>();

    public Group FindById(int id)
    {
        for(int num=0; num < Groups.size(); num++)
        {
            Group g = Groups.get(num);
            if (g != null)
            {
                if (g.GetGroupId() == id)
                    return g;
            }
        }
        return null;
    }

    public void Read() throws IOException {
        int groupID = 0;
        double Price = 0;

        int index = 0;

        BufferedReader br = new BufferedReader(new FileReader("Group.txt"));
        String line = null;

        while ((line = br.readLine()) != null) {
            index = 0;
            String[] values = line.split(",");
            for (String str : values) {
                if (index == 0) {
                    groupID = Integer.parseInt(str);
                } else if (index == 1) {
                    Price = Double.parseDouble(str);
                }
                index++;
            }

            Group group = new Group(groupID, Price);
            Groups.add(group);

            group.Print();
        }
        br.close();
    }

    public double GetLowestPrice()
    {
        double lowestPrice = 99999999.0;

        for(int num=0; num < Groups.size(); num++)
        {
            Group g = Groups.get(num);
            if (g != null)
            {
                if (g.GetPrice() < lowestPrice)
                    lowestPrice = g.GetPrice();
            }
        }

        return lowestPrice;
    }
}