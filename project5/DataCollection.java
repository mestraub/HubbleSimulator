/**
 * 
 */
package project5;
import java.util.Random;

/**
 * @author Megan
 *
 */
public class DataCollection implements Runnable {

	/**
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataCollection dc = new DataCollection();
		dc.doThis();
	}
	*/
	public DataCollection(){
		
	}
	
	public void run(){
		SharedBuffer shared = new SharedBuffer();
		int count = 0;
		int randNum = 0;
		Random num = new Random();
		
		
		// while still space in the buffer
		// keep pushing things to the buffer that are random
		try{
			while(count <= shared.getSize()){
			//for (int i = 0; i < count; i++){
				randNum = num.nextInt(4096);
				System.out.println("Count num: " + count + " Random Number: " + randNum);
				Thread.sleep(10200); //10,200 milliseconds
				count++;
			//}
			}
		}catch (InterruptedException e){
			System.out.println("Oh no! An exepction!");
			e.printStackTrace();	
		}

		
	}
	
	public void doThis(){
		DataCollection dc = new DataCollection();
		Thread t = new Thread(dc);
		
		t.run();
		System.out.println("Ok, I'm done.") ;
		System.exit(0);
	}
}
