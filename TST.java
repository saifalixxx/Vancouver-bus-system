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

                String titleSt = station[2];
                String title = titleSt.substring(0,2);

                // Move keywords flagstop, wb, nb, sb, eb from start of the names to the end of the names of the stops
                if(title.equals("NB") || title.equals("WB") || title.equals("SB") || title.equals("EB"))
                {
                    titleSt = titleSt.substring(3).concat(" " + title);
                }

                stData data = new stData(station);
                connect(titleSt.toCharArray(), data);
                newScan.close();
            }
            scan.close();
        }
        catch (FileNotFoundException error)
        {
            error.printStackTrace();
        }
    }

    private void connect(char [] stID, stData data)
    {
        if(stID.length != 0)
        {
            if(branch == null)
            {
                branch = new Node(stID[0], -1);
                ifEnded = false;
                connect(stID, 0, branch, data);
            }
        }
    }

    private Node connect(char[] stID, int count, Node newNode, stData data)
    {
        if(newNode == null)
        {
            newNode = new Node(stID[count], -1);
        }
        if(newNode.ans < stID[count])
        {
            newNode.l = connect(stID, count, newNode.l, data);
        }
        else if()
    }
}
