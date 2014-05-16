/**
 * 
 */
package project5;

/**
 * @author Megan
 *
 */
public class Receiver implements Runnable {
	Buffer b1;
	Buffer b2;
	int n;
	public boolean running;
	
	public Receiver(Buffer b1, Buffer b2, int n){
		this.b1 = b1;
		this.b2 = b2;
		this.n = n;
	}
	
	public void run() {
		try{
			int data;
			
			running = true;

			while(b1.getSize() <= n){
				Thread.sleep(1);
			}
			
			do{
				b1.waitForData();
				data = b1.remove();
					
				b2.add(data);
				
				System.out.println("This was added to buffer 2: " + data);
			}while(running);
			
		//	b1.clear();
		//	System.out.println("Size of b1 now: " + b1.getSize());
			
		} catch (Exception e){
			System.out.println("Oh no! An exepction! In receiver.");
		}
	}

}
