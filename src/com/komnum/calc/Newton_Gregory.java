package com.komnum.calc;

public abstract class Newton_Gregory extends Interpolasi {
	protected double[][] diff_tb;
	protected double[] sum;
	protected double s;
	
	public Newton_Gregory(int n, double x_target) {
		super(n, x_target);
	}
	
	protected double fact(int n) {
		int f = 1; 
	    for (int i = 2; i <= n; i++) 
	        f *= i; 
	    return f; 
	}
	
	public double getS() {
		return s;
	}

	public void setS(double s) {
		this.s = s;
	}

	public void printResult() {
		System.out.printf("%6.1s %8.4s %8.5s", 
				"x", "f(x)", "\u0394" + "f(x)");
		for (int i = 2; i < n; i++) {
			System.out.printf("%8.6s", "\u0394" + i + "f(x)");
		}
		System.out.println();
		
		getDiff();
		System.out.printf("s = %.4f\n", s);
		
		System.out.printf("%-6s = ", "Hasil");
		System.out.printf("%.4f", sum[0]);
		
		for (int i = 1; i < n; i++) {
			System.out.printf(" + %.4f", sum[i] - sum[i - 1]);
		}
		System.out.printf(" = %.3f\n", sum[n-1]);
	}
	
	protected abstract void setDiff();
	protected abstract void getDiff();
	public abstract void calculate(); // getSum
}
