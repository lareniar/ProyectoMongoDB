
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class View {
	
    ConsoleInput consoleInput = new ConsoleInput();
    Scanner sc = new Scanner(System.in);

    public String returnChoiceValue(){
    return ConsoleInput.consoleInput();
    }
}
