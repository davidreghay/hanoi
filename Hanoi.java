// Author: Karim Reghay

import java.util.Arrays;
public class Hanoi {
	
	public static String klass = new Hanoi().getClass().getSimpleName();
	
	public static int TOWER_HEIGHT = 3;
	
	static int m[];
	static int p[];
	static int q[];
	
	static int counter = 0;
	
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
		
		m = new int[TOWER_HEIGHT];
		p = new int[TOWER_HEIGHT];
		q = new int[TOWER_HEIGHT];

		for (int i = 0; i < m.length; i++) {
			m[i] = TOWER_HEIGHT - i;
		}
		
		System.out.println(Arrays.toString(m));
		System.out.println(Arrays.toString(p));
		System.out.println(Arrays.toString(q));
		towerSolve(TOWER_HEIGHT, m, p, q);
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
			
			counter += 1;
			
			System.out.println(Arrays.toString(m));
			System.out.println(Arrays.toString(p));
			System.out.println(Arrays.toString(q));
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