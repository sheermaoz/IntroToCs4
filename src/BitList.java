/*
I, <name> (<ID>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.LinkedList;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.1 ================================================

    public void addLast(Bit element) {
        if (element == null)
        {
            throw new IllegalArgumentException("Null cannot be added to the list.");
        }

        if (element.toInt() == 1)
        {
            numberOfOnes++;
        }
        super.addLast(element);
    }

    public void addFirst(Bit element) {
        if (element == null)
        {
            throw new IllegalArgumentException("Null cannot be added to the list.");
        }

        if (element.toInt() == 1)
        {
            numberOfOnes++;
        }
        
        super.addFirst(element);
    }

    public Bit removeLast() {
        Bit bit = super.removeLast();
        if (bit.toInt() == 1)
        {
            numberOfOnes--;
        }
        return bit;
    }

    public Bit removeFirst() {
        Bit bit = super.removeFirst();
        if (bit.toInt() == 1)
        {
            numberOfOnes--;
        }
        return bit;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() {
        java.util.Iterator<Bit> itr = super.iterator();
        String output = ">";
        while (itr.hasNext())
        {
            output = itr.next().toString() + output;
        }
        output = "<" + output;
        return output;
    }
    
    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
        Bit current;
        if (other == null)
        {
            throw new IllegalArgumentException("Can't construct from a null BitList.");
        }
        java.util.Iterator<Bit> itr = other.iterator();
        while (itr.hasNext())
        {
            current = itr.next();
            if (current == null)
            {
                throw new IllegalArgumentException("Null cannot be an element of BitList.");
            }
            this.addLast(current);
        }
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {
        boolean output = true;
        if (this.size() == 0)
        {
            output = false;
        }
        else
        {
            String binary = toString();
            if (binary.charAt(1) == '1' & numberOfOnes == 1)
            {
                output = false;
            }
        }
        return output;

    }
    
    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {
        boolean output;
        String binary = this.toString();

        if (!this.isNumber())
        {
            output = false;
        }
        else if (binary.length() == 3 & binary.charAt(1) == '0')
        {
            output = true;
        }
        else if (binary.substring(1, 3).equals("10") | binary.substring(1, 3).equals("01"))
        {
            output = true;
        }
        else if (binary.substring(1, 3).equals("11") & numberOfOnes == 2)
        {
            output = true;
        }
        else 
        {
            output = false;
        }

        return output;
    }

    public void reduce() {
        while (!isReduced())
        {
            removeLast();
        }


        
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement() {
        java.util.Iterator<Bit> itr = super.iterator();
        BitList output = new BitList();
        while (itr.hasNext())
        {
            Bit newBit = itr.next();
            output.addLast(newBit.negate());
        }

        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() {
        Bit output = null;
        if (this.size() > 0)
        {
            output = this.removeFirst();
        }

        return output;
    }

    public void shiftLeft() {
        this.addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.8 ================================================
    public void padding(int newLength) {
        if (newLength > size())
        {
            Bit last = removeLast();
            for (int i = newLength - size(); i > 0; i--)
            {
                this.addLast(last);
            }
        }
    }

    

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
