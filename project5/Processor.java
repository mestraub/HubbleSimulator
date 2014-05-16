/**
 * 
 */
package project5;

import java.util.concurrent.RecursiveAction;

/**
 * @author Megan
 *
 */
//one sort elements
// two normalise the data
// three save the info into an image
public class Processor extends RecursiveAction{
	int t;
	Buffer b2;
	
	public Processor(Buffer b2, int t){
		this.b2 = b2;
		this.t = t;
	}
	
	
	/* (non-Javadoc)
	 * @see java.util.concurrent.RecursiveAction#compute()
	 */
	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		
	}

}
