import java.util.Scanner;

 //In this program, I'm using a for loop to check whether the number entered by the user is a prime number.

public class TestStatement_2 {
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
     
        int number;
        
        System.out.print("Enter the positive integer ");
        number = console.nextInt();
        
        boolean flag = true;
        
        for(int i = 2; i < number; i++)
	{
	    if(number % i == 0)
            {
                flag = false;
                break;
            }
        }

	if(flag && number > 1)
        {
            System.out.println("Number is prime");
        }
	else
        {
            System.out.println("Number is not prime");
        }
        
    }  
    
}
