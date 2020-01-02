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
        /*
        The function receives a Bit and adds it to the end of the list.
        numberOfOnes is updated according the value of the given Bit.
        */
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
        /*
        The function receives a Bit and adds it to the start of the list.
        numberOfOnes is updated according the value of the given Bit.
        */
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
        /*
        The function removes the Bit at the end of the list and returns it.
        numberOfOnes is updated according the value of the removed Bit.
        */
        if (size() == 0)
        {
            throw new RuntimeException("Cannot remove from empty List.");
        }
        Bit bit = super.removeLast();
        if (bit.toInt() == 1)
        {
            numberOfOnes--;
        }
        return bit;
    }

    public Bit removeFirst() {
        /*
        The function removes the Bit at the start of the list and returns it.
        numberOfOnes is updated according the value of the removed Bit.
        */
        if (size() == 0)
        {
            throw new RuntimeException("Cannot remove from empty List.");
        }
        Bit bit = super.removeFirst();
        if (bit.toInt() == 1)
        {
            numberOfOnes--;
        }
        return bit;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() {
        /*
        The function returns a String representing the list, 
            encapsulated by '<>'.
        */
        String output = ">";
        for (Bit bit : this)
        {
            output = bit.toString() + output;
        }
        output = "<" + output;
        return output;
    }
    
    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
        /*
        A copying constructur.
        */
        if (other == null)
        {
            throw new IllegalArgumentException("Can't construct from a null BitList.");
        }
        for (Bit bit : other)
        {
            if (bit == null)
            {
                throw new IllegalArgumentException("Can't construct from a BitList containing null.");
            }
            this.addLast(bit);
        }
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {
        /*
        The function returns true if the list represents a valid 
            binary number, and false otherwise.
        */
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
        /*
        The function returns true if the number represented by the list
            is reduced, and false otherwise.
        */
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
        /*
        The function reduces the number represented by the list.
        */
        while (!isReduced())
        {
            removeLast();
        }


        
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement() {
        /*
        The function returns a new list where each Bit is 
            negative to the corresponding Bit in the list.
        */
        BitList output = new BitList();
        for (Bit bit : this)
        {
            output.addLast(bit.negate());
        }

        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() {
        /*
        The function shifts the binary number represented
            in the list to the right, and returns the removed Bit.
        */
        Bit output = null;
        if (this.size() > 0)
        {
            output = this.removeFirst();
        }

        return output;
    }

    public void shiftLeft() {
        /*
        The function shifts the binary number represented
            in the list to the left.
        */
        this.addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 2.8 ================================================
    public void padding(int newLength) {
        /*
        The function adds padding to the number, until it is 
            the length of newLength.
        If the number is longer, nothing happens.
        */
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
