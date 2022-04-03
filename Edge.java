import java.util.Iterator;

public class Edge<Object> implements Iterable<Object>
{
    private Node newNode;

    private class Node
    {
        Object Object;
        Node thereAfter;
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
