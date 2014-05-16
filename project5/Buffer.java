/**
 * 
 */
package project5;
// http://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ConcurrentLinkedQueue.html
// http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/package-summary.html
// check conccurent linked list, similar to zip file

import java.util.LinkedList;
//import java.nio.Buffer;
//import java.util.Collections;

/**
 * @author Megan
 *
 */
public class Buffer implements Runnable{

	private LinkedList<Integer> B;
	int limit;
	boolean running;
	
	public Buffer(){
		B = new LinkedList<Integer>();
	}
	
	public Buffer(int l){
		B = new LinkedList<Integer>();
		limit = l;
	}
	
	public void run() {
		try{
			//Collections.synchronizedList(B);
			running = true;
			

			Thread.sleep(1);

		}catch (InterruptedException e){
			System.out.println("Oh no! An exepction!");
			e.printStackTrace();
		}
	}	
	
	synchronized public int getSize(){
		return B.size();
	}
	
	synchronized public boolean isFull(){
		return B.size() >= limit;
	}
	
	synchronized public void add(int value){
		if(B.size() >= limit)
			return ;
		B.addLast(value);
	}
	
	synchronized public Integer remove(){
		if(B.isEmpty())
			return null;
		
		return B.removeFirst();
	}
	
	synchronized public void clear(){
		for (int i = 0; i <= B.size(); i++){
			B.removeFirst();
		}
	}
	
	synchronized public void waitForSpace(){
		try{
			while(B.size() >= limit) 
				wait();
		}catch (InterruptedException e){
			System.out.println("Oh no! An interrupt!");
		}
	}
	
	synchronized public void waitForData(){
		try{
			while(B.isEmpty())
				wait();
		}catch (InterruptedException e){
			System.out.println("Oh no! An interrupt!");
		}
	}
	
	synchronized public void printBuffer(){
		for (int i = 0; i < B.size(); i++){
			System.out.println(B.toString());
		}
	}

	
}
