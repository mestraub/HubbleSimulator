package project5;

import java.util.LinkedList;

/**
 * @author Megan
 *
 */
public class Buffer{ //implements runnable eeeeh?
	
	private LinkedList<Integer> B;
	
	private int limit;

	public Buffer(int limit){
		B = new LinkedList<Integer>();
		this.limit = limit;
	}
	
	synchronized public int getSize(){
		return B.size();
	}
	
	synchronized public boolean isFull(){
		return B.size() >= limit;
	}
	
	synchronized public void add(int value){
		if(B.size() >= limit){
			return ;
		}
		else{
			B.addLast(value);
		}
	}
	
	synchronized public int remove(){
		if(B.size() == 0){
			throw new RuntimeException("Nothing in the buffer!!");
		}
		
		return B.removeFirst();
	}
	
	public int[] makeArray(){
		int[] buff = new int [B.size()];
				
		int index = 0;
		
		for(Integer value : B){ //for each Integer value in B add to this, increment index
			buff[index] = value;
			index++;
		}
		
		return buff;
	}
	
	synchronized public void clear(){
		for (int i = 0; i <= B.size(); i++){
			B.removeFirst();
		}
	}
	
	synchronized public void printBuffer(){
		for (int i = 0; i < B.size(); i++){
			System.out.println(B.toString());
		}
	}
}