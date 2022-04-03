// Sets up direct edges and vertices to allow the class to tack Q1 and also Q2 on the paper

import java.util.Iterator;

public class Edge<Object> implements Iterable<Object>
{
    private Node newNode;

    private class Node
    {
        Object Object;
        Node thereAfter;
    }

    private class triverseInven implements Iterator<Object>
    {
        private Node present = newNode;

        public boolean hasNext()
        {
            return present != null;
        }

        public Object next()
        {
            Object Object = present.Object;
            present =  present.thereAfter;
            return Object;
        }
    }

    void add(Object Object)
    {
        Node previous = newNode;
        newNode = new Node();
        newNode.Object = Object;
        newNode.thereAfter = previous;
    }

    public Iterator<Object> iterator()
    {
        return new triverseInven();

    }
}

// This class sets up vertices, starting from no edges at all.
class costOfGrid
{
    private static final String NEXT_PAR = System.getProperty("line.separator");
    private final int TEMP_VAR;
    private int VAR;
    private Edge<costOfEdge>[] diE;

    public costOfGrid(int TEMP_VAR)
    {
        this.TEMP_VAR = TEMP_VAR;
        this.VAR = 0;
        diE = (Edge<costOfEdge>[]) new Edge[TEMP_VAR];

        int count = 0;
        while (count < TEMP_VAR) {
            diE[count] = new Edge<costOfEdge>();
            count++;
        }
    }

    public void connect(costOfEdge cost)
    {
        int var = cost.from();
        diE[var].add(cost);
        VAR++;

    }
/*
    public Iterable<costOfEdge> diE(int var)
    {
        return diE[var];
    }

    public int TEMP_VAR()
    {
        return TEMP_VAR;
    }

    public int VAR()
    {
        return VAR;
    }

} */

// Allows me to paste immutable data-only classes.
record costOfEdge(int var, int tempVar, double cost)
{
    public int from()
    {
        return var;
    }

    public int to()
    {
        return tempVar;
    }
}


