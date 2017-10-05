
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spades;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.util.Dictionary;
import java.util.Random;

/**
 *
 * @author carlosespejo
 */
public class PlayGUI extends javax.swing.JFrame {

    /**
     * Creates new form PlayGUI
     */
    //Variable for holding the Players for the game
    public ArrayList<Hand> Players;
    
    //Player1
    Player1 player1;
    
    //initialize the variables for the game
    //StartGUI welcomeScreenGUI = new StartGUI();     
    private boolean SpadesThrown = false;
    private boolean Round1 = true;
    private Deck game = new Deck(false);
    public int [] Score = new int [2];
    //Random player to start first round
    Random rand;
    int ranPlayer;
    
    //User card image
    javax.swing.ImageIcon userCardImage;
    
    //Tells the buttons then they can be selected
    boolean buttonSelectable = false;
    
    //This will hold the cards that are thrown within the rounds
    ArrayList<Card> roundCards;
    
    //Variables that will be used in "Round" class
    
    //This will hold the first card of every round
    Card firstCard;
    
    //This will hold the winner
    Hand winner = new Hand();
    
    //This will be a random suit chosen for the bot
    int randomSuit;
    
    //This will hold a random non spades card
    int randomNonSpades;
    
    //This will hold a non spades card
    Card nonSpadesHolder;
    
    // roundOrder
    ArrayList<Hand> orderOfRound;
   
    int randomCard;
    
    //Informs if user has played
    boolean playerHasPlayed;
    
    //Card chosen by user
    String chosenCardString;
    
    //holder for card image name for button selected
    String cardImageName;
    
    Card chosenCard;
    
    //Keep track of the turns taken, should end at 4
    int turnTracker;
    
    //User plays first boolean
    boolean userHasFirstTurn = false;
   
    
    public PlayGUI()  {
        initComponents();
        Players = new ArrayList<>();
        setUp();
       
        
    }
    
    public void setUp() {
        
        
        // First play of the game we choose a random player to start
        rand = new Random();
        ranPlayer = rand.nextInt(3);
        
        //making sure variable tracking user plays starts from beginning
        playerHasPlayed = false;
        
        //Initialize players and computers players
        
        //The user (player1) is in position 0 of array "Players"
        player1 = new Player1();
        player1.setName("danny");
        player1.setIDNumber(0);
        
        Players.add(player1);
        
        // Loop to initialize the bots and add to "Players" array
        for(int i = 1; i <= 3; i++ ){
            Hand Player = new Hand();
            Player.setIDNumber(i);
            Player.setName("Computer" + Integer.toString(i + 1));
            Players.add(Player);
            }
        //Make a random player (based on the number of "ranPlayer") be first/next to play
        //Players.get(ranPlayer).playerIsNext = true;
        
        //test make comp 2 first player
        Players.get(1).playerIsNext = true;
        
        //Now that we have the players, now we shuffle and  deal the cards    
        game.shuffle();
        game.deal(Players);
     
        //Sort the suit for player1
        Players.get(0).sortBySuit(); 
        
        //Here we need to update the 13 user button images with the card images
        for(int x = 1; x < 14; x++){
        userCardImage = new javax.swing.ImageIcon(getClass().getResource(Players.get(0).getCard(x - 1).getCardImageTranslation()));
        this.setUserCardButtonImages(userCardImage, x);
        }
        
        //Firt round of game begins
        //Round();
        
       //Round();
        
   /* 
    * END OF SetUp CLASS
    */      
    }
    
   /*
    This is the Round class
    Each round of the game will follow the same logic.
    */
    
