package com.komnum.calc;

public class Lagrange extends Interpolasi {
	private double[] sum;

	public Lagrange(int n, double x_target) {
		super(n, x_target);
		sum = new double[n + 1];
	}
	
	public void calculate() {
		sum[0] = 0;
		
		for (int i = 0; i < n; i++) {
			double product = xy_tb[i][1];
			
			for (int j = 0; j < n; j++) {
				if (i != j) {
					product *= (x_target - xy_tb[j][0]) /
							(xy_tb[i][0] - xy_tb[j][0]);
				}
			}
			sum[i + 1] = sum[i] + product;
		}
	}
	
	public void printResult() {
		System.out.println("Interpolasi Lagrange pada x = " + super.x_target);
		
		System.out.printf("%-5s = ", "Hasil");
		System.out.printf("%.4f", sum[1]);
		
		for (int i = 2; i < n + 1; i++) {
			System.out.printf(" + %.4f", sum[i] - sum[i - 1]);
		}
		System.out.printf(" = %.3f\n", sum[n]);
	}
}

/* Contoh input
4
656
654	2.8156
658	2.8182
659	2.8189
661	2.8202
 */
