/**
 * 
 */
package project5;
import java.util.Random;

/**
 * @author Megan
 *
 */
public class Satellite implements Runnable {

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
	private Buffer b1;
	private boolean running; // so parent thread can say "stop"
	private Random randNum;
	
	
	public Satellite(Buffer b1){
		this.b1 = b1;
		this.running = true;
		randNum = new Random();
	}
	
	public void run(){

		// while still space in the buffer
		// keep pushing things to the buffer that are random
		try{
			int count = 0;
			
			running = true;

			while(running){
				
				int num = randNum.nextInt(4097); // to count for 4096
				
				if(b1.isFull()){
					Thread.sleep(1000);
				}else{
					b1.add(num); //change to randNum.nextInt(4097) later
				//	System.out.println("Count num: " + count + " Random Number: " + num);
				}
			//	Thread.sleep(100); //10,200 milliseconds
				count++;
			}

		}catch (InterruptedException e){
			System.out.println("Oh no! An exepction in Satellite!!");
			e.printStackTrace();	
		}	
	}
	
	public void end(){
		running = false;
	}
}
