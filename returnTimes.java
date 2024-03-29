// Searching for all trips with a given arrival time, returning full details of all trips matching the
// criteria (zero, one or more), sorted by trip id

import javax.xml.crypto.Data;
import java.util.*;
import java.text.*;
import java.io.*;

public class returnTimes
{
    public static ArrayList<String> readingData()
    {
        ArrayList<String> readingData = new ArrayList<String>();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        String clockLimit = "24:00:00"; // Maximum time allowed

        try
        {
            Date limit = time.parse(clockLimit);
            BufferedReader reader = new BufferedReader(new FileReader("stop_times.txt"));
            reader.readLine();
            String newPar;

            if ((newPar = reader.readLine()) == null)
            {
            }
            else
            {
                do
                {
                    if (!newPar.isEmpty())
                    {
                        String[] shortTerm = newPar.split(",");
                        Date clock = time.parse(shortTerm[1]);

                        if (clock.getTime() < limit.getTime())
                        {
                            readingData.add(newPar);
                        }
                    }
                } while ((newPar = reader.readLine()) != null);
            }
            reader.close();

        }
        catch (IOException | ParseException error)
        {
            System.out.println(error);
        }

        return readingData;
    }

    public static ArrayList<String> searchRoutes(String object, ArrayList<String> inventory)
    {
        ArrayList<String> routes = new ArrayList<String>();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        inventory.forEach(present ->
        {
            String[] split = present.split(",");
            try {
                Date pClock = time.parse(split[1]);
                Date clock = time.parse(object);

                if (pClock.getTime() != clock.getTime())
                {
                    return;
                }
                routes.add(present);
            }
            catch (ParseException error)
            {
                System.out.println(error);
            }
        });
        return routes;
    }

    // This function gets and prints out the results that the user desires to find, this function will be called
    // in the main to allow it to print out the results
    public static boolean fetchOutput(String output)
    {
        ArrayList<String> inventory = readingData();
        ArrayList<String> print = searchRoutes(output, inventory);

        Collections.sort(print); // Built in quick sort function

        if (print.isEmpty())
        {
            System.out.println("There exist no routes for which you have requested");
            return false;
        }
        else
        {
            System.out.println("Here are the existing routes with the same times ↓ ");

            int count = 0;
            while (count < print.size())
            {
                System.out.println(print.get(count));
                count++;
            }
        }
        return true;
    }

}
