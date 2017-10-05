/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author carlosespejo
 */
public class GameLogic {
    
    /**
     * Variables for the game
     */
    Player1 player1;
    
    //an array to hold all the players on a list
    public ArrayList<Hand> arrayThatHoldAllPlayers = new ArrayList<>();
    
    //Creates the deck
    private Deck gameCards = new Deck(false);
    
    //Variables to keep track of points. 
    //Team 1 is ther user and computer 2
    int team1Points;
    
    //team 2 is the computer 1 and computer 4
    int team2Points;
    
    //list of players in order of turn
    //public ArrayList<Hand> listOfPlayersInOrderOfTurn = new ArrayList<>();
    
    //random numbers
    int randomNumberForFirstGame;
    
    int randomNumberForFirstCard;
    
    boolean isFirstRoundOfGame = true;
    
    boolean spadesHaveBeenThrown = false;
    
    //first card of each round
    Card firstCardOfRound = new Card();
    
    //Random Number Generator
    Random randomNumberGenerator = new Random();
    
    //Queue that holds order of the round
    Queue<Hand> listOfPlayersInOrderOfTurn = new LinkedList<Hand>();
    
    /**
     * Begin methods and game logic
     */
    
    /**
     * Create all Players
     */
    public void createPlayers(){
        //create new 4 new players
        //The user (player1) is in position 0 of array "Players"
        player1 = new Player1();
        player1.setName("danny");
        player1.setIDNumber(0);
        player1.isHuman = true;
        
        System.out.println("danny is human? this is within player1 from game class just after creating: " + player1.isHuman);
    
        
        //add all the newly created players to an array
        arrayThatHoldAllPlayers.add(player1);
        
        System.out.println("danny is human? this is within array from game class just after adding him to array: " + arrayThatHoldAllPlayers.get(0).isHuman);
        
        // Loop to initialize the bots and add to "Players" array
        for(int i = 1; i <= 3; i++ ){
            Hand player = new Hand();
            player.setIDNumber(i);
            player.setName("Player" + Integer.toString(i + 1));
            player.isHuman = false;
            arrayThatHoldAllPlayers.add(player);
            
            }
         System.out.println("danny is human? this is within array from game class just after adding computers to array: " + arrayThatHoldAllPlayers.get(0).isHuman);
        
        arrayThatHoldAllPlayers.get(0).isHuman = true;
        
        System.out.println("danny is human? this is within array from game class just after setting danny human: " + arrayThatHoldAllPlayers.get(0).isHuman);
    }
    
    /**
     * shuffles and deals the cards
     */
    public void dealCards(){
        
        //shuffle the cards randomly
        gameCards.shuffle();
        
        //deal the cards to all players
        gameCards.deal(arrayThatHoldAllPlayers);
        
        //sort their cards by suit
        arrayThatHoldAllPlayers.get(0).sortBySuit();
    }
    
    /**
     * will choose order of first round by
     * getting a random number and inserting
     * everyone into the queue
     */
    public void chooseOrderOfFirstRound(){
        
        //get a random Number 
        randomNumberForFirstGame = randomNumberGenerator.nextInt(3);
        
        //add players to the queue based on their number 
        for(int x = 0; x < arrayThatHoldAllPlayers.size(); x++){
            
         
            //if id # is less than 4, add that person
            if(randomNumberForFirstGame < 4){
                
                listOfPlayersInOrderOfTurn.add(arrayThatHoldAllPlayers.get(randomNumberForFirstGame));
            }
            
            //if greater than 4 but less than 6, use modulus -1 to get correct person
            else if (randomNumberForFirstGame >= 4 && randomNumberForFirstGame < 6){
                
                int modulusResult = (randomNumberForFirstGame % 3) -1;
               
                listOfPlayersInOrderOfTurn.add(arrayThatHoldAllPlayers.get(modulusResult));
            
            }
            
            //if the number is 6, this means player 2 needs to be added. 
            //Modulus equation would give us wrong answer (0).
            else if (randomNumberForFirstGame == 6){
                
                listOfPlayersInOrderOfTurn.add(arrayThatHoldAllPlayers.get(2));
              
            }
            
            //increment the random number
            randomNumberForFirstGame++;
      
        } // end of for loop for adding to queue
        
        //set first player to have next turn true
        listOfPlayersInOrderOfTurn.peek().playerIsNext = true;
             
    }//end of class
    
    public void getFirstCardForFirstRound(){
        
    }
    
    /*
    this is where the players choose their cards
    */
    public void playCards(){
        
        
       
        // while loop to keep round going
        while(!listOfPlayersInOrderOfTurn.isEmpty()){
            
            //if its the first card of game and its a bot, choose random card
            //and have that player play
            if(listOfPlayersInOrderOfTurn.size() == 3){
                
               randomNumberForFirstCard = randomNumberGenerator.nextInt(3);
               firstCardOfRound = listOfPlayersInOrderOfTurn.remove().getCard(randomNumberForFirstCard);
                
            }
   
            //choose first one in the queue when its not first player
            if(!listOfPlayersInOrderOfTurn.peek().isHuman && listOfPlayersInOrderOfTurn.size() < 3){
                
            //listOfPlayersInOrderOfTurn.peek().g
                    
            }// enf of iff

        }// end of while
        
    }// end of class
           
}// end of program
