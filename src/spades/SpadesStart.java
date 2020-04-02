/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spades;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author carlosespejo
 */
public class SpadesStart{

    public static void main(String[] args) throws IOException {

        //StartGUI startGame = new StartGUI();
        //startGame.setVisible(true); 
        Deck deckOfCards = new Deck();
        deckOfCards.displayDeck();
        deckOfCards.shuffle();
        deckOfCards.displayDeck();
        System.out.println(deckOfCards.deal());
        
        //Card aceOfSpade = new Card("Ace","Spades ",14,ImageIO.read(new File ("/Users/keilynmarcuswilliamson/Spades-Game/src/cards/ace_of_spades.png")));
        //System.out.print(aceOfSpade);

    }
    //PlayGUI test = new PlayGUI();
    //test.setVisible(true);

}
//newGame.printWinners();

