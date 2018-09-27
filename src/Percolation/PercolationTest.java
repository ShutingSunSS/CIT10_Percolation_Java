package Percolation;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
/*
 * Expect ground to return an array containing only 0s and 1s.
 * Expect seep(ground, row) to fill all reachable spaces in row+1 with water.
 * Expect myPercolate to return true if, after seeping, any location in the bottom row contains water.
 * Expect findProbability to return a value that is not near 0.50; 
 * it should return a value of p given to ground that will cause myPercolate to return true half the time, 
 * and false the other half.*/

public class PercolationTest {
	Percolation myPercolate;
	
	@Before
	public void setUp(){
		myPercolate = new Percolation();  
	}

	@Test
	public void groundTest(){
		int n = 100;
		double p = 0.5;
		double eps = 0.05;
		
		int[][] ground1 = myPercolate.ground(n, p);
		double count = 0;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				//Expect ground to return an array containing only 0s and 1s.
				assertTrue(ground1[i][j] == 1 || ground1[i][j] == 0);
				if (ground1[i][j] == 1)
					count++;
			}
		}
		//Determine whether the proportion of sand grains is reasonably close to p.
		double small = Math.abs(count / (n*n) - p);
		assertTrue(small < eps);
	}
	
	@Test
	//Expect seep(ground, row) to fill all reachable spaces in row+1 with water.
	public void seepTest(){
		int[][] myArray = {{0, 1, 1}, {2, 1, 0}, {0, 0, 0}};
		myPercolate.seep(myArray, 1);
		int[][] myArray1 = {{0, 1, 1}, {2, 1, 0}, {2, 2, 2}};
		// We need deepEquals to compare two-dimensional arrays.
		assertTrue(Arrays.deepEquals(myArray, myArray1));
		
		int[][] myArray2 = {{2, 1, 1}, {0, 0, 1}, {0, 0, 0}};
		myPercolate.seep(myArray2, 0);
		int[][] myArray3 = {{2, 1, 1}, {2, 2, 1}, {0, 0, 0}};
		assertTrue(Arrays.deepEquals(myArray2, myArray3));
		
		int[][] myArray4 = {{2, 1, 1}, {0, 0, 1}, {0, 0, 0}};
		myPercolate.seep(myArray4, 2);
		int[][] myArray5 = {{2, 1, 1}, {0, 0, 1}, {0, 0, 0}};
		assertTrue(Arrays.deepEquals(myArray4, myArray5));
	}
	
	@Test
	//Expect myPercolate to return true if, after seeping, any location in the bottom row contains water.
	public void PercolateTest(){
		int[][] myArray = {{0, 1, 1}, {1, 1, 1}, {0, 0, 0}};
		assertFalse(myPercolate.percolate(myArray));
		int[][] myArray1 = {{0, 0, 1}, {1, 0, 1}, {0, 0, 1}};
		assertTrue(myPercolate.percolate(myArray1));
		int[][] myArray3 = {{0, 1, 1}, {1, 0, 1}, {0, 1, 1}};
		assertFalse(myPercolate.percolate(myArray3));
	}
/*	
	@Test
	public void findProbabilityTest(){
		int n = 50;
		double prob = myPercolate.findProbability(n);
		System.out.println(prob);
		int[][] ground1 = myPercolate.ground(n, prob);
		double count = 0;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (ground1[i][j] == 1)
					count++;
			}
		}
		System.out.println(count);
	}
*/
}
