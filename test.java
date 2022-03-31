import java.util.Scanner;

public class test {
    private static int[] ifCrossArray;
/* SELF ASSESSMENT
   1.    createSequence:

    Did I use the correct method definition?
    Mark out of 5: 5
    Comment: I believe i have used the correct method definition
    Did I create an array of size n (passed as the parameter) and initialise it?
    Mark out of 5:3
    Comment: I have initialised my array however, i believe i have not created the array of size n properly.
    Did I return the correct item?
    Mark out of 5: 5
    Comment: Yes i returned the correct item.

   2.    crossOutMultiples

    Did I use the correct method definition?
    Mark out of 5: 5
    Comment: yes i used the correct method definition
    Did I ensure the parameters are not null and one of them is a valid index into the array
    Mark out of 2: 2
    Comment: I  ensured the parameters are not null and one of them is a valid index into the array
    Did I loop through the array using the correct multiple?
    Mark out of 5: 5
    Comment: I looped through the array using the correct multiple
    Did I cross out correct items in the array that were not already crossed out?
    Mark out of 3: 3
    Comment: I crossed out correct items in the array that were not already crossed out

   3.    sieve

    Did I have the correct function definition?
    Mark out of 5: 5
    Comment: i used the correct function definition
    Did I make calls to other methods?
    Mark out of 5: 5
    Comment: I made calls to other methods.
    Did I return an array with all non-prime numbers are crossed out?
    Mark out of 2: 2
    Comment: Yes i have returned an array with all non-prime numbers are crossed out.

   4.    sequenceTostring

    Did I have the correct function definition?
    Mark out of 5: 5
    Comment: I have the correct function definition.
    Did I ensure the parameter to be used is not null?
    Mark out of 3: 3
    Comment:  I made sure my parameter did not use null
    Did I Loop through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets?
    Mark out of 10: 10
    Comment: I Looped through the array updating the String variable with the non-crossed out numbers and the crossed numbers in brackets

   5.    nonCrossedOutSubseqToString

    Did I have the correct function definition
    Mark out of 5: 5
    Comment: I believe i had the correct function definition.
    Did I ensure the parameter to be used is not null?
    Mark out of 3:0
    Comment:I used null as i only read the self assessment no. I am really sorry, and i apologies for this. I will most definitly read the self assessment first. Due to the question being really lengthy, I lost my way in alot of the functions.
    Did I loop through the array updating the String variable with just the non-crossed out numbers?
    Mark out of 5: 5
    Comment: I looped through the array updating the String variable with just the non-crossed out numbers.

   6.    main

    Did I ask  the user for input n and handles input errors?
    Mark out of 5: 3
    Comments: I asked the user for input but i did not handle the errors.
    Did I make calls to other methods (at least one)?
    Mark out of 5: 5
    Comment:  I have made calls to other methods
    Did I print the output as shown in the question?
    Mark out of 5: 3
    Comment: I did not print the output exactly as shown in the question.

   7.    Overall

    Is my code indented correctly?
    Mark out of 4: 4
    Comments: I believe i have indented my code properly
    Do my variable names make sense?
    Mark out of 4: 4
    Comments: I think variable names make sense
    Do my variable names, method names and class name follow the Java coding standard
    Mark out of 4: 3
    Comments: I think my method names and class follow the java coding standard however due to my code being very long, there is no doubt my coding standards maybe not as good.

      Total Mark out of 100 (Add all the previous marks): 90
*/

        public static int[] createSequence(int inNumber)
        {
            int [] inArray = new int [inNumber - 1];

            for(int count = 0; count < inArray.length; count++)
            {
                inArray[count] = count + 2;
            }
            return inArray;

        }

        public static int[] crossOutHigherMultiples (int[] inSequence, int crossMul, int inNumber)
        {

            int CrossArray [] = new int[inNumber - 1];
            boolean higherMul = false;

            if (crossMul < 2 && inNumber < 2)
            {
                System.exit(1);
            }

            if (inSequence == null)
            {
                System.exit(1);
            }

            for(int count = 0; count < inNumber-1; count++)
            {

                if ((inSequence [count] % crossMul == 0) && (higherMul == false) )
                {
                    higherMul = true;
                    continue;
                }

                if ((inSequence [count] % crossMul == 0) && (higherMul == true))
                {
                    CrossArray [count] = inSequence [count];
                }
            }
            return CrossArray;
        }


        public static int [] sieve (int [] inSequence, int inNumber)
        {
            int crossArray[] = new int [inNumber - 1];
            int mergeCross [];
            int greaterTwo = 2;

            for (int count = 0; count < inNumber - 1; count++)
            {
                mergeCross = crossOutHigherMultiples(inSequence, greaterTwo, inNumber);

                for (int i =0; i < inNumber -1; i++)
                {
                    if (mergeCross[i]!=0)
                    {
                        crossArray[i] = mergeCross[i];
                    }
                }

                System.out.println(sequenceToString(inSequence, crossArray, inNumber));

                for(int count1 = 0; count1 < inNumber -1; count1++)
                {
                    if(inSequence[count1] == greaterTwo)
                    {
                        for (int i =count1 + 1; i < inNumber-1; i++)
                        {
                            if (crossArray[i] ==0)
                            {
                                greaterTwo = inSequence[i];
                                break;
                            }

                        }
                        break;
                    }
                }
                if (greaterTwo >= inSequence[inNumber -2]/2)
                {
                    break;
                }

            }
            return crossArray;

        }

        public static String sequenceToString (int inSequence[], int crossArray[], int inNumber)
        {
            String comBracket = "";

            if(inSequence == null)
            {
                System.exit(1);
            }

            for(int count =0; count < inNumber - 1; count++)
            {
                if (crossArray[count] == 0)
                {
                    comBracket += inSequence[count]+",";
                }
                else
                {
                    comBracket += "[" + inSequence[count] + "],";
                }
            }

            return comBracket;
        }

        public static String nonCrossedOutSubseqToString (int [] inSequence, int inNumber)
        {
            String inPrime = "";

            if(inSequence == null)
            {
                System.exit(1);
            }

            for ( int count = 0; count < inNumber - 1; count++)
            {
                if (ifCrossArray[count] == 0)
                {
                    inPrime += inSequence[count] + " ";
                }
            }

            return inPrime;

        }

        public static void main(String[] args) {

            System.out.print("Enter int >= 2 : ");
            Scanner input = new Scanner(System.in);
            int userNum  = input.nextInt();


            int inSequence[];

            inSequence = createSequence(userNum);
            ifCrossArray = sieve(inSequence, userNum);

            System.out.println(nonCrossedOutSubseqToString(inSequence,userNum));



        }
    }



