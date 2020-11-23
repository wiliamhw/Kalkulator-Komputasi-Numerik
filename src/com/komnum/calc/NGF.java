package com.komnum.calc;

public class NGF extends Newton_Gregory {
	
	public NGF(int n, double x_target) {
		super(n, x_target);
		s = (x_target - xy_tb[0][0]) / (xy_tb[1][0] - xy_tb[0][0]);
		sum = new double[n];
		setDiff();
	}

	protected void setDiff() {
		diff_tb = new double[n][n];
		
		for (int i = 0; i < n; i++) {
			diff_tb[i][0] = xy_tb[i][1];
		}
		
		// Calculate diff
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n - i; j++) {
				diff_tb[j][i] = diff_tb[j + 1][i - 1] - diff_tb[j][i - 1];
			}
		}
	}
	
	protected void getDiff() {
		for (int i = 0; i < n; i++) {
			System.out.printf("%8.4f", xy_tb[i][0]);
			
			for (int j = 0; j < n - i; j++) {
				System.out.printf("%8.4f", diff_tb[i][j]);
			}
			System.out.println();
		}
	}

	private double u_cal(double u, int n) {
		double temp = u; 
	    for (int i = 1; i < n; i++) 
	        temp = temp * (u - i); 
	    return temp; 
	}
	
	// Get sum
	public void calculate() {
		sum[0] = diff_tb[0][0];
		
		for (int i = 1; i < n; i++) {
			sum[i] = sum[i - 1] + (u_cal(s, i) * diff_tb[0][i]) / fact(i);
		}
	}
	
	public void printResult() {
		System.out.println("Interpolasi NGF pada x = " + super.x_target);
		super.printResult();
	}
}

/* Test input
7
1.03
----------
1	1.449
1.3	2.060
1.6	2.645
1.9	3.216
2.2	3.779
2.5	4.338
2.8	4.898
 */
