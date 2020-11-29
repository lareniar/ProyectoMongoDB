

import java.util.Scanner;

public class ConsoleInput {
    public static String consoleInput(){
        // string input of user
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige una opcion:");
        System.out.println("1 - Insert");
        System.out.println("2 - PrintAll");
        System.out.println("3 - Update field");
        System.out.println("4 - Delete field");
        System.out.println("5 - CodecRegistry insert");
        System.out.println("0 - Salir");
        String userInput = sc.nextLine();
        return userInput;
    };
}
