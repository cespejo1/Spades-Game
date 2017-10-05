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


import java.util.ArrayList;
import java.util.Scanner;

public class Player1 extends Hand{
    private String name = "Player1";
    boolean isHuman = true;
  Scanner input = new Scanner(System.in);  
   
    
    public Player1(){
        //this.name = name;
    }
    //public String getName(){
        //return 
        
    //}
    public void setName(){
        System.out.println("            \nPlease enter your name");
        System.out.print("> ");
        Player1.super.setName(input.next());
         
    }
    
    public Card getHighestCard(ArrayList<Card> suitsArray){
       
        Card highestCard = new Card();
        
        int userChoice;
           
        for(int i = 0; i < suitsArray.size(); i++){
           System.out.print(i + 1 + ". "); 
           suitsArray.get(i).print();
           System.out.print("\n");
           
           
        }
        System.out.print(">");
        userChoice = input.nextInt();
        System.out.print("                >>>>" + super.getName() + " plays ");
        suitsArray.get(userChoice - 1).print();
        
        
       return suitsArray.get( userChoice - 1); 
    }
     public void userPlaysCard(java.awt.event.ActionEvent evt){
         
     }
   
}
