// Author: Karim Reghay

import java.util.Arrays;
import java.util.HashMap;

public class Hanoi {
	
	// Set klass to whatever the class name is (for usage message)
	private static String klass = new Hanoi().getClass().getSimpleName();
	
	// Initialize TOWER_HEIGHT to a default value in case it's not user-specified
	private static int TOWER_HEIGHT = 3;
	
	private static int stack_a[];
	private static int stack_b[];
	private static int stack_c[];
	
	private static HashMap<Integer, String> stack_map = new HashMap<Integer, String>();
	
	private static int counter = 0;
	
	public static void main (String args[]){
		
		if (args.length > 2 || args.length == 1) {
			usageMessage();
		}
		else if (args.length == 2 && (!args[0].equals("-n"))) {
			usageMessage();
		}
		else if (args.length == 2){
			try {
				TOWER_HEIGHT = Integer.parseInt(args[1]);
			}
			catch (Exception e) {
				usageMessage();
			}
		}
		
		stack_a = new int[TOWER_HEIGHT];
		stack_b = new int[TOWER_HEIGHT];
		stack_c = new int[TOWER_HEIGHT];
		
		stack_map.put(stack_a.hashCode(), "stack A");
		stack_map.put(stack_b.hashCode(), "stack B");
		stack_map.put(stack_c.hashCode(), "stack C");
		
		for (int i = 0; i < stack_a.length; i++) {
			stack_a[i] = TOWER_HEIGHT - i;
		}
		
		System.out.println(Arrays.toString(stack_a));
		System.out.println(Arrays.toString(stack_b));
		System.out.println(Arrays.toString(stack_c));
		towerSolve(TOWER_HEIGHT, stack_a, stack_b, stack_c);
		System.out.println("Moved " + TOWER_HEIGHT + " disks in " + counter + " moves!");
	}
	
	private static void usageMessage() {
		System.out.println("Usage: \n\t\t java " + klass + " [-n DISKS]" + "\n\n");
		System.out.println("If specified, Hanoi will run with the given number of DISKS from option -n");
		System.exit(1);
	}

	public static void towerSolve(int n, int[] origin, int[] dest, int[] temp) {
		if (n == 1) 
		{
			System.out.println();
			push(dest, pop(origin));
			
			System.out.println("Moving disk from " + stack_map.get(origin.hashCode()) + " to " + stack_map.get(dest.hashCode()));
			
			counter += 1;
			
			System.out.println(Arrays.toString(stack_a));
			System.out.println(Arrays.toString(stack_b));
			System.out.println(Arrays.toString(stack_c));
			System.out.println();
		}
		else 
		{
			towerSolve(n-1, origin, temp, dest);
			towerSolve(1, origin, dest, temp);
			towerSolve(n-1, temp, dest, origin);
		}
	}
	
	public static void push(int[] n, int val) {
		for(int i = 0; i < n.length; i++) {
			if(n[i] == 0) {
				n[i] = val;
				return;
			}
		}
	}
	
	public static int pop(int[] n) {
		for(int i = n.length - 1; i >= 0; i--) {
			if (n[i] != 0) {
				int temp = n[i];
				n[i] = 0;
				return temp;
			}
		}
		return -1;		
	}
}