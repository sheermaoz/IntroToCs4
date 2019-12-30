import java.math.BigInteger;
import java.util.Random;

interface IBfunction2Arg
        {
            String toString();
            void call(BinaryNumber bn1,BinaryNumber bn2);
        }

interface IBfunction1Arg
    {
        String toString();
        void call(BinaryNumber bn1);
    }

public class TestsOhad {
    //----- Test ---------------------------------------
    private static int nFailures = 0;
    private static Bit one = Bit.ONE;
    private static Bit zero = Bit.ZERO;
    public static boolean TOINT_IMPLEMENTED = is_toint_implemented();
    public static int AMOUNT_OF_RANDOMS = 30;
    public static  final Bit ONE  = new Bit(true);
    public static  final Bit ZERO = new Bit(false);
    public static final String I_A_EXCEPTION = "illegal_argument_exception" ;
    public static  final String [] VALID_NUMBERS = {"1100" , "1000001" , "0","11010101011","01"};
    public static  final String [] INVALID_NUMBERS = {"100" , "1" , "","1000000000","10"};
    public static  final String [][] NUMBERS_TO_REDUCE = {{"0000001","01"},{"000101010101","0101010101"},{"11101","101"},
                                                            {"1111100","1100"},{"0000000","0"}};
    public static  final String [][] NUMBERS_TO_LEFT = {{"10001","100010"},{"10101010","101010100"},{"11111101","111111010"}};
    public static  final String [][] NUMBERS_TO_SHR = {{"100011","10001"},{"10101010","1010101"},{"01111","0111"}};
    public static  final String [][] NUMBERS_TO_COMPLEMENT = {{"100011","011100"},{"10101010","01010101"},{"1111","0000"}};
    public static  final String [][] NUMBERS_TO_PADD = {{"0","00000","5"},{"10101010","11110101010","11"},
            {"1111","1111111111","10"},{"01110010","0001110010","10"}};

    public static  final String [][] BN_FROM_CHAR = {{"0","0"},{"1","01"},{"2","010"},{"3","011"},{"4","0100"},{"5","0101"},{"6","0110"},
                                                    {"7","0111"},{"8","01000"},{"9","01001"},{"-",I_A_EXCEPTION},{" ",I_A_EXCEPTION}};
    public static final String [] BN_EQUAL = {"0101","010101","101101","0","10101"};
    public static  final int [][] NUMBERS_TO_ADD_SUB = {{1250,1000},{-3,-3},{-7,-7},{-1,-1},{-500,-500},{-154635,154635},{10,6},{10,7},{35,70},{40,-1000},{-354,67},
                                                    {-4353,-8547},{600,-100},{-100,600},{775746,54367},{100000,100000}};
    public static final int[] NUMBERS_TO_NEGATE={0,5743,543,2367,8765,232332,5464636,0,1,-4324,-5437,-23,-1,99999,7,-7};
    public static  final int [][] NUMBERS_TO_COMPER = {{40,-1000,1},{-3,-3,0},{-7,7,-1},{-1,-1,0},{-500,-500,0},{-154635,-154635,0},{2,6,-1},{35,70,-1},
            {-354,-67,-1},{43267,432,1},{-646,-543546,1}};
    public static  final String [][] BITS_TO_INT = {{"0111011100110101100101000000000","1000000000"},{"01010101001101011","43627"},
            {"0101100010","354"},{"01000011","67"}};
    public static  final int [][] NUMBERS_TO_MUL = {{10,6,60},{10,7,70},{35,70,2450},{354,67,23718},{-1,-1,1},{40,-1000,-40000},{-354,67,-23718}};
    public static  final int [][] NUMBERS_TO_DIV = {{50,60,0},{-40000,1000,-40},{60,6,10},{2450,70,35},{23718,354,67},{654,7,93},{-1,-1,1},
            {46324,574,80},{46324,-574,-80},{5748359,1,5748359},{2147483647,1,2147483647}};
    public static  final String [] BINARRY_NUMBER_FROM_DEC_STRING = {"110","1000000000","54325","56478356",
                                    "-8777777","777777","0","-7685"};//TODO: add very latge number to anothe list
    public static final IBfunction2Arg[] BINARYNUMBER_FUNCTIONS_2ARG = {
            new IBfunction2Arg(){public void call(BinaryNumber bn1, BinaryNumber bn2){bn1.equals(bn2);};public String toString(){return "equals";}},
            new IBfunction2Arg(){public void call(BinaryNumber bn1, BinaryNumber bn2){bn1.add(bn2);};public String toString(){return "add";}},
            new IBfunction2Arg(){public void call(BinaryNumber bn1, BinaryNumber bn2){bn1.subtract(bn2);};public String toString(){return "subtract";}},
            new IBfunction2Arg(){public void call(BinaryNumber bn1, BinaryNumber bn2){bn1.compareTo(bn2);};public String toString(){return "compareTo";}},
            new IBfunction2Arg(){public void call(BinaryNumber bn1, BinaryNumber bn2){bn1.multiply(bn2);};public String toString(){return "multiply";}},
            new IBfunction2Arg(){public void call(BinaryNumber bn1, BinaryNumber bn2){bn1.divide(bn2);};public String toString(){return "divide";}}};

    public static final IBfunction1Arg[] BINARYNUMBER_FUNCTIONS_1ARG = {
            new IBfunction1Arg(){public void call(BinaryNumber bn1){bn1.negate();};public String toString(){return "negate";}},
            new IBfunction1Arg(){public void call(BinaryNumber bn1){bn1.signum();};public String toString(){return "signum";}}};


    //convert "-" to Binarry number


