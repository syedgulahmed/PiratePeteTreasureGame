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
        
        //Labeling white space to Array that contains map values
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                mapArray[row][col] = ' ';
            }
        }
        
        loadTreasureFromFile();
        
    }
    
    public boolean getSquareFromUser(){
        
        //Method to get value of selected square from user.
        
        boolean isValid;
        int row;
        int column;
        
        System.out.print("Input a square, i.e (4 B), Make sure there is space b/w row and column: ");
        
        //This do while loop, keep asking for square position until it does not get valid square
        do{
           
            //Getting row value
            row = Integer.parseInt(scan.next()) - 1;
            
            //Gettng column value
            column = getColPosition(scan.next());
            
            //Making sure row and column values are valid
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
                //This block will run incase of invalid column or row value
                System.out.print("Invalid input, retry.");
            }else if(mapArray[row][column] != ' ' && mapArray[row][column] != 'H'){
                //This block will run if already discovered square was agian selected
                System.out.print("This square was already discovered, select another: ");
                isValid = false;
            }
            
        }while(!isValid);
        
        if(mapArray[row][column] == 'H'){
            //This block will run if selected square contained Treasure
            //and Labeel it T, to show the square containing treasure have been discovered
            numberOfTreasures--;
            mapArray[row][column] = 'T';
            return true;
        }else{
            //This block will run if selected square does not contain Treasure
            //and Labeel it E, to show the square did contain treasure have been discovered
            mapArray[row][column] = 'E';
            return false;
        }
        
    }
    
    public int getColPosition(String position){
        
        //Method for converting Alpha value of column to numeral value
        
        char value = position.charAt(0);
        
        int asciiValue = (int) value;
        
        asciiValue = asciiValue - 64;
        
        return (asciiValue - 1);
    }
    
    public void showTreasureMap(){
        
        //Method for displaying Treasure Map
        
        System.out.print("   ");
        //Showing column labelling
        System.out.println("  A     B     C     D     E     F     G     H     I     J");
        for(int i = 0; i < 10; i++){
            
            //Following if statment will label the row with numbers
            if(i != 9)
                System.out.print(i + 1 + "  ");
            else
                System.out.print(i + 1 + " ");
            
            //Printing the sqaures
            for(int j = 0; j < 10; j++){
                
                if(mapArray[i][j] == 'H'){
                    //This block will run if square contains undiscovered treasure to hide it
                    System.out.print("[   ] ");
                }else{
                    //This block will run to display normal squares, which may
                    //which may be discovered treasures or empty squares
                    //or may be undescovered squares boxes
                    System.out.print("[ " + mapArray[i][j] +" ] ");
                }
                
                
            }
            
            System.out.println("");
        }
    }
    
    private void loadTreasureFromFile() throws FileNotFoundException{
        
        //Method for reading treausres from PiratePete.txt file
        
        //Creating file object
        File text = new File("PiratePete.txt");
        numberOfTreasures = 0;
        String str;
        
        int row;
        int column;
        
        //Creating scanner object
        Scanner readFile = new Scanner(text);
        
        //Reading all the treasures from file one by one
        while(readFile.hasNextLine()){
            
            str = readFile.nextLine();
            
            //Reading row value
            row = Integer.parseInt(str.substring(0, 1)) - 1;
            
            //Reading column value
            column = getColPosition(str.substring(2, 3));
            
            //Labelling the array square with H that posses treasure
            mapArray[row][column] = 'H';
            
            //Increasing number of total treasures
            numberOfTreasures++;
            
            //Making sure treasures are not more than 4
            if(numberOfTreasures == 4){
                break;
            }
            
            
        }
        
        //Making sure the number of treasures must conform the validity
        if(readFile.hasNextLine()){
            //This block will run there are more than 4 treasures.
            System.out.println("More than 4 treasures found, only four treasures will be considered.");
        }else if(numberOfTreasures <= 0){
            //This block will run if there is no treasure in the file.
            System.out.println("No treasures found. Can't Continue Game!");
            System.exit(0);
        }
        
        
    }

    public int getNumberOfTreasures() {
        
        //Method for getting remaining treasures
        
        return numberOfTreasures;
    }
    
}