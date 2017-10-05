/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spades;

import javax.swing.Icon;
import javax.swing.JButton;



/**
 *
 * @author carlosespejo
 */


@SuppressWarnings("serial")
public class CardButton extends JButton {

	/*
	 * INSTANCE VARIABLES
	 */

	private Card card; // the card object of the button.

	/*
	 * CONSTRUCTORS
	 */

	/**
	 * CardButton constructor calls the JButton super class to create a new
	 * button with no card assigned
	 */
	public CardButton() {
		super();
	}

	/**
	 * CardButton constructor calls the JButton super class to create a new
	 * button, and assigns a card object to the button.
	 * 
	 * @param c
	 *            The card object to be assigned to the button.
	 */
	public CardButton(Card c) {
		super();
		card = c;
	}

	/**
	 * CardButton constructor call the JButton super class to create a new
	 * button, and assigns an icon and card to the button.
	 * 
	 * @param i
	 *            The icon of the button.
	 * @param c
	 *            The card of the button.
	 */
	public CardButton(Icon i, Card c) {
		super(i);
		card = c;
	}

	/*
	 * PUBLIC METHODS
	 */

	/**
	 * Returns the card of this button.
	 * 
	 * @return the card of this button.
	 */
	public Card getCard() {
		return card;
	}
}