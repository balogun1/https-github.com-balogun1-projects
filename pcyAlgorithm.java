package Apirori_PCY;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class pcyAlgorithm{
String[] theArray;
int arraySize;
int itemsInArray = 0;


/*public void HashFunction(String[] stringforArray,String[] theArray) throws IOException{
	for(int n = 0; n<stringforArray.length; n++){
		String newElement = stringforArray[n];
		theArray[Integer.parseInt(newElement)] = newElement;
	}*/



/*public pcyAlgorithm(int arraySize){
	
	this.arraySize = arraySize;
	theArray = new String[arraySize];
	Arrays.fill(theArray, "retail.txt");
	
}*/

	public static void main(String[] args)  throws IOException  {
		long start = System.currentTimeMillis();
		String line = null;
		boolean match;
		double minSupportThreshold = 0;
		HashMap< Integer, Boolean > hash2 = new HashMap< Integer, Boolean>();
		ArrayList<int[]> frequentCandidates = new ArrayList<int[]>();

		ArrayList<int[]> itemset = new ArrayList<>();
		int numItems = 0;
		int numOfTranactions= 0;   
		System.out.println("****PCY ALGORITHM IMPLEMENTATION*****");
		System.out.println();

		try{
		    HashMap<Integer ,Integer > hash = new HashMap< Integer,Integer >();
		 
		    BufferedReader rd = new BufferedReader( new FileReader ("retail.txt"));
		    
		    ArrayList<String> lines = new ArrayList<String>();
		    while ((line = rd.readLine()) != null){
		    	if(line.matches("\\s*")) continue;
		    	StringTokenizer t = new StringTokenizer(line," ");
			      
		    	numOfTranactions++; // count number of transactions
		        while (t.hasMoreTokens()) {
	    			int x = Integer.parseInt(t.nextToken());
	    			
	    			if (x+1>numItems) numItems=x+1;
	    		}    
			   

		    }
		    
		    int solve = 0;
		    Scanner scan = new Scanner(System.in);
		    System.out.println("Enter your minimum Support threshold:");
		    minSupportThreshold = scan.nextDouble();
		   // System.out.println("Total number of item in Basket "+ count);
		    System.out.println("Total Number of Item in Bucket if support is 100% "+numOfTranactions);
		    int threshold = numOfTranactions/2;
		    System.out.println("Total Number of Item in Bucket if support is " + minSupportThreshold +"% "+ threshold);
		    System.out.println((minSupportThreshold*100)+"% (absolute "+Math.round(numOfTranactions*minSupportThreshold)+")");
		    for ( int j = 0 ; j < hash.size() ; j++){
		        hash.get(j); 
		    }
		    
		    for(int k =1; k< itemset.size(); k++){
		    	for(int i= k+1; k< itemset.size(); k++){
		    		int[] X = itemset.get(k);
		    		int[] Y = itemset.get(i);
		    		assert (X.length==Y.length);
		    		int index2 = Y.length;
		    		int index =X.length;
		    		 solve = index + index2 %10000;
		    	}
		    	System.out.println(solve);
		    }
    		
		}catch(FileNotFoundException e){}catch (IOException e) {}
		
		
		// pass 2 starts
		int count2[] = new int[itemset.size()];
		boolean[] trans = new boolean[numItems];
		for(int j = 1; j< numOfTranactions; j++){
			
		}
		//hash to bucket
		for(int c = 0; c < itemset.size(); c++){
			match = true;
			int[] cand = itemset.get(c);
			for (int xx : cand) {
				//check if frequent
				if (trans[xx] == false) {
					match = false;
					break;
				}
			}
			if (match) { 
				count2[c]++;
				
			}
		}
		itemset = frequentCandidates;
		long end = System.currentTimeMillis();
		 System.out.println("Execution time is: "+((double)(end-start)/1000) + " seconds.");
		  System.out.println("Frequents sets for support "+(minSupportThreshold*100)+"% (absolute "+Math.round(numOfTranactions*minSupportThreshold)+")");
		  System.out.println("Done");
		
}
	
	
}
		
	