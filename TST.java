/*Searching for a bus stop by full name or by the first few characters in the name, using a
 ternary search tree (TST), returning the full stop information for each stop matching the
 search criteria (which can be zero, one or more stops) */

// Reference: https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/TST.html

// Importing libraries
import java.util.ArrayList;
import java.util.List;

public class TST<search>
{
    private newNode<search> branch;
    private List<search> characters;

    private class newNode<search>
    {
        public char newString;
        public search number;
        public newNode<search> directionL;  // Left
        public newNode<search> directionR; // Right
        public newNode<search> directionC; // Centre

        public newNode()
        {
            this.directionL = null;
            this.directionR = null;
            this.directionC = null;
        }
    }

    private void lookForNo(newNode<search> var)
    {
        if (var == null) {
            return;
        }
        lookForNo(var.directionL);
        lookForNo(var.directionC);
        lookForNo(var.directionR);

        if(var.number != null)
        {
            characters.add(var.number);
        }
    }

    // Testing for exceptions and indicating whether the method has been passed as an illegal argument
    public List<search> receive(String ans)
    {
        if (ans != null) {
            if (ans.length() == 0) {
                throw new IllegalArgumentException("Key has to be greater than or equal to 1");
            }

            newNode<search> var = receive(branch, ans, 0);

            if (var == null) {
                return null;
            }

            characters = new ArrayList<search>();

            if (var.directionC.number == null) {
                return characters;
            }
            characters.add(var.number);
            lookForNo(var.directionC);
            return characters;
        } else {
            throw new IllegalArgumentException("Key is null");
        }
    }

    private newNode<search> receive(newNode<search> var, String ans, int cmp) {
        if (var != null) {
            if (ans.length() == 0) {
                throw new IllegalArgumentException("Key has to be greater than or equal to 1");
            }

            char role = ans.charAt(cmp);

            if (role >= var.newString) {
                if (role > var.newString) {
                    return receive(var.directionR, ans, cmp);
                } else {
                    return var;
                }
            } else {
                return receive(var.directionL, ans, cmp);
            }
        } else {
            return null;
        }

    }

    // Is used to compare to see where to input the node value or what direction it should go in result of this
    // comparison.
    private newNode<search> place(newNode<search> var, String ans, search number, int cmp)
    {
        char role = ans.charAt(cmp);

        if (var != null) {
        } else {
            var = new newNode<search>();
            var.newString = role;
        }

        if (role >= var.newString) {
            if(role > var.newString)
            {
                var.directionR = place(var.directionR, ans, number, cmp);
            }
            else if(cmp < ans.length() - 1)
            {
                var.directionC = place(var.directionC, ans, number, cmp + 1);
            }
            else
            {
                var.number = number;
            }
        } else {
            var.directionL = place(var.directionL, ans, number, cmp);
        }

        return var;
    }

    public void place(String ans, search number)
    {
        if (ans != null) {
            branch = place(branch, ans, number, 0);
        } else {
            throw new IllegalArgumentException("Key is null");
        }

    }

}