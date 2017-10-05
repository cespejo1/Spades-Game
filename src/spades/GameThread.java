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
public class GameThread extends Thread{
    
    
    
    @Override
    public void run() {
        
        boolean userPlayed = false;
        
        GameLogic game;
        
        while(!userPlayed){
            
        
        // Loop for ten iterations.
        
        
            System.out.println(" looping ...");
        
            
            // Sleep for a while
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Interrupted exception will occur if
                // the Worker object's interrupt() method
                // is called. interrupt() is inherited
                // from the Thread class.
                System.out.println("you interuppted me!");
                System.out.println("has user played? this is before setting it to true" + userPlayed);
                userPlayed = true;
                System.out.println("has user played? this is after setting it to true" + userPlayed);
                break;
            }
        
        }//end of while
    }
    
    
}
