package Percolation;
import java.util.Random;

public class Percolation {
	Random random = new Random();
	
	public static void main(String args[]){
		//new Percolation().run();
		test1();
	}
	
	void run() {
		System.out.println("For 50x50, the probability is: " + findProbability(50));
		System.out.println("For 100x100, the probability is: " + findProbability(100));
    	System.out.println("For 200x200, the probability is: " + findProbability(200));
    }
	
	
	int[][] ground(int n, double p){
		int[][] res = new int[n][n];
		double sandProb;
		for (int i = 0; i < n ; i++){
			for (int j = 0; j < n; j++){
				sandProb = random.nextDouble();
				if (sandProb < p){
					res[i][j] = 1;
				}
				else{
					res[i][j] = 0;
				}
			}
		}
		return res;	
	}
	
	void seep(int[][] ground, int row){
		// Reach the last row, return
		if (row == ground.length - 1)
			return;
		for (int col = 0; col < ground[row].length; col++){
			if (ground[row][col] == 2 && ground[row + 1][col] == 0){
				ground[row + 1][col] = 2;
				int l = col - 1;
				int r = col + 1;
				// Expand to left spaces.
				while (l >= 0 && ground[row + 1][l] == 0){
					ground[row + 1][l] = 2;
					l--;
				}
				// Expand to right spaces.
				while (r < ground[row + 1].length && ground[row + 1][r] == 0){
					ground[row + 1][r] = 2;
					r++;
				}
			}
		}
	}
	
	boolean percolate(int[][] ground){
		//First line.
	    for (int col = 0; col < ground[0].length; col++){
	    	if (ground[0][col] == 0)
	    		ground[0][col] = 2;
	    }
	    int lastLine = ground.length - 1;
	    //Following lines.
		for (int row = 0; row < ground.length; row++){
		      seep(ground, row);
	    }
		//Returns true if, after water has "seeped" as far as it can.
		//Water has reached the bottom row.
		for (int col = 0; col < ground[lastLine].length; col++){
			if (ground[lastLine][col] == 2)
				return true;
		}
		return false;
	}
	
	/* For an n by n array, 
	 * determines the packing probability p 
	 * that causes the array to have a 50% probability of water 
	 * seeping all the way to the bottom.
	 * */
	 /* Expect findProbability to return a value that is not near 0.50; 
	 * it should return a value of p given to ground that will cause myPercolate to return true half the time, 
	 * and false the other half.*/
	double findProbability(int n){
		double prob = 0.5; // Start with some reasonable value of probability.
		double delta = 0.05;
		
		double countFormer = 0;
		double count = 0;
		
		int iterateN = 500; // Iterate 500 times.
		double eps = 0.005;
		
		while (Math.abs(count/iterateN - 0.5) > eps){
			countFormer = count;
			//System.out.println("countFormer, count " + countFormer +" " + count);
			count = 0;
			for (int i = 0; i < iterateN; i++){
				if (percolate(ground(n, prob))){
					count++;
				}
			}
			if (count/iterateN < 0.5){
				prob = prob - delta;
			}
			else if (count/iterateN > 0.5){
				prob =  prob + delta;
			}
			else
				return prob;
			// Converge!
			double lastTime = countFormer/iterateN;
			double thisTime = count/iterateN;
			if ( ((lastTime > 0.5) && (thisTime < 0.5)) || ((lastTime < 0.5) && (thisTime > 0.5)) )
			delta = delta / 2;
		  }
			return prob;
		}
	
	public static void test1() {                   
		 long startTime=System.currentTimeMillis();//Begin time       
		 Percolation myTime=new Percolation();       
		 myTime.run();        
		 long endTime=System.currentTimeMillis(); //End time 
		 System.out.println();
		 System.out.println("Run time of finding the three results： "+ (endTime-startTime) + " ms.");            
		 }      	 
}
	

