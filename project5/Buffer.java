package project5;
import java.util.LinkedList;

/**
 * This is a shared buffer class that holds Integers in a linked list.
 * 
 * @version 05/17/2014
 * @author Megan Straub <mstraub1@umbc.edu>
 * CMSC 341 - Spring 2014 - Project 5
 * Section 4
 */
public class Buffer{
	
	/**
	 * The thread safe array use to hold integers.
	 */
	private LinkedList<Integer> B;
	
	/**
	 * The maximum amount of integers allowed in the LinkedList.
	 */
	private int limit;
	
	/**
	 * A constructor class that instantiates the thread safe array, and the amount
	 * of integers allowed in the array.
	 * 
	 * @param limit the maximum amount of integers allowed in the thread safe array
	 */
	public Buffer(int limit){
		B = new LinkedList<Integer>();
		this.limit = limit;
	}
	
	/**
	 * Retrieves the size of the linked list.
	 * 
	 * @return the size of the linked list
	 */
	synchronized public int getSize(){
		return B.size();
	}
	
	/**
	 * Asks whether or not the linked list is full, based on the size of the limit.
	 * If the amount of elements in the linked list is greater than or equal to the limit,
	 * then the linked list is perceived as full.
	 * 
	 * @return true or false if the linked list is full
	 */
	synchronized public boolean isFull(){
		return B.size() >= limit;
	}
	
	/**
	 * Adds an integer to the linked list, if the limit has not been reached. 
	 * 
	 * @param value the integer to be added
	 */
	synchronized public void add(int value){
		if(B.size() >= limit){
			return ;
		}
		else{
			B.addLast(value);
		}
	}
	
	/**
	 * Removes an integer from the front of the linked list. 
	 * If the size is 0, an exception occurs because nothing can be removed.
	 * 
	 * @return the integer removed
	 */
	synchronized public int remove(){
		if(B.size() == 0){
			throw new RuntimeException("Nothing in the buffer!!");
		}
		
		return B.removeFirst();
	}
	
	/**
	 * Makes the linked list into an array.
	 * 
	 * @return the new array made from the linked list
	 */
	public int[] makeArray(){
		int[] buff = new int [B.size()];
				
		int index = 0;
		
		for(Integer value : B){ //for each Integer value in B add to this, increment index
			buff[index] = value;
			index++;
		}
		
		return buff;
	}
}