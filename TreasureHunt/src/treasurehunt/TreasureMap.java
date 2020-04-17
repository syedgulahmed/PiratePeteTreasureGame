package treasurehunt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreasureMap {
    
    private int numberOfTreasures;
    private char[][] mapArray;
    private Scanner scan;
    
    TreasureMap()throws FileNotFoundException{
        
        mapArray = new char[10][10];
        scan = new Scanner(System.in);
        
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                mapArray[row][col] = ' ';
            }
        }
        
        loadTreasureFromFile();
        
    }
    
    public boolean getSquareFromUser(){
        
        boolean isValid;
        int row;
        int column;
        
        System.out.print("Input a square, i.e (4 B), Make sure there is space b/w row and column: ");
        
        do{
            
            row = Integer.parseInt(scan.next()) - 1;
            
            column = getColPosition(scan.next());
            
            if(row >= 0 && row < 10){
                if(column >= 0 && column < 10){
                    isValid = true;
                }else{
                    isValid = false;
                }
            }else{
                isValid = false;
            }
            
            if(!isValid){
                System.out.print("Invalid input, retry.");
            }else if(mapArray[row][column] != ' ' && mapArray[row][column] != 'H'){
                System.out.print("This square was already discovered, select another: ");
                isValid = false;
            }
            
        }while(!isValid);
        
        if(mapArray[row][column] == 'H'){
            numberOfTreasures--;
            mapArray[row][column] = 'T';
            return true;
        }else{
            mapArray[row][column] = 'E';
            return false;
        }
        
    }
    
    public int getColPosition(String position){
        
        char value = position.charAt(0);
        
        int asciiValue = (int) value;
        
        asciiValue = asciiValue - 64;
        
        return (asciiValue - 1);
    }
    
    public void showTreasureMap(){
        System.out.print("   ");
        System.out.println("  A     B     C     D     E     F     G     H     I     J");
        for(int i = 0; i < 10; i++){
            
            if(i != 9)
                System.out.print(i + 1 + "  ");
            else
                System.out.print(i + 1 + " ");
            
            
            for(int j = 0; j < 10; j++){
                
                if(mapArray[i][j] == 'H'){
                    System.out.print("[   ] ");
                }else{
                    System.out.print("[ " + mapArray[i][j] +" ] ");
                }
                
                
            }
            
            System.out.println("");
        }
    }
    
    private void loadTreasureFromFile() throws FileNotFoundException{
        
        File text = new File("PiratePete.txt");
        numberOfTreasures = 0;
        String str;
        
        int row;
        int column;
        
        Scanner readFile = new Scanner(text);
        
        while(readFile.hasNextLine()){
            
            str = readFile.nextLine();
            
            row = Integer.parseInt(str.substring(0, 1)) - 1;
            column = getColPosition(str.substring(2, 3));
            
            mapArray[row][column] = 'H';
            
            numberOfTreasures++;
            
            if(numberOfTreasures == 4){
                break;
            }
            
            
        }
        
        if(readFile.hasNextLine()){
            System.out.println("More than 4 treasures found, only four treasures will be considered.");
        }else if(numberOfTreasures <= 0){
            System.out.println("No treasures found. Can't Continue Game!");
            System.exit(0);
        }
        
        
    }

    public int getNumberOfTreasures() {
        return numberOfTreasures;
    }
    
}