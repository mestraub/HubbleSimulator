/**
 * 
 */
package project5;

import java.util.Random;

/**
 * @author Megan
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//program is executed 20 times

        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Available Memory: " + Runtime.getRuntime().freeMemory() + "\n");

		try{
			
	        int run = 0;

			
	        for(int i = 8; i <= 11; i++){
				//Set up Buffer thread

				int n = (int)Math.pow(2, i);
				int size = (int)Math.pow(n, 2) * 2;

				Buffer b1 = new Buffer(270); // initilize with numbers
				System.out.println("\nLimit of buffer: " + b1.limit);
				
				Thread bufThread = new Thread(b1);
				bufThread.start();
				
				//Set up satelitte thread
				Satellite sat;
				sat = new Satellite(b1); // passes buffer through 
				
				Thread satThread = new Thread(sat);
				satThread.start();
				
				for(int j = 1; j <= 5; j++){
					
					int t = (int)Math.pow(10, j);
					Buffer b2 = new Buffer(n*n);
					//set up receiver thread
					Receiver rcvr = new Receiver(b1,b2,n);
					Thread rcvrThread = new Thread(rcvr);
					
					rcvrThread.start();
					rcvrThread.join();
					//pass buffer reference liek with sat.
					// do same as above for consumer.

					satThread.join();
				//	sat.running = false;
					b1.running = false;
					sat.running = false;
					rcvr.running = false;
					
					run++;
					System.out.println("Run #" + run + " i=" + i + ", j=" + j + ", N=" + n +
										", B1=" + size + ", B2=" + n*n + ", T=" + t);
				//	System.out.println("Time mergesort: ");
				//	System.out.println("Saving image: \n");
				}
				
				 //tell the sat thread to stop collectin data
				satThread.join();
	        }
		}catch(InterruptedException e){
			System.out.println("Oh no! An exception!");
		}
	}
}


//	Random rand = new Random();

//	n = rand.nextInt(4) + 8;
//	Buffer b = new Buffer();
//	b.setSize(n);
	
//	dc.doThis();
	//int n;
//	int n;
