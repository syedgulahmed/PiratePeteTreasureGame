package treasurehunt;

import java.util.Random;
import java.util.Scanner;

public class Game {
    
    int playerTurn;
    boolean firstCycle;
    
    Random rand;
    Player[] player;
    TreasureMap map;
    
    public Game(Player[] player, TreasureMap map){
        
        this.map = map;
        this.player = player;
        
        rand = new Random();
        playerTurn = 0;
        firstCycle = true;
        
    }
    
    public void gameInProgress(){
        
        boolean gameOver = false;
        
        /*
        This do while loop will keeping goind until all
        treasures are not found or all players have not run out of
        dig points. After which this game will end
        */
        
        do{
           
            if(playerTurn >= player.length){
                
                //This block will run after every cycle of game
                
                playerTurn = 0;
                showPointsTable();
                firstCycle = false;
            }
            
            //Checking if it is first round of game so dig points are provided
            if(firstCycle){
                initiatePoints();
            }
            
            //Core of Game
            
            ScreenOp.clearScreen();
            map.showTreasureMap();
            
            //Showing players turn with his name
            System.out.println("Argh.. Pirate" + player[playerTurn].getFirstName() + 
                    ".. it be your turn to dig for me treasure.");
            
            if(player[playerTurn].getDigPoints() > 0){
                
                //This block will run if dig points are greater than 0
                //alowing user to select square
                
                if(map.getSquareFromUser()){
                    //If selected square contains treasure
                    player[playerTurn].setPiratesPoinsts(20);
                    System.out.println("Yo-ho-ho and a bottle of rum. I found me some pieces of eight.");
                }else{
                    //If selected square does not contain square
                    System.out.println("Walk the plank! There be no treasure here!");
                }
                
                //Decreasing dig point for using their turn
                player[playerTurn].setDigPoints(-1);
                
            }else{
            
                //This block will run if there are no dig points
                
                System.out.println("\nArgh, Captain, me shovel has broken!");
                
                //This will ask user if they want to purchase shovel
                if(askForShovel(player[playerTurn])){
                    //If user purchased the shovel it continue to ask for selected square
                    if(map.getSquareFromUser()){
                        player[playerTurn].setPiratesPoinsts(20);
                        System.out.println("Yo-ho-ho and a bottle of rum. I found me some pieces of eight.");
                    }else{
                        System.out.println("Walk the plank! There be no treasure here!");
                    }
                    
                    //Decreasing dig point for using their turn
                    player[playerTurn].setDigPoints(-1);
                    
                }
                
                //If user did not purchase the shovel he will miss the turn
            
            }
            
            ScreenOp.promptEnterKey();
            
            //Core of Game
            
            //Checking if all treasures have been found to end the game
            if(map.getNumberOfTreasures() <= 0){
                gameOver = true;
            }
            
            //Checking if all palyers are running out of dig points to end the game
            if(!hasAnyPlayerLeftWithDigPoints()){
                gameOver = true;
            }
            
            playerTurn++;
            
        }while(!gameOver);
        
    }
    
    private void initiatePoints(){
        
        //Method for assinging dig points initially
        
        ScreenOp.clearScreen();
        
        int digPoints;
        
        do{
           digPoints = rand.nextInt(8);
        }while(digPoints <= 4);
        
        player[playerTurn].setDigPoints(digPoints);
        player[playerTurn].setPiratesPoinsts(-(digPoints * 5));
        
        System.out.println("\n" + player[playerTurn].getFirstName());
        System.out.println("Dig points: " + player[playerTurn].getDigPoints());
        System.out.println("Pirates points: " + player[playerTurn].getPiratesPoinsts());
        
        ScreenOp.promptEnterKey();
    
    }
    
    private void showPointsTable(){
        
        //This method will display the points
        
        ScreenOp.clearScreen();
        
        for(int i = 0; i < player.length; i++){
            
            System.out.println(player[i].getFirstName());
            System.out.println("Dig points: " + player[i].getDigPoints());
            System.out.println("Pirate Points: " + player[i].getPiratesPoinsts());
            System.out.println();
            
        }
        
        ScreenOp.promptEnterKey();
    
    }
    
    private boolean hasAnyPlayerLeftWithDigPoints(){
        
        //This method will checking if all players have gone out of dig points to end the game
        
        for(int i = 0; i < player.length; i++){
            
            if(player[i].getDigPoints() > 0){
                return true;
            }
            
        }
        
        return false;
    
    }
    
    private boolean askForShovel(Player player){
        
        //This method will ask for shovek from user if they want to use it or not
        
        System.out.println("Do you want shovel for 10 piratesPoints (Y/N)");
        
        Scanner scan = new Scanner(System.in);
        
        if(scan.next() != "Y"){
            return false;
        }
    
        int digPoints;
        
        do{
            digPoints = rand.nextInt(8);
        }while(digPoints < 4);
        
        player.setDigPoints(digPoints);
        player.setPiratesPoinsts(-10);
        
        return true;
        
    }
    
    public void decideWinner(){
        
        //THis method will decide the winner
        
        ScreenOp.clearScreen();
        
        Player temp;
    
        //Deciding winner according to Pirates points
        for (int i = 0; i < player.length; i++) {
            for (int j = 1; j < (player.length - i); j++) {
                if (player[j - 1].getPiratesPoinsts() > player[j].getPiratesPoinsts()) {
                    temp = player[j - 1];
                    player[j - 1] = player[j];
                    player[j] = temp;
                }
            }
        }
        
        //If there is no draw according to pirate points then winner is selected
        if(player[0].getPiratesPoinsts() != player[1].getPiratesPoinsts()){
            System.out.println("Shiver me Timbers, me hearties, sure hasn’t "
                + player[0].getFirstName() + " won the game."
                + "Keelhaul the rest ofthem!");
            
            return;
        }
        
        //Deciding winner according to Dig points
        for (int i = 0; i < player.length; i++) {
            for (int j = 1; j < (player.length - i); j++) {
                if (player[j - 1].getDigPoints() > player[j].getDigPoints()) {
                    temp = player[j - 1];
                    player[j - 1] = player[j];
                    player[j] = temp;
                }
            }
        }
        
        
        if(player[0].getPiratesPoinsts() != player[1].getPiratesPoinsts()){
            
            //This block will run if there is no draw according to dig points and select only one winner
            
            System.out.println("Shiver me Timbers, me hearties, sure hasn’t "
                + player[0].getFirstName() + " won the game."
                + "Keelhaul the rest ofthem!");
        }else{
            
            //Printing all the winners incase of draw
            
            //Creating a string with names of all the winners
            String winnersName = "";
            
            for(int i = 0; i < player.length; i++){
            
                if(player[0].getDigPoints() == player[i].getDigPoints()){
                    winnersName += player[i].getFirstName() + ", ";
                }
            
            }
            
            System.out.println("Shiver me Timbers, me hearties, sure hasn’t "
                + winnersName + " won the game."
                + "Keelhaul the rest ofthem!");
            
        }
        
        
        
  
    }
   
    
}