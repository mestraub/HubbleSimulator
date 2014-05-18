package project5;

/**
 * This runs the Hubble simulator project, and utilizes threads to run the simulation. 
 * The purpose of the project is to: 1) collect data, 2) transfer the data in batches, 
 * 3) sort and process the data stored in the shared buffers, and 4) display the data in image form. 
 * 
 * @version 05/17/2014
 * @author Megan Straub <mstraub1@umbc.edu>
 * CMSC 341 - Spring 2014 - Project 5
 * Section 4
 */
public class Main {

	public static void main(String[] args) {

        System.out.println("Available processors (cores ): " + Runtime.getRuntime().availableProcessors());
        System.out.println("Available memory (bytes): " + Runtime.getRuntime().freeMemory() + "\n");

		try{	
	        int run = 1;

	        for(int i = 8; i <= 11; i++){

				int n = (int)Math.pow(2, i);
				int size = (int)Math.pow(n, 2) * 2;

				Buffer b1 = new Buffer(size); //creates the shared buffer
				
				Satellite sat;
				sat = new Satellite(b1); 
				
				//set up satellite thread
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
					System.out.println("Saving image: " + rcvr.getPathName() + "\n");
					
					run++;
				}
				sat.end(); //tells satellite to stop generating data
	        }
		}catch(InterruptedException e){
			System.out.println("Oh no! An exception!");
		}
	}
}