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
	        int run = 1;

	        for(int i = 8; i <= 11; i++){
				//Set up Buffer thread

				int n = (int)Math.pow(2, i);
				int size = (int)Math.pow(n, 2) * 2;

				Buffer b1 = new Buffer(size); // initilize with numbers
				
				Satellite sat;
				sat = new Satellite(b1); // passes buffer through 
				Thread satThread = new Thread(sat);
				satThread.start();
				
				for(int j = 1; j <= 5; j++){
					
					int t = (int)Math.pow(10, j);
					Buffer b2 = new Buffer(n*n);
					
					//set up receiver thread
					Receiver rcvr = new Receiver(b1,b2,n, t);
					Thread rcvrThread = new Thread(rcvr);
					
					rcvrThread.start();
					rcvrThread.join();
					
					
					System.out.println("Run #" + run + " i=" + i + ", j=" + j + ", N=" + n +
										", B1=" + size + ", B2=" + n*n + ", T=" + t);
					System.out.println("Time mergesort: " + rcvr.getMergeTime() + "ms");
					System.out.println("Saving image: " + rcvr.getPathName());
					
					run++;
				}
				sat.end();
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
