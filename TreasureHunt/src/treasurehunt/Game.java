/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treasurehunt;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Gul
 */
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
        
        do{
           
            if(playerTurn >= player.length){
                playerTurn = 0;
                showPointsTable();
                firstCycle = false;
            }
            
            if(firstCycle){
                initiatePoints();
            }
            
            //Core of Game
            
            ScreenOp.clearScreen();
            map.showTreasureMap();
            
            System.out.println("Argh.. Pirate" + player[playerTurn].getFirstName() + 
                    ".. it be your turn to dig for me treasure.");
            
            if(player[playerTurn].getDigPoints() > 0){
                
                if(map.getSquareFromUser()){
                    player[playerTurn].setPiratesPoinsts(20);
                    System.out.println("Yo-ho-ho and a bottle of rum. I found me some pieces of eight.");
                }else{
                    System.out.println("Walk the plank! There be no treasure here!");
                }
                
            }else{
            
                System.out.println("\nArgh, Captain, me shovel has broken!");
                
                if(askForShovel(player[playerTurn])){
                    
                    if(map.getSquareFromUser()){
                        player[playerTurn].setPiratesPoinsts(20);
                        System.out.println("Yo-ho-ho and a bottle of rum. I found me some pieces of eight.");
                    }else{
                        System.out.println("Walk the plank! There be no treasure here!");
                    }
                    
                }
            
            }
            
            player[playerTurn].setDigPoints(-1);
            ScreenOp.promptEnterKey();
            
            //Core of Game
            
            if(map.getNumberOfTreasures() <= 0){
                gameOver = true;
            }
            
            if(!hasAnyPlayerLeftWithDigPoints()){
                gameOver = true;
            }
            
            playerTurn++;
            
        }while(!gameOver);
        
    }
    
    private void initiatePoints(){
        
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
        
        for(int i = 0; i < player.length; i++){
            
            if(player[i].getDigPoints() > 0){
                return true;
            }
            
        }
        
        return false;
    
    }
    
    private boolean askForShovel(Player player){
        
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
            System.out.println("Shiver me Timbers, me hearties, sure hasn’t "
                + player[0].getFirstName() + " won the game."
                + "Keelhaul the rest ofthem!");
        }else{
            
            //Printing all the winners incase of draw
            
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