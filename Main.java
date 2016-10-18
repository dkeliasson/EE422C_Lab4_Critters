/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4; // cannot be in default package
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console

    /* Preprocessor Directives */
    private static final String quit = "quit";
    private static final String show = "show";
    private static final String step = "step";
    private static final String seed = "seed";
    private static final String make = "make";
    private static final String stats = "stats";
    

    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
       

	        /* Do not alter the code above for your submission. */
	        /* Write your code below. */
	        String error = "error processing: ";
	      
	        while(true){
	        	/**
	        	 * Commands:
	        	 * 		quit
	        	 * 		show
	        	 * 		seed
	        	 * 		step
	        	 * 		make
	        	 * 		stats	
	        	 */
	        	
	        	System.out.print("critters> ");
	        	
	        	/* Get input */
	        	String[] input = kb.nextLine().split(" ");
	        	
	        	/* Decide what to do */
	        	switch(input[0]){
	        	case quit :
	        		if(input.length > 1){
	        			System.out.print(error);
	    				for(int i = 0; i < input.length - 1; i++){
	            			System.out.print(input[i] + " ");
	            		}
	            		System.out.println(input[input.length - 1]);
	        		}
	        		else{
	        			System.exit(0);
	        		} 
	        		break;
	        	case show : 
	        		if(input.length > 1){
	        			System.out.print(error);
	    				for(int i = 0; i < input.length - 1; i++){
	            			System.out.print(input[i] + " ");
	            		}
	    				System.out.println(input[input.length - 1]);
	        		}
	        		else{
	        			Critter.displayWorld();
	        		} 
	        		break;
	        	case step :
	        		if(input.length > 2){
	        			System.out.print(error);
	    				for(int i = 0; i < input.length - 1; i++){
	            			System.out.print(input[i] + " ");
	            		}
	    				System.out.println(input[input.length - 1]);
	        		}
	        		else if(input.length > 1){
	        			int steps = 0;
	        			try{
	        				steps = Integer.parseInt(input[1]);
	        			}catch (NumberFormatException e){
	        				System.out.print(error);
	        				for(int i = 0; i < input.length - 1; i++){
	                			System.out.print(input[i] + " ");
	                		}
	        				System.out.println(input[input.length - 1]);
	        			}
	        			for(int i = 0; i < steps; i++){
	        				Critter.worldTimeStep();
	        			}
	        		}
	        		else{
	        			Critter.worldTimeStep();
	        		}
	        		break;
	        	case seed :
	        		if(input.length > 2){
	        			System.out.print(error);
	    				for(int i = 0; i < input.length - 1; i++){
	            			System.out.print(input[i] + " ");
	            		}
	    				System.out.println(input[input.length - 1]);
	        		}
	        		else{
		        		long random = 0;
		        		try{
		        			random = Long.parseLong(input[1]);
		        		}catch (NumberFormatException e){
		        			System.out.print(error);
		        			for(int i = 0; i < input.length - 1; i++){
		            			System.out.print(input[i] + " ");
		            		}
		        			System.out.println(input[input.length - 1]);
		        		}
		        		Critter.setSeed(random);
	        		}
	        		break;
	        	case make :
	        		
	        		if(input.length == 2){
	        			try{
	        				Critter.makeCritter(input[1]);
	        			}catch (InvalidCritterException e){
	        				System.out.print(error);
	        				for(int i = 0; i < input.length - 1; i++){
	                			System.out.print(input[i] + " ");
	                		}
	        				System.out.println(input[input.length - 1]);
	        			}
	        		}
	        		else if(input.length == 3){
	        			int value = Integer.parseInt(input[2]);
	        			for(int count = 0; count < value; count++){
	        				try{
	            				Critter.makeCritter(input[1]);
	            			}catch (InvalidCritterException e){
	            				System.out.print(error);
	            				for(int i = 0; i < input.length - 1; i++){
	                    			System.out.print(input[i] + " ");
	                    		}
	            				System.out.println(input[input.length - 1]);
	            			}
	        			}
	        		}
	        		else{
	        			System.out.print(error);
	    				for(int i = 0; i < input.length - 1; i++){
	            			System.out.print(input[i] + " ");
	            		}
	    				System.out.println(input[input.length - 1]);
	        		}
	        		break;
	//        	case stats :
	//        		Class<?> Bug;
	//        		/* Use Java Reflection to qualify name */
	//        		try{
	//        			Bug = Class.forName(input[1]);
	//        		}catch(ClassNotFoundException e){
	//        			System.out.print(error);
	//        			for(int i = 0; i < input.length - 1; i++){
	//            			System.out.print(input[i] + " ");
	//            		}
	//            		System.out.print(input[input.length - 1]);
	//        		}
	//        		try{
	//        			Critter.getInstances(input[1]);
	//        		}catch (InvalidCritterException e){
	//        			System.out.print(error);
	//        			for(int i = 0; i < input.length - 1; i++){
	//            			System.out.print(input[i] + " ");
	//            		}
	//            		System.out.print(input[input.length - 1]);
	//        		}
	//        		Class<?>[] types = {List.class};
	//        		try {                
	//        	        Method m = Bug.getMethod("runStats", types);    
	//        		}catch(NoSuchMethodException | SecurityException e){
	//        			System.out.print(error);
	//        			for(int i = 0; i < input.length - 1; i++){
	//            			System.out.print(input[i] + " ");
	//            		}
	//            		System.out.print(input[input.length - 1]);
	//        		}
	//        		m.invoke(null, types);     		
	        	default :
	        		System.out.print("invalid command: ");
	        		for(int i = 0; i < input.length - 1; i++){
	        			System.out.print(input[i] + " ");
	        		}
	        		System.out.println(input[input.length - 1]);
	        		break;
	        	}
	        	
	        }
	     }
    }
}
