/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spades;

/**
 * An object of type Card represents a playing card from a
 * standard Poker deck, including Jokers.  The card has a suit, which
 * can be spades, hearts, diamonds, clubs, or joker.  A spade, heart,
 * diamond, or club has one of the 13 values: ace, 2, 3, 4, 5, 6, 7,
 * 8, 9, 10, jack, queen, or king.  Note that "ace" is considered to be
 * the smallest value.  A joker can also have an associated value; 
 * this value can be anything and can be used to keep track of several
 * different jokers.
 */
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Card {
   private String faceName, suit;
   private int faceValue;
   private BufferedImage cardImage;
   
   public Card (String face, String suit, int value, BufferedImage image ){
    faceName = face;
    this.suit = suit;
    faceValue = value;
    cardImage = image;
   }
   
   
   public int getFaceValue(){
       
       return faceValue;
   }

    public String getFaceName() {
        return faceName;
    }

    public void setFaceName(String faceName) {
        this.faceName = faceName;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public BufferedImage getCardImage() {
        return cardImage;
    }

    public void setCardImage(BufferedImage cardImage) {
        this.cardImage = cardImage;
    }
    
    
   public String toString(){
       
       return faceName + " of " + suit;
   } 
   
  
  
  
   
   
   
} // end class Card