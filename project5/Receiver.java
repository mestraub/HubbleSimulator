/**
 * 
 */
package project5;

/**
 * @author Megan
 *
 */
public class Receiver implements Runnable {
	private Buffer b1;
	
	private Buffer b2;
	
	private int n;
	
	private int t;
	
	private Processor process;
	
//	public boolean running;
	
	public Receiver(Buffer b1, Buffer b2, int n, int t){
		this.b1 = b1;
		this.b2 = b2;
		this.n = n;
		this.t = t;
		process = new Processor(b2, n, t);
	}
	
	public void run() {
		try{

			while(b1.getSize() < (n * n)){
				Thread.sleep(1000);
			}
			
			boolean running = true;
			
			do{
				b2.add(b1.remove()); // adds data to b1				
			//	System.out.println("This was added to buffer 2: " + data);
			}while(!b2.isFull());
			
			process.processData();
		//	b1.clear();
		//	System.out.println("Size of b1 now: " + b1.getSize());
			
		} catch (Exception e){
			System.out.println("Oh no! An exepction in Receiver!!");
			e.printStackTrace();
		}
	}
	
	public String getPathName(){
		return process.getPathName();
	}
	
	public long getMergeTime(){
		return process.getMergeTime();
	}
}
