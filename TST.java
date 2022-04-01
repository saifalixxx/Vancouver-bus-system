// Importing libraries
import org.w3c.dom.Node;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;


public class TST
{
    private final static int TOTAL_STOPS = 10;
    private boolean ifEnded;
    private Node branch;


    public static void registerArray(ArrayList<String> newList)
    {
        for (String s : newList) {
            System.out.println(s);
        }
    }

    TST(String folder)
    {
        branch = null;
        File newFolder = new File(folder);

        try
        {
            Scanner scan = new Scanner(newFolder);
            String nL = "";

            while(scan.hasNextLine())
            {
                nL = scan.nextLine();
                Scanner newScan = new Scanner(nL);
                newScan.useDelimiter(",");
                String [] station = new String[TOTAL_STOPS];

                for(int count = 0; count < TOTAL_STOPS; count++)
                {
                    if(!newScan.hasNext()) {
                        break;
                    }
                        station[count] = newScan.next();
                }
            }
        }
        catch (Exception error)
        {

        }
    }
}
