/*
I, Sheer Maoz (204901409), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber>{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE  = new BinaryNumber(1);
    private BitList bits;

    // Copy constructor
    //Do not chainge this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not chainge this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    public BinaryNumber(BitList bits)
    {
        this.bits = new BitList(bits);
    }

    public BinaryNumber()
    {
        this.bits = new BitList();
    }

    //Do not chainge this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c) {
        if (c > '9' | c < '0')
        {
            throw new IllegalArgumentException("The character is not a decimal digit.");
        }
        bits = new BitList();
        int n = c - '0';
        int power = 8;
        bits.addFirst(Bit.ZERO);   
        if (n != 0 )
        {
            while (power > 0)
            {
                if (n % power < n)
                {
                    bits.addFirst(Bit.ONE);
                    n = n - power;
                }
                else
                {
                    bits.addFirst(Bit.ZERO);
                }
                power = power / 2;
            }
            
        }
        bits.reduce();
    }

  //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString() {
        // Do not remove or change the next two lines
        // if (!isLegal()){ // Do not change this line
        //     throw new RuntimeException("I am illegal.");}// Do not change this line
        
        //
        return bits.toString().substring(1, length() + 1);

    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.3 ================================================
    public boolean equals(Object other) {
        if (!(other instanceof BinaryNumber))
            return false;
        
        BinaryNumber bnOther = (BinaryNumber)other;
        if (this.length() != bnOther.length())
            return false;
        Iterator<Bit> itr1 = this.bits.iterator();
        Iterator<Bit> itr2 = bnOther.bits.iterator();

        while (itr1.hasNext())
        {
            if (!(itr1.next().equals(itr2.next())))
                return false;
            
        }

        return true;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.4 ================================================
    public BinaryNumber add(BinaryNumber addMe) {
        if (addMe == null || addMe.length() == 0)
        {
            throw new IllegalArgumentException("Cannot add null or empty number.");
        }
        int maxSize = 0;
        if (addMe.length() > this.length())
        {
            maxSize = addMe.length();
        }
        else 
        {
            maxSize = this.length();
        }

        this.bits.padding(maxSize + 1);
        addMe.bits.padding(maxSize + 1);
        BitList added = new BitList();

        Iterator<Bit> itr1 = this.bits.iterator();
        Iterator<Bit> itr2 = addMe.bits.iterator();
        Bit sum = Bit.ZERO;
        Bit carry = Bit.ZERO;
        
        while (itr1.hasNext())
        {
            Bit bit1 = itr1.next();
            Bit bit2 = itr2.next();
            sum = Bit.fullAdderSum(bit1, bit2, carry);
            carry =Bit.fullAdderCarry(bit1, bit2, carry);
            added.addLast(sum);

        }

        
        added.reduce();
        addMe.bits.reduce();
        this.bits.reduce();
        return new BinaryNumber(added);

    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.5 ================================================
    public BinaryNumber negate() {
        BitList negate = new BitList();
        Iterator<Bit> itr = this.bits.iterator();
        while (itr.hasNext())
        {
            negate.addLast(itr.next().negate());
        }
        BinaryNumber output = new BinaryNumber(negate);
        output = output.add(new BinaryNumber('1'));
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe) {
        if (subtractMe == null || subtractMe.length() == 0)
        {
            throw new IllegalArgumentException("Cannot subtract null or empty number.");
        }
        BinaryNumber toSubtract = subtractMe.negate();
        return this.add(toSubtract);
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.7 ================================================
    public int signum() {
        int output;
        Bit bit = this.bits.removeLast();
        this.bits.addLast(bit);
        if (this.equals(ZERO))
        {
            output = 0;
        }
        else if (bit.equals(Bit.ONE))
        {
            output = -1;
        }
        else
        {
            output = 1;
        }

        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {
        if (other == null || other.length() == 0)
        {
            throw new IllegalArgumentException("Cannot compare to null or empty number.");
        }
        return this.subtract(other).signum();
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //

        BinaryNumber toInt = new BinaryNumber(this);
        int times = 1;
        int output = 0;
        int power = 1;
        if (toInt.signum() == -1)
        {
            toInt = toInt.negate();
            times = -1;

        }
        
        Iterator<Bit> itr = toInt.bits.iterator();
        while (itr.hasNext())
        {
            output = output + itr.next().toInt() * power;
            power = power * 2;
        }

        return output * times;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
        BinaryNumber multiplyMe2 = new BinaryNumber(multiplyMe);
        BinaryNumber output;
        if (multiplyMe.signum() == -1)
        {
            output = multiplyPositive(multiplyMe2.negate()).negate();
        }
        else
        {
            output = multiplyPositive(multiplyMe2);
        }

        return output;
    }

    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
        BinaryNumber output = new BinaryNumber(this);
        if (multiplyMe.equals(new BinaryNumber(0)) | multiplyMe.length() == 0)
        {
            output = new BinaryNumber(0);
        }
        else
        {
            Bit bit = multiplyMe.bits.removeFirst();
            multiplyMe.bits.addFirst(bit);
            if (bit.equals(Bit.ONE))
            {
                output = output.add(output.multBy2().multiplyPositive(multiplyMe.divBy2()));
            }
            else
            {
                output = output.multBy2().multiplyPositive(multiplyMe.divBy2());
            }
            
        }
        return output;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
    	// Do not remove or change the next two lines
    	if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
        //
        
        if (!isLegal())
            throw new IllegalArgumentException("I am not legal.");
        BinaryNumber divisor2 = new BinaryNumber(divisor);
        BinaryNumber output;
        BinaryNumber divider = new BinaryNumber(this);
        int toNegate = 0;
        if (divider.signum() == -1)
        {
            toNegate++;
            divider = divider.negate();
        }
        if (divisor2.signum() == -1)
        {
            toNegate--;
            divisor2 = divisor2.negate();
        }
        output = divider.dividePositive(divisor2);
        if (toNegate != 0)
            output = output.negate();
        return output;
    }

    private BinaryNumber dividePositive(BinaryNumber divisor) {
       
        BinaryNumber quotient = new BinaryNumber(0);
        BinaryNumber temp = new BinaryNumber(0);
        if (this.compareTo(divisor) == -1)
        {
            return new BinaryNumber(0);
        }
        for (int i =0; i < divisor.length() -1; i++)
        {
            temp.bits.addFirst(this.bits.removeLast());
        }
        while(this.length() > 0)
        {
            
            temp.bits.addFirst(this.bits.removeLast());
            if (divisor.compareTo(temp) != 1)
            {
                temp = temp.subtract(divisor);
                quotient.bits.addFirst(Bit.ONE);
            }
            else
            {
                quotient.bits.addFirst(Bit.ZERO);
            }
        }

        quotient.bits.reduce();
        return quotient;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {
        if (s == null || s.length() == 0)
        {
            throw new IllegalArgumentException("Can't create a number from an empty or null string.");
        }
        BinaryNumber ten = new BinaryNumber('9').add(ONE);
        BinaryNumber output = new BinaryNumber('0');

        if (s.charAt(0) != '-')
        {
            for (int i = 0; i < s.length(); i++)
            {
                char digit = s.charAt(i);
                if (digit > '9' | digit < '0')
                {
                    throw new IllegalArgumentException("The string does not represent a decimal number.");
                }
                output = output.multiply(ten);
                output = output.add(new BinaryNumber(digit));
            }
        }

        else
        {
         for (int i = 1; i < s.length(); i++)
            {
                char digit = s.charAt(i);
                if (digit > '9' | digit < '0')
                {
                    throw new IllegalArgumentException("The string does not represent a decimal number.");
                }
                output = output.multiply(ten);
                output = output.add(new BinaryNumber(digit));
                
            }
            output = output.negate(); 
        }

        this.bits = new BitList(output.bits);



    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.13 ================================================
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        BinaryNumber power = new BinaryNumber(1);
        String s = "";
        BinaryNumber toString = new BinaryNumber(this);
        if (toString.signum() == -1)
        {
            toString = toString.negate();
        }
        int length = 1;
        while(toString.compareTo(power) != -1)
        {
            length++;
            power = power.multiply(new BinaryNumber("10"));
        }
        power = power.divide(new BinaryNumber("10"));
        length--;

        while (!toString.equals(ZERO))
        {
            BinaryNumber temp = toString.divide(power);
            s = s + temp.toInt();
            toString = toString.subtract(temp.multiply(power));
            power = power.divide(new BinaryNumber("10"));
            
        }
        while (s.length() < length)
        {
            s = s + "0";
        }
        if (this.signum() == -1)
        {
            s = '-' + s;
        }
        if (s.length() == 0)
        {
            s = "0";
        }

        return s;
        
    }


    // Returns this * 2
    public BinaryNumber multBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returens this / 2;
    public BinaryNumber divBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }

    public BitList getBits(){return bits;}

}
