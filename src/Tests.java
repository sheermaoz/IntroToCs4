import java.util.TimerTask;

public class Tests {
    //----- Test ---------------------------------------
    private static int nFailures = 0;
    private static Bit one = Bit.ONE;
    private static Bit zero = Bit.ZERO;
    
    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
    	test("Bit.fullAdderSum(zero, zero, zero).equals(zero)", Bit.fullAdderSum(zero, zero, zero).equals(zero),10);
        test("Bit.fullAdderSum(one , one , zero).equals(zero)", Bit.fullAdderSum(one , one , zero).equals(zero),20);
        test("Bit.fullAdderSum(zero, zero, one ).equals(one )", Bit.fullAdderSum(zero, zero, one ).equals(one ),80);

        test("Bit.carry(zero, zero, zero).equals(zero)", Bit.fullAdderCarry(zero, zero, zero).equals(zero),90);
        test("Bit.carry(one , one , zero).equals(one )", Bit.fullAdderCarry(one , one , zero).equals(one ),100);
        test("Bit.carry(zero, zero, one ).equals(zero)", Bit.fullAdderCarry(zero, zero, one ).equals(zero),160);

        /*BitList b1 = new BitList(); // <>
        b1.addFirst(zero); // <0>
        b1.addFirst(zero); // <00>
        b1.addFirst(one); // <001>
        System.out.println(b1.toString()); // prints <001>*/

        /*BitList b1 = new BitList(); // <>
        b1.addFirst(zero); // <0>
        b1.addFirst(zero); // <00>
        b1.addFirst(one); // <001>
        BitList b2 = new BitList(b1); // <001>
        //System.out.println(b2.toString()); // <001>
        b2.addFirst(one); // <0011>
        b2.addFirst(one); // <00111>
        b2.addFirst(one); // <001111>
        System.out.println(b1.isReduced()); // <001>
        System.out.println(b2.isReduced()); // <001111>*/

