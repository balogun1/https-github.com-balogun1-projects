package Apirori_PCY;


import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Apirori extends Observable {


    public static void main(String[] args) throws Exception {
    	System.out.println("****Aprirori Implementation****");
    	System.out.println();
        apirori = new Apirori(args);
    }

   
    private ArrayList<int[]> itemsets ;
  
    private String transactionFile;
    private int numItems; 
    private int numOfTransactions; 
    private int transaction2;
   private int  transaction3;
    private double minSupportThreshold; 
    int basket;
	int basket2;
    
    private boolean boolvalue = false;

	private static Apirori apirori;

	private Scanner scan;

	private BufferedReader dataInput;

	private int numberOfFrequentSets;

    public  Apirori(String[] args, Observer ob) throws Exception
    {
    	boolvalue = true;
    	configure(args);
    	this.addObserver(ob);
    	Ap();
    }

  
    public  Apirori(String[] args) throws Exception
    {
        configure(args);
        Ap();
    }

    private void Ap() throws Exception {
      
        long start = System.currentTimeMillis();

        
        createItemsetsOfSize1();        
        int itemsetNumber=1;        
        numberOfFrequentSets = 0;
        
        while (itemsets.size()>0)
        {

            calculateFrequentItemsets();

            if(itemsets.size()!=0)
            {
                numberOfFrequentSets+=itemsets.size();
                log("Found "+itemsets.size()+" frequent itemsets of size " + itemsetNumber + " (with support "+(minSupportThreshold*100)+"%)");;
                
            }

            itemsetNumber++;
        } 

        //display the execution time
        long end = System.currentTimeMillis();
        log("Execution time is: "+((double)(end-start)/1000) + " seconds.");
        log("frequents sets for support "+(minSupportThreshold*100)+"% (absolute "+Math.round(numOfTransactions*minSupportThreshold)+")");
        log("Done");
    }

    @SuppressWarnings("unused")
	private void foundFrequentItemSet(final int[] itemset, final int support) {
    	if (boolvalue) {
            this.setChanged();
            notifyObservers(itemset);
    	}
    	else {System.out.println(Arrays.toString(itemset) + "  ("+ ((support / (double) numOfTransactions))+" "+support+")");}
    }

    /** outputs a message in Sys.err if not used as library */
    private void log(String message) {
    	if (!boolvalue) {
    		System.err.println(message);
    	}
    }
    private void checkSupport(double support) {
        if (Double.isNaN(support)) {
            throw new IllegalArgumentException("The input support is NaN.");
        }

        if (support > 1.0) {
            throw new IllegalArgumentException(
                    "The input support is too large: " + support + ", " +
                    "should be at most 1.0");
        }

        if (support < 0.0) {
            throw new IllegalArgumentException(
                    "The input support is too small: " + support + ", " +
                    "should be at least 0.0");
        }
    }
   
    private void configure(String[] args) throws Exception
    {        
     scan = new Scanner (System.in);
     
        if (args.length!=0) transactionFile = args[0];
        else transactionFile = "retail.txt";
    	if (args.length>=1)checkSupport(minSupportThreshold);
    	else
    		System.out.println("Enter your minimum Support threshold");
    			minSupportThreshold = scan.nextDouble();// by default
    	if (minSupportThreshold>1 || minSupportThreshold<0) throw new Exception("minSup: bad value");
    	
    	
    	
    	numItems = 0;
    	numOfTransactions=0;
    	dataInput = new BufferedReader(new FileReader(transactionFile));
    	while (dataInput.ready()) {    		
    		String line=dataInput.readLine();
    		if (line.matches("\\s*")) continue;
    		numOfTransactions++;
    	transaction2 =  numOfTransactions/2;
   		 transaction3 =  numOfTransactions/5;
   		 
   		  basket = (int) (numOfTransactions/2 *0.01);
   		  basket2 = (int) (numOfTransactions/5 * 0.05);

    		StringTokenizer t = new StringTokenizer(line," ");
    		while (t.hasMoreTokens()) {
    			int x = Integer.parseInt(t.nextToken());
    			
    			if (x+1>numItems) numItems=x+1;
    		}    		
    	}  
    	
        outputConfig();

    }
    
   
	private void outputConfig() {
		 log("Bucket:  " +numItems+" items, "+numOfTransactions +" transactions, ");
		log("Bucket:  "+ numItems/2 + " bucket "+basket+" items, "+transaction2+" transactions, ");
		 log("Bucket: "+numItems/5 + " Bucket "+basket2+" items, "+transaction3+" transactions, ");

		 log("minsup = "+minSupportThreshold+"%");
	}

	
	private void createItemsetsOfSize1() {
		itemsets = new ArrayList<int[]>();
        for(int i=0; i<numItems; i++)
        {
        	int[] cand = {i};
        	itemsets.add(cand);
        }
	}
			
   



    private void line2booleanArray(String line, boolean[] trans) {
	    Arrays.fill(trans, false);
	    StringTokenizer stFile = new StringTokenizer(line, " ");
	    
	    while (stFile.hasMoreTokens())
	    {
	    	
	        int parsedVal = Integer.parseInt(stFile.nextToken());
			trans[parsedVal]=true; 
	    }
    }

    
  
    private void calculateFrequentItemsets() throws Exception
    {
    	
      

        ArrayList<int[]> frequentCandidates = new ArrayList<int[]>();

        boolean match; 
        int count[] = new int[itemsets.size()]; 


		BufferedReader data_in = new BufferedReader(new InputStreamReader(new FileInputStream(transactionFile)));

		boolean[] trans = new boolean[numItems];
		
	
		for (int i = 0; i < numOfTransactions; i++) {

			String line = data_in.readLine();
			line2booleanArray(line, trans);

			
			for (int c = 0; c < itemsets.size(); c++) {
				match = true; 
				
				int[] cand = itemsets.get(c);
				
				for (int xx : cand) {
					if (trans[xx] == false) {
						match = false;
						break;
					}
				}
				if (match) { 
					count[c]++;
					
				}
			}

		}
		
		data_in.close();

		

        itemsets = frequentCandidates;
    }
}