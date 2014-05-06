/**
 * 
 */
package project5;

/* File: Counter06.java

Synchronized version of Counter05. Now the threads are
well behaved. The display thread cannot print out the
array before it first thread is done populating the
entire array. Also, the first thread cannot start
populating the array with Fibonacci numbers until
the second thread is done showing the squares.
Everything proceeds nicely in lock step. 

*/

import javax.swing.*;
import java.awt.Font ;

public class Counter06 {

// data members are accessible to methods of inner classes.
int [] sharedArray ;


// These windows just print out the contents of sharedArray
// over and over again.
//
private class Window1 implements Runnable {

   int xpos = 0 ;
   int ypos = 0 ;
   boolean keepRunning ;     // keep going?

   Window1() { 
      // do nothing
   }

   Window1(int x, int y) { 
      xpos = x ;
      ypos = y ;
   }


   public void run() {

      try {
         
         JFrame frame = new JFrame( "Fast Display");
         JLabel label = new JLabel("Hello, Java!", JLabel.CENTER );
         label.setFont( new Font("Serif", Font.BOLD, 36) ) ;
         frame.getContentPane().add( label );
         frame.setSize( 300, 300 );
         frame.setLocation( xpos, ypos );
         frame.setVisible( true );

         keepRunning = true ;
         
         while (keepRunning) {   // keep going until someone says stop!
     
            // print contents of sharedArray[]
            // this time it's synchronized.
            
            synchronized( sharedArray ) {
               for(int i = 0 ; i < sharedArray.length ; i++) {
                  label.setText( Integer.toString(sharedArray[i]) ) ;
                  Thread.sleep(250) ;
               }
            }  // end of synchronized
            
            label.setText("Again! Again!") ;   // Teletubies say eh-oh
            Thread.sleep(1000) ;

         } // end of while(keepRunning)

  
      } catch ( InterruptedException e ) {
         System.out.println("Oh look! an exception!") ;
      }
  
   } // end of run()

} // end of private class Window1



// This window tries to populate sharedArray[] with some "interesting"
// sequences.
//
private class SequenceWindow implements Runnable {

   int xpos = 0 ;
   int ypos = 0 ;

   SequenceWindow() { 
      // do nothing
   }

   SequenceWindow(int x, int y) { 
      xpos = x ;
      ypos = y ;
   }


   public void run() {

      try {

         // similar set up as usual
         JFrame frame = new JFrame( "Sequence Window");
         JLabel label = new JLabel("Hello, Java!", JLabel.CENTER );
         label.setFont( new Font("Serif", Font.BOLD, 18) ) ;
         frame.getContentPane().add( label );
         frame.setSize( 600, 300 );   // a bit wider
         frame.setLocation( xpos, ypos );
         frame.setVisible( true );
         
         Thread.sleep(1000) ; // let other window go first
  

         // Write square values (0, 1, 4, 9, 16 ...) to sharedArray[]
         // This time it is synchronized.
         //
         synchronized( sharedArray ) {
            for(int i = 0 ; i < sharedArray.length ; i++) {
               sharedArray[i] = i*i ;   // i squared
               label.setText( "Setting sharedArray[" + i + "] to " + i*i) ;
               Thread.sleep(500) ;
            }
         }  // end of synchronized

         label.setText( "Done with Squares!" ) ;
         Thread.sleep(2000) ;   // wait a bit


         // Write Fibonacci numbers to sharedArray[]
         // This time it is synchronized.
         // 
         synchronized( sharedArray ) {
            sharedArray[0] = 1 ;  // base cases
            sharedArray[1] = 1 ;

            for(int i = 2 ; i < sharedArray.length ; i++) {
               sharedArray[i] = sharedArray[i-1] + sharedArray[i-2] ;
               label.setText( "Setting sharedArray[" + i + "] to " + sharedArray[i]) ;
               Thread.sleep(500) ;
            }

         } // end of synchronized( sharedArray ) 

         label.setText( "Done with Fibonacci numbers!" ) ;

         Thread.sleep(2000) ;   // wait a bit
  
      } catch ( InterruptedException e ) {
         System.out.println("Oh look! an exception!") ;
      }
  
   }

}


public void doThis() {

   // initialize sharedArray with "boring" numbers
   //
   sharedArray = new int[20] ;

   for (int i = 0 ; i < sharedArray.length ; i++) {
      sharedArray[i] = i ;
   }


   // start window that prints out sharedArray[]
   //
   Window1 w1 = new Window1() ;      
   Thread t1 = new Thread(w1) ;
   t1.start()  ;


   // start window that populates sharedArray[] with sequences
   //
   SequenceWindow w2 = new SequenceWindow(400, 0) ;
   Thread t2 = new Thread(w2) ;
   t2.start()  ;


   try {

      // wait for sequences to stop
      t2.join() ;   

      // tell w1 to stop 
      w1.keepRunning = false ; 

      // wait for w1 to stop
      t1.join() ;

   } catch ( InterruptedException e ) {
      System.out.println("Oh look! an exception!") ;
   }

   System.exit(0) ;
}



public static void main( String[] args ) {

   Counter06 obj = new Counter06() ;
   obj.doThis() ;
}

}

