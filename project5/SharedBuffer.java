/**
 * 
 */
package project5;

//import java.nio.Buffer;

/**
 * @author Megan
 *
 */
public class SharedBuffer implements Runnable{
	public int[] B;
//	Buffer buff;
	int defaultSize = 256;
	
	public SharedBuffer(){
		B = new int[defaultSize];
	//	buff = new Buffer();
	}
	
	public int getSize(){
		return B.length;
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
