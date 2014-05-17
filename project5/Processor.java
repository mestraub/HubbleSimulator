/**
 * 
 */
package project5;

import java.util.Arrays;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.WritableRaster;

/**
 * @author Megan
 *
 */
//one sort elements
// two normalise the data
// three save the info into an image
public class Processor{

	private Buffer b2;
	
	private int t;
	
	private int n;
	
	private int[] b2Data; //data to be normalzed from B2
	
	private int[] normData; //normalized data
	
	private String pathName; //name of the file path for the iamge
	
	private long mergeSortTime; //track how long it takes to do the merge sort in milliseconds
	
	public Processor(Buffer b2, int n, int t){
		this.b2 = b2;
		this.n = n;
		this.t = t;
	}
	
	public void processData(){
		try{
			SortingMachine sorter = new SortingMachine();
			
			long start = System.currentTimeMillis(); //current time of sorting
			b2Data = sorter.sort(b2.makeArray(), t);
			mergeSortTime = System.currentTimeMillis() - start;
			
			normData = new int[b2Data.length];
			normalizeData();
			
			// ant creates image folder for use of project
			
			
			BufferedImage bufferedImage = new BufferedImage(n, n, BufferedImage.TYPE_BYTE_GRAY);
			
			int index = 0;
			
			for(int row = 0; row < bufferedImage.getHeight(); row++){
				for(int col = 0; col < bufferedImage.getHeight(); col++){
					bufferedImage.setRGB(row, col, normData[index]);
					index++;
				}
			}
			
			pathName = String.format("images/_N%d_T%d.jpg", n, t);
			ImageIO.write(bufferedImage, "jpg", new File(pathName)); //making da file
			
		}catch (Exception e){
			System.out.println("Oh no! An exepction in Receiver!!");
			e.printStackTrace();
			
		}
	}

	public void normalizeData(){
		//normalize between 0 to 255 a to b range
		int index = 0;
		
		for (Integer value : b2Data){
			
			int y = (int) ((255.0 * value)/ (4096.0)); //formula given from teacher
			normData[index] = y;
			index++;
		}
	}
	
	public String getPathName(){
		return pathName;
	}
	
	public long getMergeTime(){
		return mergeSortTime;
	}
	
	private class SortingMachine {
		
		SortingMachine(){
			
		}
		
		public int[] sort(int[] array, int t){
			if(array.length <= t){ //less than the threshold
				insertionSort(array);
				// do insertion sort
			}else {
				//do merge sort
				mergeSort(array);
			}
			
			return array;
		}
		
		synchronized public void insertionSort(int[] array){
			int temp;
			
			for (int i = 0; i < array.length; i++){
				for (int j = i; j < array.length; j++){
					if(array[j] < array[i]){
						temp = array[j];
						array[i] = array[j];
						array[j] = temp;
					}
				}
			}
		}
		
		public void mergeSort(int[] array){
			
			if(array.length > 1){
				
				int[] left = Arrays.copyOfRange(array, 0, (array.length / 2)); //creates new array lists			
				int[] right = Arrays.copyOfRange(array, left.length, (array.length - left.length));
				
				mergeSort(left);
				mergeSort(right);
				
				merge(array, left, right);
			}	
		}
		
		public void merge(int[] array, int[] left, int[] right){
			int numLeft = 0; //next num in left array
			int numRight = 0; //next num in right array
			
			int i = 0; //next position in result
			
			while(numLeft < left.length && numRight < right.length){
				if(left[numLeft] < right[numRight]){
					array[i] = left[numLeft];
					numLeft++;
				}else{
					array[i] = right[numRight];
					numRight++;
				}
				
				i++;
			}
		}	
	}
}
