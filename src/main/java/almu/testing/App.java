package almu.testing;

import almu.testing.utils.StructureBuilder;
import almu.testing.utils.StructureDirector;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        App app = new App();

        //  Get string from input stream
        System.out.print("Enter a string : ");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        // Print user-friendly format
        System.out.println(app.run(inputString));
    }

    public String run(String inputString) {
        // Todo: Not clear what to do with uppercase.
        // Should it be handled otherwise?
        // Not enough requirements -> I have simplified this question so far
        // Todo: Input string should be validated validation
        // @NotNull, @NotEmpty, @Pattern
        inputString = inputString.toLowerCase();

        //  Build the desired structure
        StructureBuilder builder = new StructureBuilder();
        StructureDirector director = new StructureDirector(builder);
        director.construct(inputString);

        return builder.getResult();
    }
}
