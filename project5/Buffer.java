/**
 * 
 */
package project5;

//import java.nio.Buffer;

/**
 * @author Megan
 *
 */
public class Buffer implements Runnable{
	public int[] B;
//	Buffer buff;
	int defaultSize = 5;
	boolean lock;
	
	public Buffer(){
		B = new int[defaultSize];
	//	buff = new Buffer();
	}
	
	public int getSize(){
		return B.length;
	}
	
	public void add(int index, int value){
		B[index] = value;
	}
	
	public void printBuffer(){
		for (int i = 0; i < B.length; i++){
			System.out.println(B[i]);
		}
	}
	public void run() {
		// TODO Auto-generated method stub
	//	buff = new Buffer();
		/*
		try{
			for (int i = 0; i < B.length; i++){
			//	B[i] = 
			}
		}catch (InterruptedException e){
			System.out.println("Oh no! An exepction!");
			e.printStackTrace();
		}
		*/
	}
	
	public void doThis(){
		// intitilize the array with "boring" numbers
		
		B = new int[defaultSize];
		
		for(int i = 0; i < B.length; i++){
			B[i] = i;
		}
		
		
		
		
	}
	
	

}
