// Author: Karim Reghay

import java.util.HashMap;

public class Hanoi {
	
	// Set klass to whatever the class name is (for usage message)
	private static String klass = new Hanoi().getClass().getSimpleName();
	
	// Initialize TOWER_HEIGHT to a default value in case it's not user-specified
	private static int TOWER_HEIGHT = 3;
	
	private static Integer stack_a[];
	private static Integer stack_b[];
	private static Integer stack_c[];
	
	static private String direction = "AC";
	
	private static HashMap<Integer, String> stack_map = new HashMap<Integer, String>();
	private static HashMap<String, Integer[]> start_map = new HashMap<String, Integer[]>();
	private static HashMap<String, Integer[]> dest_map = new HashMap<String, Integer[]>();
	private static HashMap<String, Integer[]> temp_map = new HashMap<String, Integer[]>();
	
	private static int counter = 0;
	
	public static void main (String args[]){
		
		// Check if the usage is correct and if not print usage message
		if (args.length % 2 == 1) {
			usageMessage();
		}
		else if (args.length > 0) {
			for(int i = 0; i < args.length; i += 2) {
				switch (args[i].charAt(1)) {
					case 'n':
						try {
							TOWER_HEIGHT = Integer.parseInt(args[i + 1]);
						}
						catch (Exception e) {
							usageMessage();
						}
						break;
					case 'm':
						direction = args[i + 1].toUpperCase();
						break;
					default:
						usageMessage();
				}
			}
		}	//end check on usage	
		
		createStacksAndMaps();
		
		// a last check regarding usage
		if(!start_map.containsKey(direction)) {
			usageMessage();
		}	
		
		// Let's see how long this takes?
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < start_map.get(direction).length; i++) {
			start_map.get(direction)[i] = TOWER_HEIGHT - i;
		}
		
		System.out.println("\nThe starting position is given as:\n");
		printStacks();
		
		// At long last!  One who knows the ways of the Tower!
		towerSolve(TOWER_HEIGHT, start_map.get(direction), dest_map.get(direction), temp_map.get(direction));
		
		System.out.println("Moved " + TOWER_HEIGHT + " disks in " + counter + " moves!");
		System.out.println("Completed in: " + ((double) ((System.currentTimeMillis() - start)) / 1000) + " seconds");
	}
	
	private static void usageMessage() {
		System.out.println("Usage: \n\t\t java " + klass + " [-n DISKS] " + "[-m DIRECTION]" + "\n\n");
		
		System.out.println("Options:\n");
		System.out.println("-n\tIf specified, Hanoi will run with the given number");
		System.out.println("\t" + "of DISKS from option -n. Hanoi defaults to running with 3 disks.\n");
		
		System.out.println("-m" + "\t" + "If specified, Hanoi will run with DIRECTION from option ");
		System.out.println("\t" + "-n where DIRECTION is given as a two letter permutation of the ");
		System.out.println("\t" + "members of set S were S = {A, B, C} - the first letter is the");
		System.out.println("\t" + "starting stack and the second is the destination stack.");
		System.out.println("\tHanoi defaults to running with DIRECTION = AC\n\n");
		System.out.println("Example:\tjava Hanoi -n 8 -m BC");
		System.exit(1);
	}

	public static void towerSolve(int n, Integer[] origin, Integer[] dest, Integer[] temp) {
		if (n == 1) 
		{
			System.out.println();
			push(dest, pop(origin));
			
			System.out.println("Moving disk from " + stack_map.get(origin.hashCode()) + " to " + stack_map.get(dest.hashCode()) + "\n");
			
			counter += 1;
			printStacks();
		}
		else 
		{
			towerSolve(n-1, origin, temp, dest);
			towerSolve(1, origin, dest, temp);
			towerSolve(n-1, temp, dest, origin);
		}
	}
	
	public static void push(Integer[] n, int val) {
		for(int i = 0; i < n.length; i++) {
			if(n[i] == null) {
				n[i] = new Integer(val);
				return;
			}
		}
	}
	
	public static int pop(Integer[] n) {
		for(int i = n.length - 1; i >= 0; i--) {
			if (n[i] != null) {
				int temp = n[i];
				n[i] = null;
				return temp;
			}
		}
		return -1;		
	}
	
	public static String diskToString(Integer n) {
		String ans = (n == null) ? " " : String.valueOf(n);
		return ans;
	}
	
	public static void printStacks() {
		for(int i = TOWER_HEIGHT - 1; i >= 0; i--) {
			System.out.println("\t\t" + diskToString(stack_a[i]) + "\t\t" + diskToString(stack_b[i]) + "\t\t" + diskToString(stack_c[i]));
		}
		System.out.println("\n-----------Stack A-----------Stack B-----------Stack C-----------\n");
	}
	
	public static void createStacksAndMaps() {
		stack_a = new Integer[TOWER_HEIGHT];
		stack_b = new Integer[TOWER_HEIGHT];
		stack_c = new Integer[TOWER_HEIGHT];
		
		stack_map.put(stack_a.hashCode(), "stack A");
		stack_map.put(stack_b.hashCode(), "stack B");
		stack_map.put(stack_c.hashCode(), "stack C");
		
		start_map.put("AB", stack_a);
		start_map.put("AC", stack_a);
		start_map.put("BA", stack_b);
		start_map.put("BC", stack_b);
		start_map.put("CA", stack_c);
		start_map.put("CB", stack_c);
		
		dest_map.put("AB", stack_b);
		dest_map.put("AC", stack_c);
		dest_map.put("BA", stack_a);
		dest_map.put("BC", stack_c);
		dest_map.put("CA", stack_a);
		dest_map.put("CB", stack_b);
		
		temp_map.put("AB", stack_c);
		temp_map.put("AC", stack_b);
		temp_map.put("BA", stack_c);
		temp_map.put("BC", stack_a);
		temp_map.put("CA", stack_b);
		temp_map.put("CB", stack_a);
	}
}