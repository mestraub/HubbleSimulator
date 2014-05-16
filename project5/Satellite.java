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
	Buffer buf;
	boolean running; // so parent thread can say "stop"
	
	
	public Satellite(Buffer b){
		buf = b;
	}
	
	public void run(){

		// while still space in the buffer
		// keep pushing things to the buffer that are random
		try{
			
		//	buf = new Buffer();
			int count = 0;
			int randNum = 0;
			Random num = new Random();
			 
			running = true;
			if(buf.isFull())
				buf.waitForSpace();
			while(running){
				
				randNum = num.nextInt(4096);
				buf.add(randNum);
				
				System.out.println("Count num: " + count + " Random Number: " + randNum);
			//	shared.printBuffer();
				
				Thread.sleep(100); //10,200 milliseconds
				count++;
			}
			
			//shared.printBuffer();
		}catch (InterruptedException e){
			System.out.println("Oh no! An exepction!");
			e.printStackTrace();	
		}

		
	}
	
	/*
	public void doThis(){
		Satellite dc = new Satellite();
		Thread t = new Thread(dc);
		
		t.run();
		System.out.println("Ok, I'm done.") ;
		System.exit(0);
	}
	*/
}