    public void Round() {
        
        roundCards  = new ArrayList<>();
        Random rand = new Random();
        firstCard = new Card();
        winner = new Hand();
        randomNonSpades = rand.nextInt(3) + 1;
        nonSpadesHolder = new Card();
        randomCard = rand.nextInt(13);
        orderOfRound = new ArrayList<>();
        turnTracker = 0;
       
        //Establish the order of the round and puts Players arraylist in order
        roundHand();
        
        ///test
        System.out.println("step 1");
        System.out.println(Players.get(0).getName() + "is selected first from roundhand()");
        
        //This method returns the first card of round
        firstCard = getFirstCard();
        
        
        //test
        System.out.println("playing the first card, adding to roundcard arraylist");
        
        // Add the first card as firstCard of RoundCards arraylist
        roundCards.add(firstCard);
        
        
        
        //update image of first card on card button
        javax.swing.ImageIcon computerCardImage1 = new javax.swing.ImageIcon(getClass()
                       .getResource(roundCards.get(0).getCardImageTranslation()));
               this.setComputerCardImages(computerCardImage1, Players.get(0).getIDNumber());
               
        //turn tracker set to 1
        turnTracker = 1;
        
        //test
        System.out.println(roundCards.get(0).toStringImageName() + "is selected first from " +
                Players.get(0).getName());
        
        
        
        //begin while loop for round
        while (turnTracker < 4){
        
        
        // rest of players play their cards and are added to roundCard array 
        //list and updating card button images, playing their cards
        //test
        System.out.println("Step 2 adding all the cards to roundHand which means "
                + "playing their cards and showing to screen");
           for (int i = 1; i < Players.size(); i++){
               
               
               if (Players.get(i).getIDNumber() != 0 & Players.get(i).hasNotPlayed){
                   System.out.println("if computer hand is turn then they play :" + Players.get(i).getName());
        //bots will select the highest card within valid suit for round
               
               roundCards.add(Players.get(i).getHighestCard(Players.get(i).getSuitCards(firstCard.getSuit())));
               
               //Add the computers image to middle pile button that corresponds to them
               javax.swing.ImageIcon computerCardImage = new javax.swing.ImageIcon(getClass()
                       .getResource(roundCards.get(i).getCardImageTranslation()));
               this.setComputerCardImages(computerCardImage, Players.get(i).getIDNumber());
               
               //Player has now played so set hasNotPlayed to false so tat they wont play again;
               Players.get(i).hasNotPlayed = false;
               
               //Increment turn tracker
               turnTracker++;
               
               //test
               System.out.println("turntracker: " + turnTracker);
               }
               
               else {
                   
                   //while(!playerHasPlayed){
                   
                   //test
                   System.out.println("The player is now up and should choose a card. cpu shouldnt play until user selects");
                   //User picks card
                   playCard();
                   System.out.println("User has finished playing and we are leaving else clause");
                  // } end of while
                   
               }
               
           
           } // End of for loop for players playing their cards
           
        }//end of while loop for turn tracker
           
           
              
       
    }
    
    
    //This is an action listener for all buttons for user's turn
    private void playCard(){
        System.out.println("playCard 1st spot");
             
      //while(!playerHasPlayed){

  
      jButton1.addActionListener(new playCardListener());
      jButton2.addActionListener(new playCardListener());
      jButton3.addActionListener(new playCardListener());
      jButton4.addActionListener(new playCardListener());
      jButton5.addActionListener(new playCardListener());
      jButton6.addActionListener(new playCardListener());
      jButton7.addActionListener(new playCardListener());
      jButton8.addActionListener(new playCardListener());
      jButton9.addActionListener(new playCardListener());
      jButton10.addActionListener(new playCardListener());
      jButton11.addActionListener(new playCardListener());
      jButton12.addActionListener(new playCardListener());
      jButton13.addActionListener(new playCardListener());
      
      //}
      
      
      
      //If the user is first to play then we'll store his selected Card
      //to add to roundCards
      System.out.println("playCard 2nd spot after button is selected which then goes to playcardlistener");
      
      //}
       
   }
   
    //This is the event handler implemented after user picks card
   class playCardListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          System.out.println("playcardlistener 1st spot");
          System.out.println("action command is?" + e.getActionCommand());
       
            chosenCardString = e.getActionCommand();
            getCardImageName(chosenCardString);
            jTextField1.setText(cardImageName);
            
          //while(!playerHasPlayed){// while loop begins
              
              //System.out.println("in while loop");
               
           
            
                    switch ( e.getActionCommand() ) {
         case "jButton1" :   userCard.setIcon(jButton1.getIcon());
                            break;
         case "jButton2":   userCard.setIcon(jButton2.getIcon());
                            break;
         case "jButton3":   userCard.setIcon(jButton3.getIcon());
                            break;
         case "jButton4":   userCard.setIcon(jButton4.getIcon());
                            break;
         case "jButton5":   userCard.setIcon(jButton5.getIcon());
                            break;
         case "jButton6":   userCard.setIcon(jButton6.getIcon());
                            break;
         case "jButton7":   userCard.setIcon(jButton7.getIcon());
                            break;
         case "jButton8":   userCard.setIcon(jButton8.getIcon());
                            break;
         case "jButton9":   userCard.setIcon(jButton9.getIcon());
                            break;
         case "jButton10":  userCard.setIcon(jButton10.getIcon());
                            break;
         case "jButton11":  userCard.setIcon(jButton11.getIcon());
                            break;
         case "jButton12":  userCard.setIcon(jButton12.getIcon());
                            break;
         case "jButton13":  userCard.setIcon(jButton13.getIcon());
                            break;
        }
                    playerHasPlayed = true;
                    
