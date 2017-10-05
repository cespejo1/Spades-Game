/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spades;

/**
 *
 * @author carlosespejo
 */
/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import java.util.ArrayList;
import java.util.*;
import javax.swing.*;

public class Hand {

    private ArrayList<Card> hand;   // The cards in the hand.
    public boolean playerTurn = false;
    public boolean playerIsNext = false;
    public boolean hasNotPlayed = true;
    private int points = 0;
    private String name = "";
    int idNumber;
    public boolean isHuman;
    Card currentCard = new Card();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getIDNumber(){
        return idNumber;
    }
    
    public void setIDNumber(int IDNum){
        idNumber = IDNum;
    }
    /**
     * Create a hand that is initially empty.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }

    /**
     * Remove all cards from the hand, leaving it empty.
     */
    public void clear() {
        hand.clear();
    }

    /**
     * Add a card to the hand.  It is added at the end of the current hand.
     * @param c the non-null card to be added.
     * @throws NullPointerException if the parameter c is null.
     */
    public void addCard(Card c) {
        if (c == null)
            throw new NullPointerException("Can't add a null card to a hand.");
        hand.add(c);
    }

    /**
     * Remove a card from the hand, if present.
     * @param c the card to be removed.  If c is null or if the card is not in 
     * the hand, then nothing is done.
     */
    public void removeCard(Card c) {
        hand.remove(c);
    }

    /**
     * Remove the card in a specified position from the hand.
     * @param position the position of the card that is to be removed, where
     * positions are starting from zero.
     * @throws IllegalArgumentException if the position does not exist in
     * the hand, that is if the position is less than 0 or greater than
     * or equal to the number of cards in the hand.
     */
    public void removeCard(int position) {
        if (position < 0 || position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: "
                    + position);
        hand.remove(position);
    }

    /**
     * Returns the number of cards in the hand.
     */
    public int getCardCount() {
        return hand.size();
    }

    public int getPoints(){
        return points;
    }
    
    public void setPoints(int newPoints){
        points = points + newPoints;
    }
    /**
     * Gets the card in a specified position in the hand.  (Note that this card
     * is not removed from the hand!)
     * @param position the position of the card that is to be returned
     * @throws IllegalArgumentException if position does not exist in the hand
     */
    public Card getCard(int position) {
        if (position < 0 || position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: "
                    + position);
        return hand.get(position);
    }

    /**
     * Sorts the cards in the hand so that cards of the same suit are
     * grouped together, and within a suit the cards are sorted by value.
     * Note that aces are considered to have the lowest value, 1.
     */
    public void sortBySuit() {
        ArrayList<Card> newHand = new ArrayList<Card>();
        while (hand.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = hand.get(0);  // Minimal card.
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if ( c1.getSuit() < c.getSuit() ||
                        (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }

    /**
     * Sorts the cards in the hand so that cards of the same value are
     * grouped together.  Cards with the same value are sorted by suit.
     * Note that aces are considered to have the lowest value, 1.
     */
    public void sortByValue() {
        ArrayList<Card> newHand = new ArrayList<Card>();
        while (hand.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = hand.get(0);  // Minimal card.
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if ( c1.getValue() < c.getValue() ||
                        (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }
    public void printHand() {
        for(int i = 0; i < hand.size(); i++){
        hand.get(i).print();
        }

    }
    public ArrayList<Card> getSuitCards(int suit){
         ArrayList<Card> suitArray = new ArrayList<Card>();
         boolean gotSuit = false;
         boolean gotSpades = false;
         Random rand = new Random();
      int ranCard = rand.nextInt(hand.size());
        for(int i = 0; i < hand.size(); i++){
            
            
            if (hand.get(i).getSuit() == suit){
                suitArray.add(hand.get(i));  
                gotSuit = true;
                }
            if (hand.get(i).getSuit() == 0){
                gotSpades = true;
            }
            
            
            }
        if (!gotSuit & gotSpades){
            for(int i = 0; i < hand.size(); i++) {
                if (hand.get(i).getSuit() == 0){
                suitArray.add(hand.get(i)); 
                }
              }
            }
        else if (!gotSuit & !gotSpades) {
            for(int i = 0; i < hand.size(); i++){
                suitArray.add(hand.get(i));
            }
        }
        return suitArray;
    }
    public Card getHighestCard(ArrayList<Card> suitsArray){
        int highest = 0;
        Card highestCard = new Card();
        
        for(int i = 0; i < suitsArray.size(); i++){
            if(suitsArray.get(i).getValue() > highest);
            highest = suitsArray.get(i).getValue();
            highestCard = suitsArray.get(i);           
        }
       
       highestCard.print();
       return highestCard; 
    }
    
    public ImageIcon getCardImageTranslation(Card a){
       
      ImageIcon toImageIcon = new ImageIcon(a.getValueAsString().toLowerCase()+ "_"+ "of" + "_"+ a.getSuitAsString().toLowerCase()+".png");
      return toImageIcon;
  }
    //Method to translate card string name to actual Card.
    //This is for after button has been selected in GUI
    public Card translate_Card_String_to_Card(ArrayList<Hand> cardHand, String card_Image_String){
        for(int x = 0; x < cardHand.size(); x++){
            //if (card_Image_String.contains(name)
        }
        return cardHand.get(0).getCard(0);
    }
 
}