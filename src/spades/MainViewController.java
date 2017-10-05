/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spades;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author carlosespejo
 */
public class MainViewController implements Initializable {
    
    /**
     * Variables for the game
     */
    Player1 player1;
    
    //an array to hold all the players on a list
    public ArrayList<Hand> arrayThatHoldAllPlayers = new ArrayList<>();
    
    //Creates the deck
    private Deck gameCards = new Deck(false);
    
    //New Game
    GameLogic newGame = new GameLogic();
            
    //card Image
    Image userCardImage;
    
    Image computerCardImage;
    
    //thread
    GameThread testThread;
    
    ExecutorService service = null;
    
    /**
     * Game elements/nodes for GUI
     */
    @FXML
    private ImageView playerCard1;
    
    @FXML
    private ImageView playerCard2;
    
    @FXML
    private ImageView playerCard3;
    
    @FXML
    private ImageView playerCard4;
    
    @FXML
    private ImageView playerCard5;
    
    @FXML
    private ImageView playerCard6;
    
    @FXML
    private ImageView playerCard7;
    
    @FXML
    private ImageView playerCard8;
    
    @FXML
    private ImageView playerCard9;
    
    @FXML
    private ImageView playerCard10;
    
    @FXML
    private ImageView playerCard11;
    
    @FXML
    private ImageView playerCard12;
    
    @FXML
    private ImageView playerCard13;
    
    @FXML
    private ImageView computer1Card;
    
    @FXML
    private ImageView computer2Card;
    
    @FXML
    private ImageView computer3Card;
    
    @FXML
    private ImageView cardPlayedIcon;
    
    /**
     * Begin methods and game logic
     */
    
  

    
    /**
     * set the icons up for the cards on GUI
     */
    private void setCardImagesForGUI(){
        
        
        for(int x = 1; x < 14; x++){
            
            //this method translate each users card into the file name 
            //where the image is located
            userCardImage = new Image(newGame.arrayThatHoldAllPlayers.get(0).getCard(x - 1).getCardImageTranslation());
        
            //begin switch
            switch ( x ) {
             case 1:   playerCard1.setImage(userCardImage);
             case 2:   playerCard2.setImage(userCardImage);
             case 3:   playerCard3.setImage(userCardImage);
             case 4:   playerCard4.setImage(userCardImage);
             case 5:   playerCard5.setImage(userCardImage);
             case 6:   playerCard6.setImage(userCardImage);
             case 7:   playerCard7.setImage(userCardImage);
             case 8:   playerCard8.setImage(userCardImage);
             case 9:   playerCard9.setImage(userCardImage);
             case 10:  playerCard10.setImage(userCardImage);
             case 11:  playerCard11.setImage(userCardImage);
             case 12:  playerCard12.setImage(userCardImage);
             case 13:  playerCard13.setImage(userCardImage);
            } //end of switch
        
        }// end of for loop
    }
    
    
    /**
     * test
     */
    @FXML
    private void testButton(){
   
        //create players
        newGame.createPlayers();
        
        //deal players
        newGame.dealCards();
       
        //Set up all the cards
        setCardImagesForGUI();
        
        //see if queue works
        newGame.chooseOrderOfFirstRound();
        
        //thread test
        //testThread = new GameThread();
        //testThread.start();
        
        threadTest();
        
        
        //see if playing works and cpu waits for us
        //testPlayCards();
                    
    }
    
