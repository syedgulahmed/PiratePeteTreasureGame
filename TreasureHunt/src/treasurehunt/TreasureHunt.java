package treasurehunt;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreasureHunt {
    
    static Scanner scan;
    static Player player[];
    static TreasureMap map;
    static Game game;

    public static void main(String[] args)throws FileNotFoundException{
        
        map = new TreasureMap();
        scan = new Scanner(System.in);
        int numberOfPlayers;
        
        //Getting number of players
        numberOfPlayers = getNumberOfPlayers();
        
        
        //Getting Name and Age of players
        player = new Player[numberOfPlayers];
        getPlayersInfo(player);
        
        //Initiate game object
        game = new Game(player, map);
        
        //Outlining game rules to the players
        displayGameRules();
        
        //Statring game 
        game.gameInProgress();
        
        
        //DecidingWinner
        game.decideWinner();
        
             
    }
    
    private static int getNumberOfPlayers(){
        
        //Method created get numbers of players that will play the game
        int numberOfPlayers;
        
        //Using do while loop to conform the validity that numbers of players must be between 2 and 4 inclusive.
        do{
            System.out.print("Enter number of players in the game: ");
            numberOfPlayers = Integer.parseInt(scan.next());
            
            if(numberOfPlayers < 2 || numberOfPlayers > 4){
                System.out.println("Number of players must be 2, 3 or 4");
            }
            
        }while(numberOfPlayers < 2 || numberOfPlayers > 4);
        
        return numberOfPlayers;
    }
    
    private static void getPlayersInfo(Player[] player){
        
        //Method for getting information of players
        
        int numberOfPlayers = player.length;
        
        
        for(int i = 0; i < numberOfPlayers; i++){
            
            //Getting first name and surname of player
            player[i] = new Player();
            System.out.println("Player #" + (i+1));
            System.out.print("Enter first name and surname with spcae between two: ");
            player[i].setFirstName(scan.next());
            player[i].setSurname(scan.next());
            
            //Getting age of player
            System.out.print("Enter age: ");
            player[i].setAge(Integer.parseInt(scan.next()));
            
            //Making player's age is not less than 12 years
            if(player[i].getAge() < 12){
                System.out.println("Sorry, you must be 12 or over to play this game.");
                System.exit(0);
            }
            
        }
    
    }
    
    private static void displayGameRules(){
        
        //Method created to dislay rules of the game.
        
        ScreenOp.clearScreen();
        
        //Stroing all the rules in a String array
        String rules[] = 
        {
            "GAME RULES:",
            "----------------------------------------------------------------------------------------------",
            "Each player may only select a square that exists on the 'Map'", 
            "A square can only be selected once (i.e. you cannot ‘dig’ in the same place more than once)",
            "If the player finds some treasure, then 20 'Pirate Points' will be added to their total\n"
                + "and square will be marked on the map as having been selected (or 'used').",
            "If the player does not find any treasure, then they do not score any 'Pirate Points'.\n"
                + "The square will be marked on the map as having been selected (or 'used').",
            "In all cases, 1 point will be subtracted from 'dig points'",
            "After this, next player's turn happen.",
            "If a player has no 'Dig Points' left, then they will miss their turn.",
            "If all the treasure is found then the game is over.",
            "If no player has any 'Dig Points' left, then the game is over.",
            "----------------------------------------------------------------------------------------------",
            "Note: All players have been assigned 100 Pirate Points."
        };
    
        //Printing the all stored rules in string array using for loop
        for(int i = 0; i < rules.length; i++){
            
            if(i > 1 && i < 11){
                System.out.println((i - 1) + ")" + rules[i] + "\n");
            }else{
                System.out.println(rules[i]);
            }
        
        }
        
        ScreenOp.promptEnterKey();
    
    }
    
}