package project5;

/**
 * This class receives data from the Satellite class through the shared
 * Buffer, b1. Receiver must wait until there is a certain amount of
 * elements in the shared buffer before removing them and placing the
 * elements into another shared buffer. Once the data is placed into
 * the second shared buffer, Receiver calls an object from the Processor
 * class to manipulate the data and create the image.
 * 
 * @version 05/17/2014
 * @author Megan Straub <mstraub1@umbc.edu>
 * CMSC 341 - Spring 2014 - Project 5
 * Section 4
 */
public class Receiver implements Runnable {
	
	/**
	 * The shared buffer with random integers.
	 */
	private Buffer b1;
	
	/**
	 * A shared buffer that retrieves integers from b1.
	 */
	private Buffer b2;
	
	/**
	 * The size of the image, defined by Main.
	 */
	private int n;
	
	/**
	 * The threshold for sorting the data, defined by Main.
	 */
	private int t;
	
	/**
	 * An object of the class, Processor, used to process the shared buffer b2.
	 */
	private Processor process;
	
	/**
	 * A constructor that initializes: b1, b2, n, t, and the object process.
	 * 
	 * @param b1 the shared buffer of random integers
	 * @param b2 the shared buffer to be manipulated
	 * @param n the size of the image, defined by Main.
	 * @param t the threshold for sorting the data, defined by Main.
	 */
	public Receiver(Buffer b1, Buffer b2, int n, int t){
		this.b1 = b1;
		this.b2 = b2;
		this.n = n;
		this.t = t;
		process = new Processor(b2, n, t);
	}
	
	/**
	 * This will obtain data from the shared buffer b1, once the size is big enough,
	 * and move the data from b1 into b2, while b2 isn't full. Once all the data has been
	 * moved the Processor object, process, will process the data for normalization.
	 */
	public void run() {
		try{

			while(b1.getSize() < (n * n)){
				Thread.sleep(1000);
			}
			
			do{
				b2.add(b1.remove()); // adds data to b2, and removes from b1				
			}while(!b2.isFull());
			
			process.processData();
			
		} catch (Exception e){
			System.out.println("Oh no! An exepction in Receiver!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the path name of the jpg image.
	 * 
	 * @return the path name
	 */
	public String getPathName(){
		return process.getPathName();
	}
	
	/**
	 * Retrieves the amount of time it takes to merge sort the array.
	 * 
	 * @return the merge time
	 */
	public long getMergeTime(){
		return process.getMergeTime();
	}
}