        /*BitList b3 = new BitList(); // <>
        //System.out.println(b1.isNumber()); // prints false
        b3.addFirst(one); // <1>
        b3.addFirst(zero); // <10>
        b3.addFirst(one); // <100>
        b3.addFirst(zero);
        //System.out.println(b1.isNumber()); // prints false
        b3.addLast(zero); // <1100>
        b3.padding(6);
        System.out.println(b3.toString()); // prints true*/


    	
    	BinaryNumber n0 = new BinaryNumber('0');
        BinaryNumber n1 = new BinaryNumber('1');
        BinaryNumber n2 = new BinaryNumber('2');
        BinaryNumber n3 = new BinaryNumber('3');
        BinaryNumber n4 = new BinaryNumber('4');
        BinaryNumber n5 = new BinaryNumber('5');
        BinaryNumber n6 = new BinaryNumber('6');
        BinaryNumber n7 = new BinaryNumber('7');
        BinaryNumber n8 = new BinaryNumber('8');
        BinaryNumber n9 = new BinaryNumber('9');
        //---------------------------------------------------
        test(n0.toString(), n0.toString().equals("0"), 10);
        test(n3.toString(), n3.toString().equals("011"), 40);
        test(n6.toString(), n6.toString().equals("0110"), 70);
        test(n9.toString(), n9.toString().equals("01001"), 100);
        //---------------------------------------------------
        BinaryNumber nn9 = n9.add(n0);
        test(nn9.toString(), nn9.toString().equals("01001"), 110);
        BinaryNumber n10 = n9.add(n1);
        BinaryNumber n18 = n9.add(n9);
        test(n18.toString(), n18.toString().equals("010010"), 140);
        //----- Negate --------
        BinaryNumber m18 = n18.negate();
        test(m18.toString(), m18.toString().equals("101110"), 150);
        BinaryNumber m0 = n18.add(m18);
        BinaryNumber m1 = n1.negate();
        m0 = n1.add(m1);
        //------ subtract --------
        test(n9+".subtract("+n5+").equals("+n4+")", n9.subtract(n5).equals(n4), 180);
        BinaryNumber m4 = n4.negate();
        test(m4+" = "+n4+".negate()", m4.toString().equals("1100"), 190);
        m4 = n5.subtract(n9);
        test(m4+" = "+n5+".subtract("+n9+")", m4.toString().equals("1100"), 200);
        test(n1+".subtract("+n1+").equals("+n0+")", n1.subtract(n1).equals(n0), 220);
        // --------- compareTo ------------------------------
        test(n5+".compareTo("+n4+") == 1",n5.compareTo(n4) == 1, 240);
        test(n4+".compareTo("+n5+") == -1",n4.compareTo(n5) == -1, 242);
        // ------------ Multiply ----------------------------
        test(n2+".operate("+n2+").equals("+n4+")", n2.multiply(n2).equals(n4), 250);
        test(getZero()+".multiply("+n9+").equals("+getZero()+")", getZero().multiply(n9).equals(getZero()),260);
        BinaryNumber m8 = n8.negate();
        test(m4+".multiply("+n2+").equals("+m8+")", m4.multiply(n2).equals(m8),280);
        //------------ divide ---------
        test(getZero()+".divide("+n9+").equals("+getZero()+")", getZero().divide(n9).equals(getZero()),290);
        test(n9+".divide("+n9+").equals("+getOne()+")", n9.divide(n9).equals(getOne()),300);
        test(n10.add(n10)+".divide("+n9+").equals("+n2+")", n10.add(n10).divide(n9).equals(n2),320);
        BinaryNumber n89 = new BinaryNumber("89");
        test(n89+".divide("+n10+").equals("+n89.divide(n10)+")", n89.divide(n10).equals(new BinaryNumber("8")),321);
        //------------ toInt() -------------------------
        test(n10.add(n10).toInt()+" == 20", n10.add(n10).toInt() == 20, 330);
        //----------- constructor ----------------------------
        new BinaryNumber("12");
        test("new BinaryNumber(\"12\")).toInt() == 12",   (new BinaryNumber("12")).toInt() == 12, 340 );
        test("new BinaryNumber(\"-12\")).toInt() == -12",   (new BinaryNumber("-12")).toInt() == -12, 350 );
        // -------------- Fibonacci ---------------------------
        test("The 3rd number in Fibonacci series is "+fib(3).toIntString(), fib(3).toIntString().equals("2"), 400);
        test("The 8th number in Fibonacci series is "+fib(8).toIntString(), fib(8).toIntString().equals("21"), 410);
        test("The 11th number in Fibonacci series is "+fib(11).toIntString(), fib(11).toIntString().equals("89"), 411);
        BinaryNumber fib100 = fib(100);
        test("The 100th number in Fibonacci series is "+fib100.toIntString(), fib100.toIntString().equals("354224848179261915075"), 420);
        BinaryNumber twiceFib100 = n2.multiply(fib100);
        test("fib(100) * 2 = "+twiceFib100.toIntString(), twiceFib100.toIntString().equals("708449696358523830150"), 430);
        test("fib(100) / 2 "+fib100.divide(n2).toIntString(), fib100.divide(n2).toIntString().equals("177112424089630957537"), 440);
        test("minus fib(100) = "+fib100.negate().toIntString(), fib100.negate().toIntString().equals("-354224848179261915075"), 450);
        Long endTime = System.currentTimeMillis();
        System.out.println("Tests execution took " + (endTime - startTime) + " milliseconds.");
    }

    private static BinaryNumber getZero() {
        return new BinaryNumber('0');
    }
    private static BinaryNumber getOne() {
        return new BinaryNumber('1');
    }

    private static BinaryNumber fib(int n) {
    	BinaryNumber ans;
    	if(n == 0) {
    		ans = getOne();
    	}
    	else {
    		ans = getOne();
    		BinaryNumber prev = getZero();
    		while(n >= 2) {
    			ans = ans.add(prev); // fib(n) = fib(n-1) + fib(n-2)
    			prev = ans.subtract(prev); // fib(n-1) = fib(n) - fib(n-2)
    			n = n - 1;
    		}
    	}
    	return ans;
    }
    
    public static void test(String sufix, boolean term, int id) {

        if (term)
            System.out.println(" Test no. " + id + " OK. Overall " + nFailures + " failures " + sufix);
        else {
            nFailures = nFailures + 1;
            System.out.println(" Test no. " + id + " failed. Overall " + nFailures + " failures " + sufix);
        }
    }

}
