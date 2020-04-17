package treasurehunt;

import java.io.IOException;
import java.util.Scanner;

public class ScreenOp {
    
    public static void clearScreen() {  
        try {
                if( System.getProperty( "os.name" ).startsWith( "Window" ) ) {
                        Runtime.getRuntime().exec("cls");
                } else {
                        Runtime.getRuntime().exec("clear");
                }
            } catch (IOException e) {

                for(int i = 0; i < 1000; i++) {
                    System.out.println();
                }
            } 
    } 
    
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
}