                    if (userHasFirstTurn){
          firstCard = translate_Card_String_to_Card(player1, cardImageName);
      }
      else{
             //Here we add the chosen card to the roundcards array 
                roundCards.add(translate_Card_String_to_Card(player1, cardImageName));
      }
                    
         System.out.println("playcardlistener 3nrd spot");
         
         turnTracker++;
         
         
      //}//end of while loop                 
                
      } 
      
   }
   
   //A method to get the card image name of 
   //button in order to translate into card
   private void getCardImageName(String ButtonName){
                switch ( chosenCardString ) {
         case "jButton1" :  cardImageName = jButton1.getIcon().toString().substring(77);
                            break;
         case "jButton2":   cardImageName = jButton2.getIcon().toString().substring(77);
                            break;
         case "jButton3":   cardImageName = jButton3.getIcon().toString().substring(77);
                            break;
         case "jButton4":   cardImageName = jButton4.getIcon().toString().substring(77);
                            break;
         case "jButton5":   cardImageName = jButton5.getIcon().toString().substring(77);
                            break;
         case "jButton6":   cardImageName = jButton6.getIcon().toString().substring(77);
                            break;
         case "jButton7":   cardImageName = jButton7.getIcon().toString().substring(77);
                            break;
         case "jButton8":   cardImageName = jButton8.getIcon().toString().substring(77);
                            break;
         case "jButton9":   cardImageName = jButton9.getIcon().toString().substring(77);
                            break;
         case "jButton10":  cardImageName = jButton10.getIcon().toString().substring(77);
                            break;
         case "jButton11":  cardImageName = jButton11.getIcon().toString().substring(77);
                            break;
         case "jButton12":  cardImageName = jButton12.getIcon().toString().substring(77);
                            break;
         case "jButton13":  cardImageName = jButton13.getIcon().toString().substring(77);
                            break;
        }
   }
   
   /*
   After the user chooses the card button, we need to convert that to card object*/
   public Card translate_Card_String_to_Card(Hand player1, String card_Image_String){
        for(int x = 0; x < player1.getCardCount(); x++){
            

          
            if (card_Image_String.contains(player1.getCard(x).toStringImageName())){
                chosenCard = player1.getCard(x);
                System.out.println("Card has been chosen" + chosenCard.stringImageName);
                break;
            }
        }
        return chosenCard;
    }
    
    /* 
        At the beginning of every round, we must check if 
        spades has been thrown. That will dictake if
        spades can be thrown as a first card of the round
    */
    
    private Card getFirstCard(){
        
        /*
        If spades has been thrown, than the bot can select any random card 
       left in its hand
       */
       
        if(SpadesThrown){
            
                
            if(Players.get(0).getIDNumber()!= 0){   
            firstCard = Players.get(0).getCard(randomCard);                  
            }
            else {
                System.out.println("User is first player of round, spades has been thrown");
                playCard();
                System.out.println("user has played card after being first in round");
            }
                        
            
        }
        /*
        If spades has not been thrown, bot will select 
        random non spades card to start
        */
        
        else {
            
                
                    if(Players.get(0).getIDNumber()!= 0){
                        firstCard = Players.get(0).getHighestCard(Players.get(0).getSuitCards(randomNonSpades));
                       
                        }
                    else {
                        System.out.println("User is first player of round, spades has not been thrown");
                        playCard();
                        System.out.println("user has played card after being first in round");
                    }
                    
                
            }
        
       
        return firstCard;
        
        
    }
    
    /**
     * 
     * Establishes the order of the round. 
     * Puts Player array list in order
     */
    public void roundHand(){
        ArrayList<Hand> playerOrder= new ArrayList<>();
        boolean noneLeft = false;
         
           
           int b = 0;     
        
        
        while (!Players.isEmpty()){
            
            for (int i = 0; i < Players.size(); i++)
                
                //get the next player
                if(Players.get(i).playerIsNext){


                    playerOrder.add(Players.get(i));


                        if(i < Players.size() - 1 && i != 4){
                            Players.get(i + 1).playerIsNext = true;
                            Players.get(i).playerIsNext = false;
                            Players.remove(i);


                        }
                        else {
                                 Players.get(0).playerIsNext = true;
                                 Players.get(i).playerIsNext = false;
                                 Players.remove(i);
                         } 
                    }
            
            b++;
            }
                   
                    Players =  playerOrder;
                    
        
        }
    

    
    
    public void setButtonSelectable(boolean x){
        buttonSelectable = x;
    }
    
    public void setUserCardButtonImages(javax.swing.ImageIcon x, int y){
        
        switch ( y ) {
         case 1:   jButton1.setIcon(x);
         case 2:   jButton2.setIcon(x);
         case 3:   jButton3.setIcon(x);
         case 4:   jButton4.setIcon(x);
         case 5:   jButton5.setIcon(x);
         case 6:   jButton6.setIcon(x);
         case 7:   jButton7.setIcon(x);
         case 8:   jButton8.setIcon(x);
         case 9:   jButton9.setIcon(x);
         case 10:  jButton10.setIcon(x);
         case 11:  jButton11.setIcon(x);
         case 12:  jButton12.setIcon(x);
         case 13:  jButton13.setIcon(x);
        }
        
        
        
        
    }
    
    /**
     * 
     * Method for matching the card button to the bot  with its card selection
     */
    public void setComputerCardImages(javax.swing.ImageIcon x, int y){
            switch ( y ) {
         case 2:   computer2Card.setIcon(x);
         case 3:   computer3Card.setIcon(x);
         case 4:   computer4Card.setIcon(x);
        }
            
            
            
        }
    
    //public String getcardSelected(){
        
        //return cardSelected;
        
    //}
    
    public void setPlayerHasPlayed(Boolean x){
        playerHasPlayed = x;
    }
    
    public Boolean getPlayerHasPlayed(){
        return playerHasPlayed;
    }
    
    public void testPlayCard(){
    
}
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        computer2Card = new javax.swing.JLabel();
        computer3Card = new javax.swing.JLabel();
        userCard = new javax.swing.JLabel();
        computer4Card = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 51));
        setPreferredSize(new java.awt.Dimension(1300, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(1300, 300));

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jPanel3.setPreferredSize(new java.awt.Dimension(1300, 700));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1971, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 859, Short.MAX_VALUE)
        );

        computer2Card.setText("computer2");
        computer2Card.setPreferredSize(new java.awt.Dimension(100, 200));

        computer3Card.setText("computer3");

        userCard.setText("user");
        userCard.setPreferredSize(new java.awt.Dimension(100, 200));

        computer4Card.setText("computer4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(323, 323, 323)
                .addComponent(computer2Card, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(computer3Card, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(computer4Card, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1971, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(computer2Card, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(computer3Card)
                                    .addComponent(userCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(computer4Card, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {computer2Card, computer3Card, userCard});

        jPanel2.setPreferredSize(new java.awt.Dimension(1300, 400));

        jButton11.setText("jButton11");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton6.setText("jButton6");
        jButton6.setPreferredSize(new java.awt.Dimension(100, 200));

        jButton9.setText("jButton9");

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton12.setText("jButton12");

        jButton2.setText("jButton2");
        jButton2.setMaximumSize(new java.awt.Dimension(100, 200));
        jButton2.setPreferredSize(new java.awt.Dimension(100, 200));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.setPreferredSize(new java.awt.Dimension(100, 200));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton7.setText("jButton7");

        jButton10.setText("jButton10");
        jButton10.setPreferredSize(new java.awt.Dimension(100, 200));

        jButton5.setText("jButton5");

        jButton3.setText("jButton3");
        jButton3.setPreferredSize(new java.awt.Dimension(100, 200));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setText("jButton8");

        jButton13.setText("jButton13");

        jButton14.setText("test");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(888, 888, 888))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton10, jButton11, jButton12, jButton13, jButton2, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButton14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(222, 222, 222))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton10, jButton11, jButton12, jButton13, jButton2, jButton4, jButton5, jButton6, jButton7, jButton8, jButton9});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 3608, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 2927, Short.MAX_VALUE)
                        .addGap(370, 370, 370)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 186, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(buttonSelectable){
            userCard.setIcon(jButton1.getIcon());
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //getUserCard(jButton4.getIcon().toString());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        //getUserCard(jButton11.getIcon().toString());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
Round();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    
    //Display the cards being played
    public void displayComputer2Card(Card comp2Card){
        computer2Card.setText(comp2Card.toString());
        
    }
    public void displayuserCard(Card usCard){
        userCard.setText(usCard.toString());
    }
    public void displayComputer3Card(Card comp3Card){
        computer3Card.setText(comp3Card.toString());
    }
    public void displayComputer4Card(Card comp4Card){
        computer4Card.setText(comp4Card.toString());
    }
    

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel computer2Card;
    private javax.swing.JLabel computer3Card;
    private javax.swing.JLabel computer4Card;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton10;
    public javax.swing.JButton jButton11;
    public javax.swing.JButton jButton12;
    public javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    public javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton5;
    public javax.swing.JButton jButton6;
    public javax.swing.JButton jButton7;
    public javax.swing.JButton jButton8;
    public javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JLabel userCard;
    // End of variables declaration//GEN-END:variables
}