    private static boolean is_toint_implemented()
    {
        try {
            new BinaryNumber('1').toInt();
            return true;
        }
        catch (UnsupportedOperationException e)
        {
            return false;
        }
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();

        part1_test();


        test_BitList_from_string();
        test_add_first_null_ohad();
        test_add_last_null_ohad();
        test_BitList_tostrong_2_2_ohad2();
        test_BitList_tostrong_2_2_ohad();
        test_BitList_coping_constructor_2_3_ohad();
        test_BitList_isNumber_2_4_true_ohad();
        test_BitList_isNumber_2_4_false_ohad();
        test_isReduced_2_5_true_and_false_ohad();
        test_complement_2_6_ohad();
        test_shift_left_2_7_ohad();
        test_shift_right_2_7_ohad();
        test_padding_2_8_ohad();

        test_BinarryNumber_from_char_3_1_ohad();
        test_BinarryNumber_equals_3_3_ohad();
        test_BinarryNumber_add_3_4_ohad();
        test_BinarryNumber_negate_3_5();
        test_BinarryNumber_subtract_3_6_ohad();
        test_BinarryNumber_signum_3_7();
        test_BinarryNumber_comperto_3_8_ohad();
        test_BinarryNumber_toint_3_9_ohad();
        test_BinarryNumber_multiply_3_10_ohad();
        test_BinarryNumber_divide_3_11_ohad();
        test_binarrynumber_tointstring_0();
        test_BinarryNumber_from_decimal_string_3_12_ohad();
        test_binarrynumber_random_biginteger_3_12_3_13_ohad();
        test_binarrynumber_random_biginteger_calculations_3_4_5_6_10_11_ohad();
        test_BinaryNumber_same_after_funtion_1_arg();
        test_BinaryNumber_same_after_funtion_2_arg();

        original_test();


        Long endTime = System.currentTimeMillis();
        System.out.println("Tests execution took " + (endTime - startTime) + " milliseconds.");

        OhadInfra.print_final_report();
    }

