package com.komnum.calc;

public class Polinomial extends Interpolasi {
	private double[] koef;
	private double[] y_target;
	
	public Polinomial(int n, double x_target) {
		super(n, x_target);
		
		y_target = new double[super.n];
		koef = new double[super.n];
		for (int i = 0; i < super.n; i++) {
			koef[i] = super.xy_tb[i][1];
		}
	}

	public void calculate() {
		// Get coef
		for (int i = 1; i < super.n; i++) {
			for (int j = n - 1; j > i - 1; j--) {
				koef[j] = (double) (koef[j] - koef[j-1]) / (xy_tb[j][0]- xy_tb[j-i][0]);
			}
		}
		
		// Get y_target
		double xterm = 1;
		y_target[0] = koef[0];
		
		for (int i = 1; i < n; i++) {
			xterm *= (x_target - xy_tb[i - 1][0]);
			y_target[i] = y_target[i - 1] + koef[i] * xterm;
		}
	}

	public void printResult() {
		System.out.println("Interpolasi polinomial pada x = " + super.x_target);
		
		for (int i = 0; i < n; i++) {
			System.out.printf("  b%-1d = %-8.4f\n", i, koef[i]);
		}
		
		System.out.printf("%-6s = ", "Hasil");
		System.out.printf("%.4f", y_target[0]);
		
		for (int i = 1; i < n; i++) {
			System.out.printf(" + %.4f", y_target[i] - y_target[i - 1]);
		}
		
		System.out.printf(" = %.3f\n", y_target[n-1]);
		
//		System.out.printf("%-6s : %.2f%c\n", "Er", getEr(Va, y_target[n-1]), '%');
	}
}

/* Test Input
3
2
0.69314718
--------------
1	0
4	1.3862944
6	1.7917595
 */
