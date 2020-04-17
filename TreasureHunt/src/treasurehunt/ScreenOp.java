package treasurehunt;

import java.io.IOException;
import java.util.Scanner;

public class ScreenOp {
    
    /*
        
        This class contains screen related methods,
        One is clear screen method, that is static, when called clear the screen
        Other is promt enter key method, that is also static, when called pause the action unless Enter key is not pressed
    
    */
    
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