    private void playCards(){

        // while loop to keep round going
        while(!newGame.listOfPlayersInOrderOfTurn.isEmpty()){
            
            //if its the first card of game and is a bot, choose random card
            //and have that bot play
            if(!newGame.listOfPlayersInOrderOfTurn.peek().isHuman && newGame.listOfPlayersInOrderOfTurn.size() == 3 && newGame.isFirstRoundOfGame){
                
               // gets a random # for first card of CPU
               newGame.randomNumberForFirstCard = newGame.randomNumberGenerator.nextInt(3);
               
               //gets highest card and also removes him from Queue
               newGame.firstCardOfRound = newGame.listOfPlayersInOrderOfTurn.remove().getCard(newGame.randomNumberForFirstCard);
                
            }
   
            //choose first one in the queue when its not first player
            if(!newGame.listOfPlayersInOrderOfTurn.peek().isHuman && newGame.listOfPlayersInOrderOfTurn.size() < 3){
                
            //listOfPlayersInOrderOfTurn.peek().g
                    
            }// enf of iff

        }// end of while
          
    }// end of class
    
    private void setComputerCardImage(int computerNumber){
        
        //this method translate each users card into the file name 
            //where the image is located
            computerCardImage = new Image(newGame.arrayThatHoldAllPlayers.get(computerNumber).getCard(1).getCardImageTranslation());
        
            //begin switch
            switch ( computerNumber ) {
             case 1:   computer1Card.setImage(computerCardImage);
             case 2:   computer2Card.setImage(computerCardImage);
             case 3:   computer3Card.setImage(computerCardImage);
            }
            
        }// end of class
    
    private void testPlayCards(){
        
        System.out.print("First Player to play is: " + newGame.listOfPlayersInOrderOfTurn.peek().getName());
        System.out.println("amount of people in queue: " + newGame.listOfPlayersInOrderOfTurn.size());
        System.out.println("danny is human? : " + newGame.player1.isHuman);
        System.out.println("danny is human? this is within queue: " + newGame.listOfPlayersInOrderOfTurn.peek().isHuman);
        System.out.println("danny is human? this is within array: " + newGame.arrayThatHoldAllPlayers.get(0).isHuman);
        System.out.println("danny is human? this is within array whats his name: " + newGame.arrayThatHoldAllPlayers.get(0).getName());
        
        
        while(!newGame.listOfPlayersInOrderOfTurn.isEmpty()){
            
            
            //just have them play a card
            if(!newGame.listOfPlayersInOrderOfTurn.peek().isHuman && newGame.listOfPlayersInOrderOfTurn.peek().playerIsNext){
     
                newGame.listOfPlayersInOrderOfTurn.peek().getCard(1);
                setComputerCardImage(newGame.listOfPlayersInOrderOfTurn.peek().getIDNumber());
                System.out.println("name of player who played is: " + newGame.listOfPlayersInOrderOfTurn.peek().getName());
                System.out.println("is player human: " + newGame.listOfPlayersInOrderOfTurn.peek().isHuman);
                newGame.listOfPlayersInOrderOfTurn.remove();
                System.out.println("amount of people in queue: " + newGame.listOfPlayersInOrderOfTurn.size());
                if(!newGame.listOfPlayersInOrderOfTurn.isEmpty()){
                newGame.listOfPlayersInOrderOfTurn.peek().playerIsNext = true;
                }
                
             
            
            }//end of if
            
            
        
        }
        
        System.out.println("end of while and round");
        
    
        
        
    }// end of class
    
    //sets the middle card image to the image of card user clicked on
    @FXML
    private void setUserCardImageOnClick(){
      
        cardPlayedIcon.setImage(playerCard1.getImage());
        newGame.listOfPlayersInOrderOfTurn.remove();
        System.out.println("User Played");
        if(!newGame.listOfPlayersInOrderOfTurn.isEmpty()){
        newGame.listOfPlayersInOrderOfTurn.peek().playerIsNext = true;
        System.out.println("amount of people in queue: " + newGame.listOfPlayersInOrderOfTurn.size());
        //testThread.interrupt();
        
        }
        
        
    }
    
    private void threadTest(){
        
      
//         service = Executors.newSingleThreadExecutor();
//
//         System.out.println("begin");
//         service.execute(testThread);
         //service.execute(() ➜ System.out.println("Printing zoo inventory") );
         testThread testThread = new testThread();
         Thread t0 = new Thread(testThread);
         testThread.computerPlayed = true;
      
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
