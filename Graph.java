// Finding shortest paths between 2 bus stops (as input by the user), returning the list of stops
// en route as well as the associated “cost

import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.io.FileNotFoundException;

public class Graph
{
    public costOfGrid grid;
    public HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(); // Store index of ID in Hashmap

    public int location = 0;

    public static int insert(Graph shortest, String enter)
    {
        Scanner newScanner = new Scanner(System.in);
        boolean accepted = false;
        int end = -1;

        if (!accepted)
        {
            do
            {
                System.out.println(enter);

                if (!newScanner.hasNext())
                {
                    System.out.println("This type of input is not accepted, please try again");
                    newScanner.nextLine();
                }
                else
                {
                    end = newScanner.nextInt();

                    if (!shortest.hash.containsKey(end))
                    {
                        System.out.println("ID number does not exist 😔");
                    } else
                    {
                        accepted = true;
                    }
                }
            } while (!accepted);
        }
        return end;
    }

    Graph(String folder) // Initialise functions
    {
        grid = setEdge(folder);
        grid = readClock();
        grid = readRoutes();
    }

    public costOfGrid setEdge(String folder)
    {
        if (folder != null)
        {
            File newFolder = new File(folder);

            int totalEdge = 0;

            try
            {
                Scanner newScanner = new Scanner(newFolder);

                if (newScanner.hasNextLine())
                {
                    do
                    {
                        totalEdge++;
                        newScanner.nextLine();
                    } while (newScanner.hasNextLine());
                }

                totalEdge -= 1;
                grid = new costOfGrid(totalEdge);
            }
            catch (FileNotFoundException error)
            {
                System.out.println("Error folder does not exist");
                error.printStackTrace();
            }
            return grid;
        }
        else
        {
            return null;
        }

    }

    public costOfGrid readClock() // Iterates through stop times and individually splits them as characters
    {
        File newFolder = new File("stop_times.txt");

        try
        {
            Scanner newScanner = new Scanner(newFolder);
            String parOne = newScanner.nextLine();
            String presentPar = newScanner.nextLine();

            if (newScanner.hasNextLine())
            {
                do
                {
                    String newPar = newScanner.nextLine();
                    String[] presentParData = presentPar.split(",");
                    String[] newParData = newPar.split(",");

                    if (!presentParData[0].equalsIgnoreCase(newParData[0]))
                    {
                    }
                    else
                    {
                        String stData = presentParData[3];
                        int temp = Integer.parseInt(stData);

                        if (hash.get(temp) != null) {
                        }
                        else
                        {
                            hash.put(temp, location);
                            location++;
                        }

                        String newStData = newParData[3];
                        int newTemp = Integer.parseInt(newStData);

                        if (hash.get(newTemp) != null) {
                        }
                        else
                        {
                            hash.put(newTemp, location);
                            location++;
                        }

                        double cost = 1;
                        costOfEdge coe = new costOfEdge(hash.get(temp), hash.get(newTemp), cost);
                        grid.connect(coe);
                    }
                    presentPar = newPar;

                } while (newScanner.hasNextLine());
            }
            newScanner.close();
        }
        catch (FileNotFoundException error)
        {
            System.out.println("Folder does not exist");
            error.printStackTrace();
        }
        return grid;
    }

    public costOfGrid readRoutes()
    {
        try
        {
            File newFolder = new File("transfers.txt");
            Scanner newScanner = new Scanner(newFolder);
            String exp = newScanner.nextLine();

            if (newScanner.hasNextLine())
            {
                do
                {
                    String par = newScanner.nextLine();
                    String[] parData = par.split(",");

                    int temp = Integer.parseInt(parData[0]);
                    if (hash.get(temp) == null)
                    {
                        hash.put(temp, location);
                        location++;
                    }

                    int newTemp = Integer.parseInt(parData[1]);
                    if (hash.get(newTemp) == null)
                    {
                        hash.put(newTemp, location);
                        location++;
                    }

                    int routeInfo = Integer.parseInt(parData[2]);
                    double cost = 0;

                    switch (routeInfo)
                    {
                        case 2 -> {
                            double routeHour = Double.parseDouble(parData[3]);
                            cost = routeHour / 100;
                        }
                        case 0 -> cost = 2;
                    }

                    costOfEdge coe = new costOfEdge(hash.get(temp), hash.get(newTemp), cost);
                    grid.connect(coe);

                } while (newScanner.hasNextLine());
            }
        }
        catch (FileNotFoundException error)
        {
            System.out.println("Folder does not exist");
            error.printStackTrace();
        }
        return grid;

    }

    public static boolean bestRoute()
    {
        String folder = "stops.txt";
        Graph shortest = new Graph(folder);
        String data = "Kindly input the stop ID that you're interested in: ";
        String station = "Also input the stop ID of the destination station";
        int origin = insert(shortest, data);
        int dest = insert(shortest, station);
        int location = shortest.hash.get(origin);
        int sLocation = shortest.hash.get(dest);
        Dijkstra newGrid = new Dijkstra(shortest.grid, location);

        if (!newGrid.possessRoute(sLocation))
        {
            System.out.println("No route exists 😔");
        }
        else
        {
            double dist = newGrid.distance(sLocation);
            System.out.println("\nTotal cost = " + dist);
            return true;
        }
        return false;
    }

}
