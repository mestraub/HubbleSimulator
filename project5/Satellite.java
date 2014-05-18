package project5;
import java.util.Random;

/**
 * This generates and adds elements into a shared, thread-safe buffer.
 * 
 * @version 05/17/2014
 * @author Megan Straub <mstraub1@umbc.edu>
 * CMSC 341 - Spring 2014 - Project 5
 * Section 4
 */
public class Satellite implements Runnable {
	
	/**
	 * The thread safe array use to hold random integers.
	 */
	private Buffer b1;
	
	/**
	 * Tracks when the satellite thread is running, and is used to stop the thread.
	 */
	private boolean running;
	
	/**
	 * The random number generator for the integers being added to the thread safe array.
	 */
	private Random randNum;
	
	/**
	 * A constructor that accepts the buffer that will hold random numbers.
	 * This instantiates the running boolean to true, and creates the random
	 * number generator.
	 * 
	 * @param b1 the buffer being used to hold integers
	 */
	public Satellite(Buffer b1){
		this.b1 = b1;
		this.running = true;
		randNum = new Random();
	}
	
	/**
	 * This creates random integers between 0 and 4096 and adds them
	 * to the buffer. This operation occurs only when running is true, and 
	 * the buffer still has space. If the buffer is full it will wait until 
	 * space is cleared.
	 */
	public void run(){
		try{		
			running = true;

			while(running){
				
				if(b1.isFull()){
					Thread.sleep(1000);
				}else{
					b1.add(randNum.nextInt(4097)); // counts for 4096
				}
			}
		}catch (InterruptedException e){
			System.out.println("Oh no! An exepction in Satellite!!");
			e.printStackTrace();	
		}	
	}
	
	/**
	 * This is used to stop the satellite thread.
	 */
	public void end(){
		running = false;
	}
}