    public static void part1_test()
    {
        OhadInfra.report("Bit.fullAdderSum(zero, zero, zero).equals(zero)_yonatan", Bit.fullAdderSum(zero, zero, zero).equals(zero));
        OhadInfra.report("Bit.fullAdderSum(one , one , zero).equals(zero)_yonatan", Bit.fullAdderSum(one, one, zero).equals(zero));
        OhadInfra.report("Bit.fullAdderSum(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderSum(zero, zero, one).equals(one));
        OhadInfra.report("Bit.fullAdderSum(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderSum(one, zero, zero).equals(one));
        OhadInfra.report("Bit.fullAdderSum(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderSum(zero, one, zero).equals(one));
        OhadInfra.report("Bit.fullAdderSum(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderSum(one, zero, one).equals(zero));
        OhadInfra.report("Bit.fullAdderSum(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderSum(zero, one, one).equals(zero));
        OhadInfra.report("Bit.fullAdderSum(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderSum(one, one, one).equals(one));

        OhadInfra.report("Bit.carry(zero, zero, zero).equals(zero)_yonatan", Bit.fullAdderCarry(zero, zero, zero).equals(zero));
        OhadInfra.report("Bit.carry(one , one , zero).equals(one )_yonatan", Bit.fullAdderCarry(one, one, zero).equals(one));
        OhadInfra.report("Bit.carry(zero, zero, one ).equals(zero)_yonatan", Bit.fullAdderCarry(zero, zero, one).equals(zero));
        OhadInfra.report("Bit.carry(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderCarry(one, zero, zero).equals(zero));
        OhadInfra.report("Bit.carry(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderCarry(zero, one, zero).equals(zero));
        OhadInfra.report("Bit.carry(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderCarry(one, zero, one).equals(one));
        OhadInfra.report("Bit.carry(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderCarry(zero, one, one).equals(one));
        OhadInfra.report("Bit.carry(zero, zero, one ).equals(one )_yonatan", Bit.fullAdderCarry(one, one, one).equals(one));

    }

    public static  void original_test()
    {
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
//        //---------------------------------------------------
        OhadInfra.report("n0.toString()", n0.toString(),"0");
        OhadInfra.report("n3.toString()", n3.toString().equals("011"));
        OhadInfra.report("n6.toString()", n6.toString().equals("0110"));

        OhadInfra.report("n9.toString()", n9.toString(),"01001");
//        //---------------------------------------------------
        BinaryNumber nn9 = n9.add(n0);
        OhadInfra.report("test_add 9+0", nn9.toString(),"01001");
        OhadInfra.report("test_add 1+1",n1.add(n1).toString(),n2.toString());
        OhadInfra.report("test_add 4+4",n4.add(n4).toString(),n8.toString());
        BinaryNumber n10 = n9.add(n1);
        BinaryNumber n18 = n9.add(n9);
        OhadInfra.report("test_add 9+9", n18.toString(),"010010");
//        //----- Negate --------
        BinaryNumber m18 = n18.negate();
        OhadInfra.report("n18.negate()", m18.toString(),"101110");
        BinaryNumber m0 = n18.add(m18);
        BinaryNumber m1 = n1.negate();
        m0 = n1.add(m1);
//        //------ subtract --------
        OhadInfra.report("subtract test ohad 9-5",n9.subtract(n5).toString(),"0100", n9.subtract(n5).equals(n4));
        BinaryNumber m4 = n4.negate();
        OhadInfra.report(m4+" = "+n4+".negate()", m4.toString().equals("1100"));
        m4 = n5.subtract(n9);
        OhadInfra.report("-4 = 5-9", m4.toString().equals("1100"));
        BinaryNumber n1m1 = n1.subtract(n1);
        OhadInfra.report("1-1 = 0", n1m1.toString(),n0.toString());
//        // --------- compareTo ------------------------------
        OhadInfra.report(n5+".compareTo("+n4+") == 1",n5.compareTo(n4) == 1);
        OhadInfra.report(n4+".compareTo("+n5+") == -1",n4.compareTo(n5) == -1);
        OhadInfra.report(n4+".compareTo("+n4+") == -1",n4.compareTo(n4) == 0);
//        // ------------ toInt ----------------------------
        OhadInfra.report("to_int_test_ohad 9",n9.toInt(),9);
        OhadInfra.report("to_int_test_ohad 18",n18.toInt(),18);
        OhadInfra.report("to_int_test_ohad 8",n8.toInt(),8);
        OhadInfra.report("to_int_test_ohad -4",m4.toInt(),-4);
//        // ------------ Multiply ----------------------------
        OhadInfra.report("2*2=4", n2.multiply(n2).toString(),(n4).toString());
        OhadInfra.report("0*9=0", getZero().multiply(n9).toString(),(getZero()).toString());
        BinaryNumber m8 = n8.negate();
        OhadInfra.report("-4*2=-8", m4.multiply(n2).toString(),(m8).toString());
//        //------------ divide ---------
        OhadInfra.report("0/9=0", getZero().divide(n9).toString(),getZero().toString());
        OhadInfra.report("9/9=1", n9.divide(n9).toString(),getOne().toString());
        OhadInfra.report("20/9=2", n10.add(n10).divide(n9).toString(),n2.toString());
        BinaryNumber n89 = new BinaryNumber("89");
        OhadInfra.report(n89+".divide("+n10+").equals("+n89.divide(n10)+")", n89.divide(n10).equals(new BinaryNumber("8")));
//        //------------ toInt() -------------------------
        test(n10.add(n10).toInt()+" == 20", n10.add(n10).toInt() == 20, 330);
//        //----------- constructor ----------------------------
        new BinaryNumber("12");
        OhadInfra.report("new BinaryNumber(\"12\")).toInt() == 12",   (new BinaryNumber("12")).toInt(),12 );
        OhadInfra.report("new BinaryNumber(\"-12\")).toInt() == -12",   (new BinaryNumber("-12")).toInt() , -12 );
//        // -------------- Fibonacci ---------------------------
        Fibonacci();
    }

    public  static  void Fibonacci()
    {
        BinaryNumber n2 = new BinaryNumber('2');
        OhadInfra.report("The 3rd number in Fibonacci series is "+fib(3).toIntString(), fib(3).toIntString(),"2");
        OhadInfra.report("The 8th number in Fibonacci series is "+fib(8).toIntString(), fib(8).toIntString(),"21");
        OhadInfra.report("The 11th number in Fibonacci series is "+fib(11).toIntString(), fib(11).toIntString(),"89");
        BinaryNumber fib100 = fib(100);
        OhadInfra.report("The 100th number in Fibonacci series is "+fib100.toIntString(), fib100.toIntString(),"354224848179261915075");
        BinaryNumber twiceFib100 = n2.multiply(fib100);
        OhadInfra.report("fib(100) * 2 = "+twiceFib100.toIntString(), twiceFib100.toIntString(),"708449696358523830150");
        OhadInfra.report("fib(100) / 2 "+fib100.divide(n2).toIntString(), fib100.divide(n2).toIntString(),"177112424089630957537");
        OhadInfra.report("minus fib(100) = "+fib100.negate().toIntString(), fib100.negate().toIntString(),"-354224848179261915075");
    }

    private static BinaryNumber getZero() {
        return new BinaryNumber('0');
    }
    private static BinaryNumber getOne() {
        return new BinaryNumber('1');
    }
    private static BinaryNumber getTwo() {
        return new BinaryNumber('2');
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

    public static void test_exception_for_invalid_input()
    {
        BitList bl1= new BitList();
        BitList bl2= new BitList();
        BinaryNumber empty1 = new BinaryNumber(bl1);
        BinaryNumber empty2 = new BinaryNumber(bl2);
        test_exception_for_2_invalid_numbers(empty1,empty2,getOne(),getTwo());
        BinaryNumber invalid1 = get_invalid1();
        BinaryNumber invalid2 = get_invalid1();
        test_exception_for_2_invalid_numbers(invalid1,invalid2,getOne(),getTwo());
    }

    private static BinaryNumber get_invalid1()
    {
        BitList bl1= new BitList();
        bl1.addFirst(new Bit(true));
        BinaryNumber invalid1 = new BinaryNumber(bl1);
        return invalid1;
    }

    private static BinaryNumber get_invalid2()
    {
        BitList bl2= new BitList();
        bl2.addFirst(new Bit(true));
        bl2.addFirst(new Bit(false));
        BinaryNumber invalid2 = new BinaryNumber(bl2);
        return invalid2;
    }

    public static void test_exception_for_2_invalid_numbers(BinaryNumber invalid1, BinaryNumber invalid2,BinaryNumber valid_number1,BinaryNumber valid_number2)
    {
        BinaryNumber inv1 = new BinaryNumber(invalid1);
        BinaryNumber inv2 = new BinaryNumber(invalid2);
        BinaryNumber valid1 = new BinaryNumber(valid_number1);
        BinaryNumber valid2 = new BinaryNumber(valid_number2);
        for(int i = 0; i< BINARYNUMBER_FUNCTIONS_2ARG.length; i++)
        {
            check_got_exception(BINARYNUMBER_FUNCTIONS_2ARG[i],inv1,inv2);
            check_got_exception(BINARYNUMBER_FUNCTIONS_2ARG[i],inv2,inv1);
            check_got_exception(BINARYNUMBER_FUNCTIONS_2ARG[i],valid1,inv1);
            check_got_exception(BINARYNUMBER_FUNCTIONS_2ARG[i],inv1,valid1);
            check_run_smothly(BINARYNUMBER_FUNCTIONS_2ARG[i],valid1,valid2);
            check_run_smothly(BINARYNUMBER_FUNCTIONS_2ARG[i],valid1,valid1);
        }
        for(int i =0;i<BINARYNUMBER_FUNCTIONS_1ARG.length;i++)
        {
            check_got_exception(BINARYNUMBER_FUNCTIONS_1ARG[i],inv1);
            check_run_smothly(BINARYNUMBER_FUNCTIONS_1ARG[i],valid1);
        }
    }

    public static void test_BinaryNumber_same_after_funtion_2_arg()
    {
        //from the results of the last tests i suspect that you are changing the value of the instance after you use one of its functions
        Random rnd = new Random();
        for(int i=0;i<BINARYNUMBER_FUNCTIONS_2ARG.length;i++) {
            BinaryNumber bn = BinaryNumber_from_int(rnd.nextInt(10));
            BinaryNumber bn2 = BinaryNumber_from_int(rnd.nextInt(10));
            BinaryNumber initial_value = new BinaryNumber(bn);
            BinaryNumber initial_value2 = new BinaryNumber(bn2);
            for(int j=0;j<20;j++)
            {
                BINARYNUMBER_FUNCTIONS_2ARG[i].call(bn,bn2);
                if(!bn.equals(initial_value)||!bn2.equals(initial_value2))
                    break;

            }
            String test_case = "test_BinaryNumber_same_after_funtion "+BINARYNUMBER_FUNCTIONS_2ARG[i];
            String failure_msg = "the function "+ BINARYNUMBER_FUNCTIONS_2ARG[i] + "changed the value of the object it was called from";
            String failure_msg2 = "the function "+ BINARYNUMBER_FUNCTIONS_2ARG[i] + "changed the value of the object it was called with as a parameter";
            OhadInfra.report(test_case,bn.toString(),initial_value.toIntString(),bn.equals(initial_value),failure_msg);
            OhadInfra.report(test_case,bn2.toString(),initial_value2.toIntString(),bn2.equals(initial_value2),failure_msg2);
        }

    }

    public static void test_BinaryNumber_same_after_funtion_1_arg()
    {
        //from the results of the last tests i suspect that you are changing the value of the instance after you use one of its functions
        Random rnd = new Random();
        for(int i=0;i<BINARYNUMBER_FUNCTIONS_1ARG.length;i++) {
            BinaryNumber bn = BinaryNumber_from_int(rnd.nextInt(10));
            BinaryNumber initial_value = new BinaryNumber(bn);
            for(int j=0;j<20;j++)
            {
                BINARYNUMBER_FUNCTIONS_1ARG[i].call(bn);
                if(!bn.equals(initial_value))
                    break;

            }
            String test_case = "test_BinaryNumber_same_after_funtion "+BINARYNUMBER_FUNCTIONS_1ARG[i];
            String failure_msg = "the function "+ BINARYNUMBER_FUNCTIONS_1ARG[i] + "changed the value of the object it was called from";
            OhadInfra.report(test_case,bn.toString(),initial_value.toIntString(),bn.equals(initial_value),failure_msg);
        }

    }

    public static void check_got_exception(IBfunction2Arg ibfunction, BinaryNumber bn1, BinaryNumber bn2)
    {
        try
        {
            ibfunction.call(bn1,bn2);
        }
        catch (IllegalArgumentException e)
        {
            String msg = "got IllegalArgumentException as expected while called '" +ibfunction +"' with the values '"+bn1.getBits() +"' and '"+bn2.getBits()+"'";
            OhadInfra.report(msg,true);
            return;
        }
        catch(Exception e)
        {
            String msg = "got exception "+ e.toString()+"insted of IllegalArgumentException while calling '" +ibfunction +"' with the values '"+bn1.getBits() +"' and '"+bn2.getBits()+"' ";
            OhadInfra.report(msg,false);
            return;
        }
        String msg = "you were supposed to get IllegalArgumentException for the function '" +ibfunction +"' with the values '"+bn1.getBits() +"' and '"+bn2.getBits()+"' but you didn't throw any exception";;
        OhadInfra.report(msg,false);
    }

    public static void check_got_exception(IBfunction1Arg ibfunction, BinaryNumber bn)
    {
        try
        {
            ibfunction.call(bn);
        }
        catch (IllegalArgumentException e)
        {
            String msg = "got IllegalArgumentException as expected while called '" +ibfunction +"' with the values '"+bn.getBits() +"'";
            OhadInfra.report(msg,true);
            return;
        }
        catch(Exception e)
        {
            String msg = "got exception "+ e.toString()+"insted of IllegalArgumentException while calling '" +ibfunction +"' with the values '"+bn.getBits()+"' ";
            e.printStackTrace();
            OhadInfra.report(msg,false);
            return;
        }
        String msg = "you were supposed to get IllegalArgumentException for the function '" +ibfunction +"' with the values '"+bn.getBits() +"' but you didn't throw any exception";;
        OhadInfra.report(msg,false);
    }

    public static void check_run_smothly(IBfunction2Arg ibfunction, BinaryNumber bn1, BinaryNumber bn2)
            //checks that the function dosent throw exception for no reson
    {
        try
        {
            ibfunction.call(bn1,bn2);
            String msg = "the function '" +ibfunction +"' with the values '"+bn1 +"' and '"+bn2+"' ran without exception as expected";;
            OhadInfra.report(msg,true);
        }
        catch(Exception e)
        {
            String msg = "got exception "+ e.toString()+" for no reason , the function '" +ibfunction +"' should run smothly with the values '"+bn1.getBits() +"' and '"+bn2.getBits()+"' ";
            e.printStackTrace();
            OhadInfra.report(msg,false);
        }

    }

    public static void check_run_smothly(IBfunction1Arg ibfunction, BinaryNumber bn)
    //checks that the function dosent throw exception for no reson
    {
        try
        {
            ibfunction.call(bn);
            String msg = "the function '" +ibfunction +"' with the value '"+bn+"' ran without exception as expected";;
            OhadInfra.report(msg,true);
        }
        catch(Exception e)
        {
            String msg = "got exception "+ e.toString()+" for no reason , the function '" +ibfunction +"' should run smothly with the value '"+bn.getBits() +"' ";
            e.printStackTrace();
            OhadInfra.report(msg,false);
        }

    }

    public static void test_remove_first_empty_ohad()
    {
        BitList bl = new BitList();
        try{
            bl.removeFirst();
            bl.removeLast();
        }
        catch (IllegalArgumentException e)
        {
            OhadInfra.report("test_remove_first_empty_ohad",true);
            return;
        }

        OhadInfra.report("test_remove_first_empty_ohad",false ,"you probebly didnt check that the list is not empty before removing ");
    }

    public static void test_add_first_null_ohad()
    {
        BitList bl = new BitList();
        try{
            bl.addFirst(null);
        }
        catch (IllegalArgumentException e)
        {
            OhadInfra.report("test_add_first_null_ohad",true);
            return;
        }
        OhadInfra.report("test_add_first_null_ohad",false,"you allowed to add null to the list and that is forbidden ");
    }

    public static void test_add_last_null_ohad()
    {
        BitList bl = new BitList();
        try{
            bl.addLast(null);
        }
        catch (IllegalArgumentException e)
        {
            OhadInfra.report("test_add_last_null_ohad",true);
            return;
        }
        OhadInfra.report("test_add_last_null_ohad",false,"you allowed to add null to the list and that is forbidden ");
    }

    public static void test_remove_last_empty_ohad()
    {
        BitList bl = new BitList();
        try{
            bl.removeLast();
        }
        catch (IllegalArgumentException e)
        {
            OhadInfra.report("test_remove_last_empty_ohad",true);
            return;
        }
        OhadInfra.report("test_remove_last_empty_ohad",false,"you probebly didnt check that the list is not empty before removing ");
    }

    public static void test_BitList_tostrong_2_2_ohad()
    {
        BitList b1 = new BitList(); //<>
        b1.addFirst(Bit.ZERO);//   <0>
        b1.addFirst(Bit.ZERO);          //  <00>
        b1.addFirst(Bit.ONE);           // <001>
        OhadInfra.report("test_BitList_tostrong_2_2_ohad" ,b1.toString().equals("<001>"));
    }

    public static void test_BitList_tostrong_2_2_ohad2()
    {
        BitList b1 = BitList_from_string("001");
        OhadInfra.report("test_BitList_tostrong_2_2_ohad2" ,b1.toString().equals("<001>"));
    }

    public static void test_BitList_coping_constructor_2_3_ohad()
    {
        BitList b1 = new BitList();   //        <>
         b1.addFirst(ZERO);            //       <0>
         b1.addFirst(ZERO);            //      <00>
         b1.addFirst(ONE);             //     <001>
         BitList b2 = new BitList(b1); //     <001>
         b2.addFirst(ONE);             //    <0011>
         b2.addFirst(ONE);             //   <00111>
         b2.addFirst(ONE);             //  <001111>

        OhadInfra.report("test_BitList_coping_constructor_2_3_ohad " ,b2.toString().equals("<001111>"));
    }



    public static void test_BitList_isNumber_2_4_true_ohad()
    {
        for (int i = 0; i< VALID_NUMBERS.length; i++)
        {
            BitList b1 = BitList_from_string(VALID_NUMBERS[i]);
            OhadInfra.report("test_BitList_isNumber_2_4_true_ohad "+ VALID_NUMBERS[i] ,b1.isNumber());
        }
    }
    public static void test_BitList_isNumber_2_4_false_ohad()
    {
        for (int i = 0; i< INVALID_NUMBERS.length; i++)
        {
            BitList b1 = BitList_from_string(INVALID_NUMBERS[i]);
            OhadInfra.report("test_BitList_isNumber_2_4_fasle_ohad "+ INVALID_NUMBERS[i] ,!b1.isNumber());
        }
    }

    public static void test_shift_right_2_7_ohad()
    {
        for(int i=0;i<NUMBERS_TO_SHR.length;i++)
        {
            BitList before_shr = BitList_from_string(NUMBERS_TO_SHR[i][0]);
            before_shr.shiftRight();
            String excpected_result = "<"+NUMBERS_TO_SHR[i][1]+">";
            OhadInfra.report("test_shift_right_2_7_ohad "+ NUMBERS_TO_SHR[i][0] ,before_shr.toString(),excpected_result);
        }
    }

    public static void test_shift_left_2_7_ohad()
    {
        for(int i=0;i<NUMBERS_TO_LEFT.length;i++)
        {
            BitList before_shl = BitList_from_string(NUMBERS_TO_LEFT[i][0]);
            before_shl.shiftLeft();
            String excpected_result = "<"+NUMBERS_TO_LEFT[i][1]+">";
            OhadInfra.report("test_shift_left_2_7_ohad "+ NUMBERS_TO_LEFT[i][0] ,before_shl.toString(),excpected_result);
        }
    }

    public static void test_complement_2_6_ohad()
    {
        for(int i=0;i<NUMBERS_TO_COMPLEMENT.length;i++)
        {
            BitList before_comp = BitList_from_string(NUMBERS_TO_COMPLEMENT[i][0]);
            before_comp = before_comp.complement();
            String excpected_result1 = "<"+NUMBERS_TO_COMPLEMENT[i][1]+">";
            String excpected_result2 = "<"+NUMBERS_TO_COMPLEMENT[i][0]+">";
            OhadInfra.report("test_complement_2_6_ohad "+ NUMBERS_TO_COMPLEMENT[i][0] ,before_comp.toString(),excpected_result1);
            before_comp = before_comp.complement();
            OhadInfra.report("test_complement_2_6_ohad "+ NUMBERS_TO_COMPLEMENT[i][1] ,before_comp.toString(),excpected_result2);
        }
    }

    public static void test_padding_2_8_ohad()
    {
        for(int i=0;i<NUMBERS_TO_PADD.length;i++)
        {
            BitList before_padding = BitList_from_string(NUMBERS_TO_PADD[i][0]);
            int padding_amount = Integer.parseInt(NUMBERS_TO_PADD[i][2]);
            before_padding.padding(padding_amount);
            String excpected_result = "<"+NUMBERS_TO_PADD[i][1]+">";
            OhadInfra.report("test_padding_2_8_ohad "+ NUMBERS_TO_PADD[i][0] ,before_padding.toString(),excpected_result);
        }
    }



    public static void test_isReduced_2_5_true_and_false_ohad()
    {
        for(int i=0;i<NUMBERS_TO_REDUCE.length;i++)
        {
            BitList before_reduce = BitList_from_string(NUMBERS_TO_REDUCE[i][0]);
            BitList after_reduce = BitList_from_string(NUMBERS_TO_REDUCE[i][1]);
            OhadInfra.report("test_isReduced_2_5_true "+ NUMBERS_TO_REDUCE[i][1] ,after_reduce.isReduced());
            OhadInfra.report("test_isReduced_2_5_false "+ NUMBERS_TO_REDUCE[i][0] ,!before_reduce.isReduced());

            test_Reduce_2_5(before_reduce,i);
        }
    }

    private static void test_Reduce_2_5(BitList before_reduce,int i)
    {
        before_reduce.reduce();
        String excpected_result = "<"+NUMBERS_TO_REDUCE[i][1]+">";
        OhadInfra.report("test_Reduce_2_5  "+ NUMBERS_TO_REDUCE[i][0],before_reduce.toString(),excpected_result);
    }

    public static void test_BinarryNumber_from_char_3_1_ohad()
    {
        for(int i=0;i<BN_FROM_CHAR.length;i++)
        {
            if(BN_FROM_CHAR[i][1]!=I_A_EXCEPTION) {
                BinaryNumber bn = new BinaryNumber(BN_FROM_CHAR[i][0].charAt(0));
                OhadInfra.report("test_BinarryNumber_from_char_3_1_ohad " + BN_FROM_CHAR[i][0], bn.toString(), BN_FROM_CHAR[i][1]);
            }
            else
            {
                test_bn_from_char_throw_exception(i);
            }
        }
    }

    public static void test_BinarryNumber_equals_3_3_ohad()
    {
        for(int i=0;i<BN_EQUAL.length;i++)
        {
            BinaryNumber bn1 = BinaryNumber_from_bin_string(BN_EQUAL[i]);
            BinaryNumber bn2 = BinaryNumber_from_bin_string(BN_EQUAL[i]);
            String msg = "test_BinarryNumber_equals_3_3_ohad " + BN_EQUAL[i];
            OhadInfra.report(msg, bn1.equals(bn2));
        }
    }

    public static void test_BinarryNumber_add_3_4_ohad()
    {
        for(int i = 0; i< NUMBERS_TO_ADD_SUB.length; i++)
        {
            BinaryNumber bn1 = BinaryNumber_from_int(NUMBERS_TO_ADD_SUB[i][0]);
            BinaryNumber bn2 = BinaryNumber_from_int(NUMBERS_TO_ADD_SUB[i][1]);
            int plus_result = NUMBERS_TO_ADD_SUB[i][0] + NUMBERS_TO_ADD_SUB[i][1];
            BinaryNumber bn3 = bn1.add(bn2);
            String msg = "test_BinarryNumber_add_3_4_ohad "+ NUMBERS_TO_ADD_SUB[i][0]+"+"+ NUMBERS_TO_ADD_SUB[i][1];

            if (TOINT_IMPLEMENTED)
            {
                int result = bn3.toInt();
                OhadInfra.report(msg ,result, plus_result);
            }
            else
            {
                BinaryNumber excpected_ans = BinaryNumber_from_int(plus_result);
                OhadInfra.report(msg ,bn3.toString(), excpected_ans.toString());
            }
        }
    }

    public  static  void test_BinarryNumber_negate_3_5()
    {
        for(int i=0;i<NUMBERS_TO_NEGATE.length;i++)
        {
            BinaryNumber bn1 = BinaryNumber_from_int(NUMBERS_TO_NEGATE[i]);
            BinaryNumber bn2 = BinaryNumber_from_int(-NUMBERS_TO_NEGATE[i]);
            String msg = "test_BinarryNumber_negate_3_5 " + NUMBERS_TO_NEGATE[i];
            OhadInfra.report(msg, bn1.negate().equals(bn2));
            OhadInfra.report(msg, bn2.negate().equals(bn1));
        }
    }

    public  static  void test_BinarryNumber_signum_3_7()
    {
        for(int i=0;i<NUMBERS_TO_NEGATE.length;i++)
        {
            BinaryNumber bn1 = BinaryNumber_from_int(NUMBERS_TO_NEGATE[i]);
            int expected_result =expected_signum(NUMBERS_TO_NEGATE[i]);
            String msg = "test_BinarryNumber_signum_3_7 " + NUMBERS_TO_NEGATE[i];
            OhadInfra.report(msg, bn1.signum(),expected_result);
        }
    }

    private static  int expected_signum(int num)
    {
        if(num>0)
            return 1;
        if (num==0)
            return  0;
        return -1;

    }


    public static void test_BinarryNumber_comperto_3_8_ohad()
    {
        for(int i = 0; i< NUMBERS_TO_COMPER.length; i++)
        {
            BinaryNumber bn1 = BinaryNumber_from_int(NUMBERS_TO_COMPER[i][0]);
            BinaryNumber bn2 = BinaryNumber_from_int(NUMBERS_TO_COMPER[i][1]);
            int result = NUMBERS_TO_COMPER[i][2];
            String msg = "test_BinarryNumber_comperto_3_8_ohad "+ NUMBERS_TO_COMPER[i][0]+" , "+ NUMBERS_TO_COMPER[i][1];
            OhadInfra.report(msg ,result, bn1.compareTo(bn2));

        }
    }

    public static void test_BinarryNumber_subtract_3_6_ohad()
    {
        for(int i = 0; i< NUMBERS_TO_ADD_SUB.length; i++)
        {
            BinaryNumber bn1 = BinaryNumber_from_int(NUMBERS_TO_ADD_SUB[i][0]);
            BinaryNumber bn2 = BinaryNumber_from_int(NUMBERS_TO_ADD_SUB[i][1]);
            int minus_result = NUMBERS_TO_ADD_SUB[i][0] - NUMBERS_TO_ADD_SUB[i][1];
            BinaryNumber bn3 = bn1.subtract(bn2);
            String msg = "test_BinarryNumber_subtract_3_6_ohad "+ NUMBERS_TO_ADD_SUB[i][0]+"-"+ NUMBERS_TO_ADD_SUB[i][1];

            if (TOINT_IMPLEMENTED)
            {
                int result = bn3.toInt();
                OhadInfra.report(msg ,result, minus_result);
            }
            else
            {
                BinaryNumber excpected_ans = BinaryNumber_from_int(minus_result);
                OhadInfra.report(msg ,bn3.toString(), excpected_ans.toString());
            }
        }
    }

    public static void test_bn_from_char_throw_exception(int i)
    {
        try
        {
            BinaryNumber bn = new BinaryNumber(BN_FROM_CHAR[i][0].charAt(0));
        }
        catch (IllegalArgumentException e)
        {
            OhadInfra.report("test_BinarryNumber_from_char_3_1_ohad '" + BN_FROM_CHAR[i][0] + "' got exception as expected",true);
            return;
        }
        OhadInfra.report("test_BinarryNumber_from_char_3_1_ohad '" + BN_FROM_CHAR[i][0] + "' should have gotten an exception",false);

    }


    public static void test_BinarryNumber_toint_3_9_ohad()
    {
        for(int i=0;i<BITS_TO_INT.length;i++)
        {
            BinaryNumber bn = BinaryNumber_from_bin_string(BITS_TO_INT[i][0]);
            int result = bn.toInt();
            OhadInfra.report("test_BinarryNumber_toint_3_9_ohad " ,result,Integer.valueOf(BITS_TO_INT[i][1]));
        }
    }

    public static void test_BinarryNumber_multiply_3_10_ohad()
    {
        for(int i = 0; i< NUMBERS_TO_MUL.length; i++)
        {
            BinaryNumber bn1 = BinaryNumber_from_int(NUMBERS_TO_MUL[i][0]);
            BinaryNumber bn2 = BinaryNumber_from_int(NUMBERS_TO_MUL[i][1]);
            BinaryNumber bn3 = bn1.multiply(bn2);
            int result = bn3.toInt();
            String msg = "test_BinarryNumber_multiply_3_10_ohad "+ NUMBERS_TO_MUL[i][0]+"*"+ NUMBERS_TO_MUL[i][1];
            OhadInfra.report(msg ,result, NUMBERS_TO_MUL[i][2]);
        }
    }

    public static void test_BinarryNumber_divide_3_11_ohad()
    {
        for(int i = 0; i< NUMBERS_TO_DIV.length; i++)
        {
            BinaryNumber bn1 = BinaryNumber_from_int(NUMBERS_TO_DIV[i][0]);
            BinaryNumber bn2 = BinaryNumber_from_int(NUMBERS_TO_DIV[i][1]);
            BinaryNumber bn3 = bn1.divide(bn2);
            int result = bn3.toInt();
            String msg = "test_BinarryNumber_divide_3_11_ohad "+ NUMBERS_TO_DIV[i][0]+"/"+ NUMBERS_TO_DIV[i][1];
            OhadInfra.report(msg ,result, NUMBERS_TO_DIV[i][2]);
        }
    }



    public static void test_BinarryNumber_from_decimal_string_3_12_ohad()
    {
        for(int i=0;i<BINARRY_NUMBER_FROM_DEC_STRING.length;i++)
        {
            BinaryNumber bn_from_decimal = new BinaryNumber(BINARRY_NUMBER_FROM_DEC_STRING[i]);
            String msg = "test_BinarryNumber_from_decimal_string_3_12_ohad " + BINARRY_NUMBER_FROM_DEC_STRING[i];
            OhadInfra.report(msg,bn_from_decimal.toInt(),Integer.valueOf(BINARRY_NUMBER_FROM_DEC_STRING[i]));
        }
    }


    public static void test_binarrynumber_random_biginteger_3_12_3_13_ohad()
    {
        Random rd = new Random();
        BigInteger randBig,minusbi;
        BigInteger NEGATIVE_ONE = new BigInteger("-1");
        for(int i = 0;i<AMOUNT_OF_RANDOMS;i++)
        {
            randBig = new BigInteger(50, rd);
            BinaryNumber bn = new BinaryNumber(randBig.toString());
            minusbi =randBig.multiply(NEGATIVE_ONE);
            BinaryNumber bnm = new BinaryNumber(minusbi.toString());
            OhadInfra.report("test_binarrynumber_random_biginteger_3_12_13_ohad " + randBig,bn.toIntString(),randBig.toString());
            OhadInfra.report("test_binarrynumber_random_biginteger_3_12_13_ohad " + randBig,bnm.toIntString(),minusbi.toString());
        }
    }

    public static void test_binarrynumber_tointstring_0()
    {
        BinaryNumber bn = new BinaryNumber('0');
        OhadInfra.report("test_binarrynumber_tointstring_0 ",bn.toIntString(),"0");
    }


    public static void test_binarrynumber_random_biginteger_calculations_3_4_5_6_10_11_ohad()
    {
        Random rd = new Random();
        BigInteger randBig,randBig2,excpected_add,excpected_sub,excpected_mul,excpected_div;
        BigInteger NEGATIVE_ONE = new BigInteger("-1");
        for(int i = 0;i<AMOUNT_OF_RANDOMS;i++)
        {
            randBig = new BigInteger(50, rd);
            randBig2= new BigInteger(50, rd);
            BinaryNumber bn = new BinaryNumber(randBig.toString());
            BinaryNumber bn2 = new BinaryNumber(randBig2.toString());
            excpected_add = randBig.add(randBig2);
            excpected_sub = randBig.subtract(randBig2);
            excpected_mul = randBig.multiply(randBig2);
            excpected_div = randBig.divide(randBig2);
            String add_msg = "test_binarrynumber_random_biginteger_calculations_3_4_5_10_11_ohad add " + randBig +" + " +randBig2;
            OhadInfra.report(add_msg,bn.add(bn2).toIntString(),excpected_add.toString());
            String sub_msg = "test_binarrynumber_random_biginteger_calculations_3_4_5_10_11_ohad sub " + randBig +" - " +randBig2;
            OhadInfra.report(sub_msg,bn.subtract(bn2).toIntString(),excpected_sub.toString());
            String mul_msg = "test_binarrynumber_random_biginteger_calculations_3_4_5_10_11_ohad mul " + randBig +" * " +randBig2;
            OhadInfra.report(mul_msg,bn.multiply(bn2).toIntString(),excpected_mul.toString());
            String div_msg = "test_binarrynumber_random_biginteger_calculations_3_4_5_10_11_ohad div " + randBig +" / " +randBig2;
            OhadInfra.report(div_msg,bn.divide(bn2).toIntString(),excpected_div.toString());
            String div1_msg = "test_binarrynumber_random_biginteger_calculations_3_4_5_10_11_ohad div " + randBig +" / " +1;
            OhadInfra.report(div1_msg,bn.divide(new BinaryNumber('1')).toIntString(),bn.toIntString());
            String negate_msg = "test_binarrynumber_random_biginteger_calculations_3_4_5_10_11_ohad negate " + randBig +" / " +1;
            OhadInfra.report(negate_msg,bn.negate().toIntString(),randBig.multiply(NEGATIVE_ONE).toString());

        }
    }









    private static  BitList BitList_from_string(String str)
    {
        BitList b1 = new BitList();
        while(str.length()>0)
        {
            b1.addFirst(new Bit(str.charAt(0)=='1'));
            str = str.substring(1);
        }
        return b1;
    }

    private static  BinaryNumber BinaryNumber_from_bin_string(String str)
    {
        BitList b1 = BitList_from_string(str);
        return new BinaryNumber(b1);
    }

    public static BinaryNumber BinaryNumber_from_int(int num)
    {
        boolean is_negativ = num<0;
        if (is_negativ)
            num*=-1;
        String bin_st = bit_string_from_int(num);
        BinaryNumber ans = BinaryNumber_from_bin_string(bin_st);
        if (is_negativ)
            ans= ans.negate();
        return ans;
    }



    public static void test_BitList_from_string()
    {
        BitList bl_org = new BitList();
        bl_org.addFirst(Bit.ONE);
        bl_org.addFirst(Bit.ZERO);
        bl_org.addFirst(Bit.ZERO);
        bl_org.addFirst(Bit.ZERO);
        bl_org.addFirst(Bit.ZERO);
        bl_org.addFirst(Bit.ZERO);
        BitList bl_str = BitList_from_string("100000");
        System.out.println("bl_org : "+bl_org);
        System.out.println("bl_str : "+bl_str);
        OhadInfra.report("test_BitList_from_string" , bl_org.toString().compareTo(bl_str.toString())==0);
        OhadInfra.report("test_isNumber 100000" , !bl_org.isNumber());

    }

    public static String bit_string_from_int(int num)
    {
        String str = "";
        while(num>0)
        {
            int curr_bit = num%2;
            str=curr_bit+str;
            num/=2;
        }
        str = "0"+str;
        return str;
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
