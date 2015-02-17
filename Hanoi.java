// Author: Karim Reghay

import java.util.Arrays;
public class Hanoi {
	
	public static final int TOWER_HEIGHT = 5;
	
	static int m[] = {5, 4, 3, 2, 1};
	static int p[] = new int[TOWER_HEIGHT];
	static int q[] = new int[TOWER_HEIGHT];
	
	public static void main (String args[]){
		System.out.println(Arrays.toString(m));
		System.out.println(Arrays.toString(p));
		System.out.println(Arrays.toString(q));
		towerSolve(5, m, p, q);
		
	}
	
	public static void towerSolve(int n, int[] origin, int[] dest, int[] temp) {
		if (n == 1) 
		{
			System.out.println();
			push(dest, pop(origin));
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