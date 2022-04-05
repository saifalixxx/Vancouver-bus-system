// Reference: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/IndexMinPQ.java.html
// Reference: https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Stack.java.html


import java.util.Iterator;
import java.util.NoSuchElementException;

public class IMPQ<Ans extends Comparable<Ans>>
{
    private int limit;
    private int integer;
    private int [] binaryHeap;
    private int [] inverseHeap;
    private Ans[] ans;

    public IMPQ(int limit)
    {
        this.limit = limit;
        integer = 0;
        ans = (Ans[]) new Comparable [limit + 1];
        binaryHeap = new int [limit + 1];
        inverseHeap = new int [limit + 1];

        int count = 0;
        while (count <= limit)
        {
            inverseHeap[count] = -1;
            count++;
        }
    }

    public boolean clear()
    {
        return integer == 0;
    }

    public boolean contains(int count)
    {
        return inverseHeap[count] != -1;
    }

    public void input(int count, Ans newAns)
    {
        integer++;
        inverseHeap[count] = integer;
        binaryHeap[integer] = count;
        ans[count] = newAns;
        hHelper(integer);
    }

    public int delete()
    {
        int least = binaryHeap[1];
        general(1, integer--);
        drop(1);
        inverseHeap[least] = - 1;
        ans[least] = null;
        binaryHeap[integer + 1] = -1;

        return least;
    }

    public void reduce(int count, Ans newAns)
    {
        ans[count] = newAns;
        hHelper(inverseHeap[count]);
    }

    private boolean larger(int count, int y)
    {
        return ans[binaryHeap[count]].compareTo(ans[binaryHeap[count]]) > 0;
    }

    private void general(int count, int y )
    {
        int reverse = binaryHeap[count];
        binaryHeap[count] = binaryHeap[y];
        binaryHeap[y] = reverse;
        inverseHeap[binaryHeap[count]] = count;
        inverseHeap[binaryHeap[y]] = y;
    }

    private void hHelper(int h)
    {
        while(h > 1 && larger(h/2, h))
        {
            general(h, h/2);
            h = h/2;
        }
    }

    private void drop(int h)
    {
        if (2 * h <= integer)
        {
            do {
                int y = 2 * h;

                if (y >= integer || !larger(y, y + 1))
                {
                }
                else
                {
                    y++;
                }
                if (!larger(h, y)) {
                    break;
                }

                general(h, y);
                h = y;
            } while (2 * h <= integer);
        }
    }
}

class Queue<Object> implements Iterable<Object>
{
    private Node<Object> start;
    private int integer;

    private static class Node<Object>
    {
        private Object object;
        private Node<Object> after;
    }

    public Queue()
    {
        start = null;
        integer = 0;
    }

    public void push(Object objects)
    {
        Node<Object> previous = start;
        start = new Node<Object>();
        start.object = objects;
        start.after = previous;
        integer++;
    }

    public String parse()
    {
        StringBuilder string = new StringBuilder();

        for (Iterator<Object> iterator = this.iterator(); iterator.hasNext();)
        {
            Object objects = iterator.next();
            string.append(objects);
            string.append(' ');
        }

        return string.toString();
    }

    public Iterator<Object> iterator()
    {
        return new triverse(start);
    }

    private class triverse implements Iterator<Object>
    {
        private Node<Object> present;

        public triverse(Node<Object> start)
        {
            present = start;
        }

        public boolean hasNext()
        {
            if (present != null) return true;
            else return false;
        }

        public void delete()
        {
            throw new UnsupportedOperationException();
        }

        public Object next()
        {
            assert hasNext();
                Object object = present.object;
                present = present.after;
                return object;

        }
    }
}


