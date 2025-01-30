import java.util.Scanner;
//I import Scanner that allows me to input things 

public class UserInput {
    public static void main(String[] args) {
        //I could write "Scanner input = new Scanner(System.in)" and it will work , but it's prefered to use this "try (Scanner input = new Scanner(System.in)){}"
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Please enter number 1 here :");
            int x = input.nextInt();
            System.out.println("Please enter number 2 here :");
            float y = input.nextFloat();
            System.out.println("number 1: "+x+" "+"number 2: "+y);
            //Notice that printf is more simple than println
            System.out.printf("number 1: %d number 2: %.2f",x,y);
            /*
             * d : byte, int, short, long
             * f : float
             * cC : character and C will uppercase the letter
             * sS : string and S will uppercase all the string 
             * n : new line (%n) 
             */
        }
    }
    
}
