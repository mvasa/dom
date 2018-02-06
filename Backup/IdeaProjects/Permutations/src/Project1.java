import java.util.Scanner;


public class Project1 {
    public static void main(String[] args)
    {
        System.out.println("enter n: ");
        int root = new Scanner(System.in).nextInt();

        generateAddition(root, 0);
    }

    static boolean t = false;

    private static void ifLess(int addend, int root)
    {/////////////////////////////////////////////////////////////////////////////////////////////////////////breakpoint here
        System.out.print(addend);
        boolean moreIterations = false;
        int nextInteger = 0;
        int original = addend;
        int nextInt = addend;
        int sum = 0;

        while(nextInt!=0){
            sum = addend + nextInt;
            if(sum == root){
                System.out.println("+" + nextInt);
                nextInt = 0;
            }
            else if(sum > root){
                nextInt--;
            }
            else if(sum < root){
                if(nextInt > 1){
                    moreIterations = true;
                    nextInteger = nextInt;
                }
                System.out.print("+" + nextInt);
                if(sum + nextInt > root){
                    nextInt--;
                    addend = sum;
                }
                else if(sum + nextInt == root){
                    System.out.println("+" + nextInt);
                    nextInt = 0;
                }
                else{
                    addend = sum;
                }
            }
        }
        if(moreIterations == true)
        {
            System.out.print(original);
            nextInteger--;
            calcRest(root, nextInteger, original);
        }

        /*addend--;
        generateAddition(root, addend);



        while (sum < root)
        {
            sum = sum + 1;
            System.out.print("+1");
            if (sum == root)
            {
                System.out.println();
                if(addend == 0 && t == true)
                {
                    return;
                }
                generateAddition(root, addend - 1);
            }
        }*/
    }
    /*private static void ifLess(int addend, int root)
    {
        System.out.print(addend);
        int sum = addend;
        while (sum < root)
        {
            sum = sum + 1;
            System.out.print("+1");
            if (sum == root)
            {
                System.out.println();
                if(addend == 0 && t == true)
                {
                    return;
                }
                generateAddition(root, addend - 1);
            }
        }
    }*/

    private static void ifMore(int addend, int nextInt, int root) {
        boolean intNext = false;
        int nextInteger = 0;
        int sum;

        if (addend == 0 && t == true)
        {
            return;
        }
        System.out.print(addend);
        while (nextInt != 0)
        {
            nextInt -= 1;
            sum = addend + nextInt;
            if (sum < root && nextInt != 0)
            {
                if(intNext == false){
                    nextInteger = nextInt;
                    intNext = true;
                    nextInt = 0;
                }
                //Write System.out.print(addend + "+"); instead
                //System.out.print("+" + nextInt);
                //addend = sum;
            }
            if(sum == root)
            {
                System.out.println("+" + nextInt);

            }
        }
        if(intNext)
        {////////////////////////////////////////////////////////////breakpoint here
            System.out.print(addend);
            calcRest(root, nextInteger, addend);
        }
        addend = addend - 1;
        generateAddition(root, addend);
    }
    private static void calcRest(int root, int next, int addend){
        /*
        pseudocode
        ----------------------
        next identifies the next number that is added to the addend that produces sum less than root

        while next-- != 0
        find summation for next --
        if sum == root
        -> next --
        and find next summation...
         */

        int original = addend;
        int nextIteration = next;
        int iteration = next;
        int sum;

        while(iteration != 0){
            sum = addend + next;
            if(sum == root){
                System.out.println("+" + next);
                iteration--;
                if(iteration != 0){
                    addend = original;
                    System.out.print(addend);

                }

                next = nextIteration - 1;
                nextIteration--;
            }
            else if(sum > root){
                next--;
            }
            else{
                System.out.print("+" + next);
                addend = sum;
                if(addend + next > root){
                    next--;
                }

            }

        }

    }

    /*private static void ifMore(int addend, int nextInt, int root)
    {
        int sum;
        while (nextInt != 0) {
            nextInt -= 1;
            sum = addend + nextInt;
            if (sum == root) {
                System.out.println(addend + "+" + nextInt);
                if(nextInt > 1)
                {
                    calcRemaining(addend, root);
                }
                if (addend == 0 && t == true) {
                    return;
                }
                generateAddition(root, addend - 1);
            }
        }
    }*/

    private static void ifEquals(int addend, int nextInt, int root)
    {
        System.out.println(addend + "+" + nextInt);
        if(nextInt > 1)
        {
            calcRemaining(addend, root);
        }
        if(addend == 0 && t == true ){
            return;
        }
        generateAddition(root, addend - 1);
    }

    //edit this method so it calculates values with nextints greater than one
    private static void calcRemaining(int addend, int root)
    {
        System.out.print(addend);
        int sum = addend;
        while (sum < root)
        {
            sum = sum + 1;
            System.out.print("+1");
            if (sum == root)
            {
                System.out.println();
            }
        }
    }
    private static void generateAddition(int root, int addend)
    {
        if (addend == 0 && t == false)
        {
            addend = root;
            System.out.println(addend);
            t = true;
            generateAddition(root, addend - 1);
        }
        else if(addend == 0 && t == true)
        {
            System.out.println("\nSUCCESS!\n");
        }
        else if (addend > 0)
        {
            int nextInt = addend;
            int sum = addend + nextInt;
            if (sum == root)
            {
                ifEquals(addend, nextInt, root);

            }
            else if (sum < root)
            {
                ifLess(addend, root);
                addend = addend - 1;
                generateAddition(root, addend);
            }
            else if(sum > root)
            {
                ifMore(addend, nextInt, root);

            }
        }
    }
}