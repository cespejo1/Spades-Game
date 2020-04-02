/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spades;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import javax.imageio.ImageIO;

/**
 *
 * @author keilynmarcuswilliamson
 */
public class Deck {

    private Card[] deck;
    private int currentCard;

    public Deck() throws IOException {
        String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};

        deck = new Card[52];
        currentCard = 0;

        BufferedImage tempCardImage;

        File dir = new File("/Users/keilynmarcuswilliamson/Spades-Game/src/cards");
        File[] file = dir.listFiles();

        for (int suit = 0; suit < 4; suit++) {
            for (int faceNum = 0; faceNum < 13; faceNum++) {

                if (file != null) {
                    tempCardImage = ImageIO.read(file[faceNum + (suit * 13)]);

                    deck[faceNum + (suit * 13)] = new Card(faces[faceNum],
                            suits[suit], // number on the card
                            faceNum + 2, // value of the card
                            tempCardImage); // image of the card
                }

            }

        }

    }

    public void displayDeck() {
        for (Card card : deck) {
            System.out.println(card);
        }
    }

    public void shuffle() {
        currentCard = 0;

        SecureRandom secureRandom = new SecureRandom();

        for (int firstPlace = 0; firstPlace < 52; firstPlace++) {

            int randomPlace = secureRandom.nextInt(52);
            Card tempPlace = deck[firstPlace];
            deck[firstPlace] = deck[randomPlace];
            deck[randomPlace] = tempPlace;

        }
        
        
    }
    
    public Card deal(){
        
        return deck[deck.length-1];
        }

}
