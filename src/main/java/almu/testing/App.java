package almu.testing;

import almu.testing.utils.StructureBuilder;
import almu.testing.utils.StructureDirector;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        //  Get string from input stream
        System.out.print("Enter a string : ");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        //  Build the desired structure
        StructureBuilder builder = new StructureBuilder();
        StructureDirector director = new StructureDirector(builder);
        director.construct(inputString);

        // Print user-friendly format
        System.out.println(builder.getResult());
    }
}
