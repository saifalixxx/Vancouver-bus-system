import java.util.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.text.ParseException;


import java.util.stream.IntStream;

public class Main
{
    public static void getGraph()
    {
        boolean isAccepted = false;

        do
        {

            if (isAccepted)
            {
                break;
            }

            if (Graph.bestRoute())
            {
                isAccepted = true;
            }
        } while (true);
    }

    public static void getStationInfo()
    {
        Scanner uEntered = new Scanner(System.in);
        stationInfo sI = new stationInfo("stops.txt");
        boolean isAccepted = false;

        if (!isAccepted)
        {
            do
            {
                System.out.println("Please input your stop:");
                String iS = uEntered.nextLine();

                if (sI.stationData(iS.toUpperCase())) {
                    isAccepted = true;
                }

            } while (!isAccepted);
        }

        uEntered.close();
    }

    public static void getReturnTimes()
    {
        Scanner uEntered = new Scanner(System.in);
        boolean ifAccepted = false;
        DateFormat clock = new SimpleDateFormat("HH:mm:ss");
        clock.setLenient(false);

        if (!ifAccepted)
        {
            do
            {
                System.out.println("Please input the appropriate arrival time in the format [HH:MM:SS]:");
                String enterClock = uEntered.next();

                try
                {
                    clock.parse(enterClock);

                    if (returnTimes.fetchOutput(enterClock))
                    {
                        ifAccepted = true;
                    }
                } catch (ParseException error)
                {
                    System.out.println("Error- incorrect arrangement. Please try again \n");
                }

            } while (!ifAccepted);
        }
        uEntered.close();
        ;
    }

    static class stationInfo
    {
        public final String[] CLASSIFICATION = new String[]{"FLAGSTOP", "WB", "NB", "SB", "EB"};
        public final String[] STATION_DATA = new String[]{"Station ID", "\nStation code", "\nStation name", "\nStation info",
                "\nStation latitude", "\nStation longitude", "\nLocation ID", "\nStation URL","\n"};
        public static final int STATION_MAX = 2;
        TST<String[]> T;
        List<String> enterKey;

        public boolean ifSimilar(String stopName)
        {
            String[] stopWords = stopName.split(" ");
            return IntStream.range(0, enterKey.size()).allMatch(i -> stopWords[i].equals(enterKey.get(i)));
        }

        public String alteredStation(String stationName, boolean isAccepted)
        {
            String[] characters = stationName.split(" ");
            enterKey = new ArrayList<String>();

            if (!Arrays.asList(CLASSIFICATION).contains(characters[0]))
            {
                return stationName;
            }
            do
            {
                String charOne = characters[0];
                stationName = stationName.replace(charOne, "").trim();

                if (!isAccepted)
                {
                    stationName = stationName + " " + charOne;
                }
                else
                {
                    enterKey.add(charOne);

                }

                characters = stationName.split(" ");
            } while (Arrays.asList(CLASSIFICATION).contains(characters[0]));

            return stationName;
        }

        public stationInfo(String folder)
        {
            T = new TST<String[]>();

            try
            {
                Scanner newScanner = new Scanner(new File(folder));
                newScanner.nextLine();

                if (!newScanner.hasNextLine())
                {
                    return;
                }
                do
                {
                    String station = newScanner.nextLine();
                    String[] stationInfo = station.split(",");
                    String stationName = stationInfo[STATION_MAX];
                    String fixedStation = alteredStation(stationName, false);
                    T.place(fixedStation, stationInfo);

                } while (newScanner.hasNextLine());
            }
            catch (FileNotFoundException error)
            {
                throw new IllegalArgumentException("Folder does not exist");
            }
        }

        public boolean stationData(String stationName)
        {
            switch (stationName.replaceAll(" ", ""))
            {
                case "" -> System.out.println("Key must have length greater than or equal to 1");
                default ->
                        {
                    List<String[]> stInfo = recieveData(stationName);
                            if (stInfo != null && stInfo.size() != 0)
                            {
                                for (String[] stationS : stInfo)
                                {
                                    String result = "";
                                    System.out.println("Station details: " + stationS[STATION_MAX]);
                                    int x = 0;

                                    while (x < stationS.length - 1)
                                    {
                                        result += (STATION_DATA[x] + ": " + (!stationS[x].replaceAll(" ", "").equals("") ? stationS[x] : ""));
                                        x++;
                                    }

                                    System.out.print(result.substring(0, result.length() - 2));

                                    System.out.println("\n↓");
                                }
                                System.out.println("Thank you 😀");
                            }
                            else
                            {
                                System.out.println("No stations exist");
                                return false;
                            }
                        }
            }
            return true;
        }

        public List<String[]> recieveData(String stationName)
        {
            String alterN = alteredStation(stationName, true);
            List<String[]> stationData = T.receive(alterN);

            if (stationData != null && stationData.size() != 0)
            {
                String sN;
                int count = 0;

                do
                {
                    if (count >= stationData.size())
                    {
                        break;
                    }
                    sN = stationData.get(count)[STATION_MAX];

                    if (!ifSimilar(sN))
                    {
                        stationData.remove(count);
                    }
                    else
                    {
                        count++;
                    }

                } while (true);

                return stationData;
            }
            else
            {
                return null;
            }
        }

    }

    static class userInterface
    {
        public static void main(String[] args)
        {
            Scanner user = new Scanner(System.in);
            boolean ifAccepted = false;

            while(!ifAccepted)
            {
                System.out.println("**********************");
                System.out.println(" VANCOUVER BUS SYSTEM");
                System.out.println("**********************");
                System.out.println("Please input the information you are looking for:");
                System.out.println("Press 1 ---> To find shortest path");
                System.out.println("Press 2 ---> To find bus stop");
                System.out.println("Press 3 ---> To find all routes within a certain time");
                System.out.println("Or type exit to quit the program ");

                if(user.hasNextLine())
                {
                    String option = user.nextLine();

                    if (option.equalsIgnoreCase("Exit"))
                    {
                        System.out.println("The program has now closed");
                        break;

                    }
                    else if (option.equals("1"))
                    {
                        ifAccepted = true;
                        getGraph();


                    }
                    else if (option.equals("2"))
                    {
                        ifAccepted = true;
                        getStationInfo();

                    }
                    else if (option.equals("3"))
                    {
                        ifAccepted = true;
                        getReturnTimes();

                    }
                    else
                    {
                        System.out.println("Input has not been accepted");
                    }
                }

                else
                {
                    System.out.println("This program takes integers only");
                    user.nextLine();
                }

            }
            user.close();
        }
    }
}


