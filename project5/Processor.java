package project5;

import java.util.Arrays;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


/**
 * This class sorts the elements of an array, normalizes the data, and saves the information
 * into an image.
 * 
 * @version 05/17/2014
 * @author Megan Straub <mstraub1@umbc.edu>
 * CMSC 341 - Spring 2014 - Project 5
 * Section 4
 */
public class Processor{
	
	/**
	 * The shared buffer that contains integers to be normalized.
	 */
	private Buffer b2;
	
	/**
	 * The threshold for sorting the data, defined by Main.
	 */
	private int t;
	
	/**
	 * The size of the image, defined by Main.
	 */
	private int n;
	
	/**
	 * The data from b2, is put into an array to help with normalizing.
	 */
	private int[] b2Data; //data to be normalzed from B2
	
	/**
	 * The normalized data.
	 */
	private int[] normData; //normalized data
	
	/**
	 * The name of the file path for the image created.
	 */
	private String pathName; //name of the file path for the iamge
	
	/**
	 * The amount of time it takes to merge sort b2Data in milliseconds.
	 */
	private long mergeSortTime; //track how long it takes to do the merge sort in milliseconds
	
	/**
	 * A constructor method that takes in the shared buffer, n and t.
	 * 
	 * @param b2 the shared buffer
	 * @param n the size of the image, defined by Main
	 * @param t the threshold for sorting the data, defined by Main
	 */
	public Processor(Buffer b2, int n, int t){
		this.b2 = b2;
		this.n = n;
		this.t = t;
	}
	
	/**
	 * This processes the data from the array, b2Data. First, the array is sorted
	 * by a merge sort. Then the data is normalized and placed into a new array, normData.
	 * Then an image is created from the normData array, and the image is placed into the
	 * image folder.
	 */
	public void processData(){
		try{
			SortingMachine sorter = new SortingMachine();
			
			long start = System.currentTimeMillis(); //current time of sorting
			b2Data = sorter.sort(b2.makeArray(), t);
			mergeSortTime = System.currentTimeMillis() - start;
			
			normData = new int[b2Data.length];
			normalizeData();
			
			//use this for GL
			/*
			pathName = getClass().getClassLoader().getResource(".").getPath();
			pathName += String.format("output_N%d_T%d.jpg", n, t);
			pathName = pathName.replaceAll("bin/", "images/");
			*/											
			
			// Use this for Windows
			pathName = String.format("images/output_N%d_T%d.jpg", n, t);
			
			BufferedImage buffImage = new BufferedImage(n, n, BufferedImage.TYPE_BYTE_GRAY);
			 
			int index = 0;
			
			for(int length = 0; length < buffImage.getWidth(); length++){
				for(int width = 0; width < buffImage.getHeight(); width++){
					
					buffImage.getRaster().setPixel(width, length, new int[] {normData[index]});
					index++;
				}
			}

			ImageIO.write(buffImage, "jpg", new File(pathName)); 
			
		}catch (Exception e){
			System.out.println("Oh no! An exepction in Processor!!");
			e.printStackTrace();	
		}
	}
	
	/**
	 * This normalizes the data into a range from 0 to 255.
	 */
	public void normalizeData(){
		//normalize between 0 to 255 a to b range
		int index = 0;
		
		for (Integer value : b2Data){
			
			int y = (int) ((255.0 * value)/ (4096.0)); //formula given from teacher
			normData[index] = y;
			index++;
		}
	}
	
	/**
	 * Retrieves the path name of the jpg image.
	 * 
	 * @return the file name
	 */
	public String getPathName(){
		return pathName;
	}
	
	/**
	 * Retrieves the amount of time it takes to merge sort the array.
	 * 
	 * @return the merge time
	 */
	public long getMergeTime(){
		return mergeSortTime;
	}
	
	/**
	 * This is a private class used for merge sorting. If the array given
	 * is smaller than a certain threshold, insertion sort is used instead.
	 */
	private class SortingMachine {
		
		/**
		 * The main sort method. If the array is smaller than the threshold, t,
		 * then insertion sort is used instead. If it isn't smaller, merge sort is
		 * used to sort the array.
		 * 
		 * @param array the array to be sorted
		 * @param t the threshold
		 * @return the sorted array
		 */
		public int[] sort(int[] array, int t){
			if(array.length <= t){ //less than the threshold
				insertionSort(array);
			}else {
				mergeSort(array);
			}
			
			return array;
		}
		
		/**
		 * The insertion sort method; this is used only if the array is 
		 * smaller than the threshold. 
		 * 
		 * @param array the array to be sorted
		 */
		private void insertionSort(int[] array){
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
		
		/**
		 * The merge sort method. This divides the array into halfs, and sorts each half
		 * recursively until the array is sorted.
		 * 
		 * @param array the array to be sorted
		 */
		private void mergeSort(int[] array){
			
			if(array.length > 1){
				
				//The array cut in half
				int[] left = Arrays.copyOfRange(array, 0, (array.length / 2));		
				int[] right = Arrays.copyOfRange(array, left.length, array.length);
				
				//recursive calls to keep cutting the array in half
				mergeSort(left);
				mergeSort(right);
				
				//merging the halfs together
				merge(array, left, right);
			}	
		}
		
		/**
		 * This merges two arrays together, and puts them in sorted order.
		 * 
		 * @param array the original array to be written over with the sorted array
		 * @param left the left half of the original array
		 * @param right the right half of the original array
		 */
		private void merge(int[] array, int[] left, int[] right){
			int numLeft = 0; //next num in left array
			int numRight = 0; //next num in right array
			
			int i = 0; //next position in array
			
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
			
	        System.arraycopy(left, numLeft, array, i, left.length - numLeft);
	        System.arraycopy(right, numRight, array, i, right.length - numRight);
		}	
	}
}
