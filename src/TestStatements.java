    /*
     * if.
     * if ,else.
     * if ,else , if.
     * switch.
     * "!" in this program I will only use switch statement.
     */

     import java.util.Scanner;

     public class TestStatements {
     
         public static void main(String[] args) {
             char c;
             int x1, x2;
             
             try (Scanner in = new Scanner(System.in)) {
                 System.out.println("Enter one of these operators: *, /, +, -");
                 c = in.next().charAt(0);
                 
                 System.out.println("Enter x1:");
                 x1 = in.nextInt();
                 
                 System.out.println("Enter x2:");
                 x2 = in.nextInt();  // Fixed typo from x1 to x2
     
                 switch (c) {
                     case '+':
                         System.out.println("The result = " + (x1 + x2));
                         break;
                     case '*':
                         System.out.println("The result = " + (x1 * x2));
                         break;
                     case '/': //I used if, else here to prevent division by zero!
                         if (x2 == 0) {
                             System.out.println("Error: Division by zero!");
                         } else {
                             System.out.println("The result = " + (x1 / x2));
                         }
                         break;
                     case '-':
                         System.out.println("The result = " + (x1 - x2));
                         break;
                     default:
                         System.out.println("Invalid operator entered!");
                         break;
                 }
             }
         }
     }