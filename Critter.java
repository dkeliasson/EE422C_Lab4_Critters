/* CRITTERS Critter.java
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
package assignment4;

import java.util.*;
import java.lang.reflect.*;
import java.math.*;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private static ArrayList[][] ourworld = new ArrayList[Params.world_height][Params.world_width];
	private boolean hasMoved = false;

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
		if(this.hasMoved){
			this.energy -= Params.walk_energy_cost;
			return;
		}
		switch(direction){
		case 0 :
			x_coord = (x_coord + 1) % Params.world_width;
		case 1 : 
			x_coord = (x_coord + 1) % Params.world_width;
			y_coord = (y_coord - 1);
			if(y_coord < 0){
				y_coord = Params.world_height - 1;
			}
		case 2 :
			y_coord = (y_coord - 1);
			if(y_coord < 0){
				y_coord = Params.world_height - 1;
			}
		case 3 :
			x_coord = (x_coord - 1);
			if(x_coord < 0){
				x_coord = Params.world_width - 1;
			}
			y_coord = (y_coord - 1);
			if(y_coord < 0){
				y_coord = Params.world_height - 1;
			}
		case 4 :
			x_coord = (x_coord - 1);
			if(x_coord < 0){
				x_coord = Params.world_width - 1;
			}
		case 5 :
			x_coord = (x_coord - 1);
			if(x_coord < 0){
				x_coord = Params.world_width - 1;
			}
			y_coord = (y_coord + 1) % Params.world_height;
		case 6 :
			y_coord = (y_coord + 1) % Params.world_height;
		case 7 :
			x_coord = (x_coord + 1) % Params.world_width;
			y_coord = (y_coord + 1) % Params.world_height;
		default : 
			x_coord = x_coord;
			y_coord = y_coord;
		}
		energy -= Params.walk_energy_cost;
	}
	
	protected final void run(int direction) {
		if(this.hasMoved){
			this.energy -= Params.run_energy_cost;
			return;
		}
		switch(direction){
		case 0 :
			x_coord = (x_coord + 2) % Params.world_width;
		case 1 : 
			x_coord = (x_coord + 2) % Params.world_width;
			y_coord = (y_coord - 2);
			if(y_coord < 0){
				y_coord = Params.world_height - 2;
			}
		case 2 :
			y_coord = (y_coord - 2);
			if(y_coord < 0){
				y_coord = Params.world_height - 2;
			}
		case 3 :
			x_coord = (x_coord - 2);
			if(x_coord < 0){
				x_coord = Params.world_width - 2;
			}
			y_coord = (y_coord - 2);
			if(y_coord < 0){
				y_coord = Params.world_height - 2;
			}
		case 4 :
			x_coord = (x_coord - 2);
			if(x_coord < 0){
				x_coord = Params.world_width - 2;
			}
		case 5 :
			x_coord = (x_coord - 2);
			if(x_coord < 0){
				x_coord = Params.world_width - 2;
			}
			y_coord = (y_coord + 2) % Params.world_height;
		case 6 :
			y_coord = (y_coord + 2) % Params.world_height;
		case 7 :
			x_coord = (x_coord + 2) % Params.world_width;
			y_coord = (y_coord + 2) % Params.world_height;
		default : 
			x_coord = x_coord;
			y_coord = y_coord;
		}
		energy -= Params.run_energy_cost;
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		/* Check for reproduction energy and return if critter can't have little babies */
		if(this.energy < Params.min_reproduce_energy){ return; }
		else{
			double small_energy = offspring.energy = (this.energy)/2;
			offspring.energy = (int) Math.floor(small_energy);
			/* Half parents energy (rounding up) */
			double big_energy = (this.energy)/2;
			this.energy = (int) Math.ceil(big_energy);
			switch(direction){
			case 0 :
				offspring.x_coord = (this.x_coord + 1) % Params.world_width;
			case 1 : 
				offspring.x_coord = (this.x_coord + 1) % Params.world_width;
				offspring.y_coord = (this.y_coord - 1);
				if(offspring.y_coord < 0){
					offspring.y_coord = Params.world_height - 1;
				}
			case 2 :
				offspring.y_coord = (this.y_coord - 1);
				if(offspring.y_coord < 0){
					offspring.y_coord = Params.world_height - 1;
				}
			case 3 :
				offspring.x_coord = (this.x_coord - 1);
				if(offspring.x_coord < 0){
					offspring.x_coord = Params.world_width - 1;
				}
				offspring.y_coord = (this.y_coord - 1);
				if(offspring.y_coord < 0){
					offspring.y_coord = Params.world_height - 1;
				}
			case 4 :
				offspring.x_coord = (this.x_coord - 1);
				if(offspring.x_coord < 0){
					offspring.x_coord = Params.world_width - 1;
				}
			case 5 :
				offspring.x_coord = (this.x_coord - 1);
				if(offspring.x_coord < 0){
					offspring.x_coord = Params.world_width - 1;
				}
				offspring.y_coord = (this.y_coord + 1) % Params.world_height;
			case 6 :
				offspring.y_coord = (this.y_coord + 1) % Params.world_height;
			case 7 :
				offspring.x_coord = (this.x_coord + 1) % Params.world_width;
				offspring.y_coord = (this.y_coord + 1) % Params.world_height;
			default : 
				offspring.x_coord = this.x_coord;
				offspring.y_coord = this.y_coord;
			}
		}
		/* Add the baby to his/her respective cradle */
		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		/* Prepend package to name */
		String newClass = myPackage + "." + critter_class_name;
		
		/* Creating class template for a Class of critters */
		Class<?> Bug;
		/* Attempt to initialize class, if not found throw exception */
		try{
			Bug = Class.forName(newClass);
		}catch(ClassNotFoundException e){
			throw new InvalidCritterException(newClass);
		}
		/* Declaring constructor */
		Constructor<?> construct = null;
		/* Declare object to setup */
		Object instanceofBug = null;
		/* Attempt to get constructor, else throw exception */
		try{
			construct = Bug.getConstructor();
		}catch (NoSuchMethodException | SecurityException e){
			e.printStackTrace();
		}
		/* Attempt to construct the object */
		try{
			instanceofBug = construct.newInstance();
		}catch (IllegalAccessException |  InstantiationException |  IllegalArgumentException |  InvocationTargetException e){
			e.printStackTrace();
		}
		
		Critter crit = (Critter)instanceofBug;
		crit.x_coord = getRandomInt(Params.world_width);
		crit.y_coord = getRandomInt(Params.world_height);
		crit.energy = Params.start_energy;
		population.add(crit);
		
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		if(!critter_class_name.equals("Critter")){
			/* Creating class template for a Class of critters */
			Class<?> Bug;
			/* Attempt to initialize class, if not found throw exception */
			try{
				Bug = Class.forName(critter_class_name);
			}catch(ClassNotFoundException e){
				throw new InvalidCritterException(critter_class_name);
			}
			/* Declaring constructor */
			Constructor<?> construct = null;
			/* Declare object to setup */
			Object instanceofBug = null;
			/* Attempt to get constructor, else throw exception */
			try{
				construct = Bug.getConstructor();
			}catch (NoSuchMethodException | SecurityException e){
				e.printStackTrace();
			}
			/* Attempt to construct the object */
			try{
				instanceofBug = construct.newInstance();
			}catch (IllegalAccessException |  InstantiationException |  IllegalArgumentException |  InvocationTargetException e){
				e.printStackTrace();
			}
			Critter crit = (Critter)instanceofBug;
			for(int i = 0; i < population.size(); i++){
				if(population.get(i).equals(crit)){
					result.add(population.get(i));
				}
			}
			return result;
		}
		else{
			result.addAll(population);
			return result;
		}
		
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
	}
	
	public static void worldTimeStep() {
		/* Iterate through every critter and invoke the doTimeStep method */
		Critter critter;
		int i = 0;
		while(i < population.size()){
			critter = population.get(i);
			critter.doTimeStep();
			i++;
		}
		
		/* Resolve encounters between critters */
		Critter a;
		Critter b;
		for(int row = 0; row < Params.world_height; row++){
			for(int col = 0; col < Params.world_width; col++){

					/* More than 1 critter in coordinate */
					/* We must battle */
					/* Pull out 2 critters for battle(hopefully) */
					a = (Critter)ourworld[row][col].get(0);
					b = (Critter)ourworld[row][col].get(1);
					
					
					
					/* Resolve any conflict */
					/* FIGHT TIME */
					/* LETS ROCK THIS */
					/* Check first if either has moved to keep track of in case one attempts to run */
					boolean Amoved = a.hasMoved;
					boolean Bmoved = b.hasMoved;
					
					/* Hold each critters energy to replace if they come back to fight */
					int Aenergy = a.energy;
					int Benergy = b.energy;
					
					/* Now we fight? */
					boolean fight_choiceA = a.fight(b.toString());
					boolean fight_choiceB = b.fight(a.toString());
					
					/* Both chose to duke it out */
					if(fight_choiceA && fight_choiceB){
						int rollA = Critter.getRandomInt(a.energy);
						int rollB = Critter.getRandomInt(b.energy);
						if(rollA >= rollB){
							if(a.energy <= 0){}
							else{
								a.energy += (b.energy/2);
								b.energy = 0;
								ourworld[row][col].remove(1);
							}
						}
						else{
							b.energy += (a.energy/2);
							a.energy = 0;
							ourworld[row][col].remove(0);
						}
					}
					/* B wants to run away */
					else if(fight_choiceA && !fight_choiceB){
						/* if b moved prior to fight then no choice but to fight */
						if(Bmoved){
							a.energy += (b.energy/2);
							b.energy = 0;
							ourworld[row][col].remove(1);
						}
						/* else if b moved during call to fight check that it didn't move to occupied place */
						else if(!Bmoved && b.hasMoved){
							/* Check to see if place that b moved to is populated already */
							boolean populated = false;
							for(i = 0; i < population.size(); i++){
								if((b.x_coord == population.get(i).x_coord) && (b.y_coord == population.get(i).y_coord)){
									populated = true;
								}
							}
							/* if b moved to populated coordinate then they fight */
							if(populated){
								a.energy += (Benergy/2);
								b.energy = 0;
								ourworld[row][col].remove(1);
							}
							else{}
						}					
					}
					/* A wants to run away */
					else if(!fight_choiceA && fight_choiceB){
						/* if a moved prior to fight then no choice but to fight */
						if(Amoved){
							int rollA = Critter.getRandomInt(a.energy);
							int rollB = Critter.getRandomInt(b.energy);
							if(rollA >= rollB){
								if(a.energy <= 0){}
								else{
									a.energy += (b.energy/2);
									b.energy = 0;
									ourworld[row][col].remove(1);
								}
							}
							else{
								b.energy += (a.energy/2);
								a.energy = 0;
								ourworld[row][col].remove(0);
							}
						}
						/* else if a moved during call to fight check that it didn't move to occupied place */
						else if(!Amoved && a.hasMoved){
							/* Check to see if place that b moved to is populated already */
							boolean populated = false;
							for(i = 0; i < population.size(); i++){
								if((a.x_coord == population.get(i).x_coord) && (a.y_coord == population.get(i).y_coord)){
									populated = true;
								}
							}
							/* if a moved to populated coordinate then they fight */
							if(populated){
								int rollA = Critter.getRandomInt(a.energy);
								int rollB = Critter.getRandomInt(b.energy);
								if(rollA >= rollB){
									a.energy = Aenergy;
									a.energy += (b.energy/2);
									b.energy = 0;
									ourworld[row][col].remove(1);
								}
								else{
									b.energy += (a.energy/2);
									a.energy = 0;
									ourworld[row][col].remove(0);
								}
							}
							else{}
						}		
					}
					/* else they both are babies and want to run away crying */
					else{
						/* Check all possible combinations of A and B movements */
						
					}		
				}	
				/* Refresh Algae */
				int count = Params.refresh_algae_count;
				while(count != 0){
					critter = new Algae();
					critter.energy = Params.start_energy;
					/* Assign a random position */
					critter.x_coord = getRandomInt(Params.world_width - 1);
					critter.y_coord = getRandomInt(Params.world_height - 1);
					/* Add this guy to the population */
					population.add(critter);
				}
					
					
				/* Add any new offspring to collection */
				for(int j = 0; j < babies.size(); j++){
					population.add(babies.get(j));
					babies.remove(j);
				}
				
				/* Deduct rest energy */
				for(int m = 0; m < population.size(); m++){
					population.get(m).energy -= Params.rest_energy_cost;
				}
				
				/* Clear all dead critters */
				for(int k = 0; k < population.size(); k++){
					if(population.get(k).energy <= 0){
						population.remove(k);
					}
				}
				
				/* Set all critters move field back to false */
				for(int l = 0; l < population.size(); l++){
					population.get(l).hasMoved = false;
				}
			}
		}
	
	
	@SuppressWarnings("unchecked")
	private static void constructWorld(){
		/* Construct a large 2D world made up of arraylists at each coordinate */
		for(int row = 0; row < Params.world_height; row++){
			for(int col = 0; col < Params.world_width; col++){
				ourworld[row][col] = new ArrayList<Critter>();
			}
		}
		/* Populate this world with the little critters */
		int i = 0;
		while(i < population.size()){
			ourworld[population.get(i).y_coord][population.get(i).x_coord].add(population.get(i));
			i++;
		}
	}
	
	public static void displayWorld() {
		createBorder();
		constructWorld();
		for(int row = 1; row < Params.world_height + 1; row++){
			for (int col = 0; col < Params.world_width + 2; col++){			
				if(col == 0){
					System.out.print("|");
				}
				else if(col == Params.world_width + 1){
					System.out.println("|");
				}
				/* If population array is empty means no critter to print */
				else if (ourworld[row - 1][col - 1].size() == 0){
					System.out.print(" ");
				}
				/* Critter availabe so print the first critter in array */
				else{
					System.out.print(ourworld[row - 1][col - 1].get(0).toString());
				}
			}	
		}
		createBorder();
	}
	
	
	private static void createBorder() {
		for(int i = 0; i < Params.world_width + 2; i++){
			if(i == 0){
				System.out.print("+");
			}
			else if(i == Params.world_width + 1){
				System.out.println("+");
			}
			else{
				System.out.print("-");
			}
		}
	}
}
