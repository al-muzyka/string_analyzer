package almu.testing;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.print("Enter a string : ");
        Scanner scanner = new Scanner(System. in);
        String inputString = scanner.nextLine();
        System.out.println("String read from console is : \n"+inputString);
    }
}
