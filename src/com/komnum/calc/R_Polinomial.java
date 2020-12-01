package com.komnum.calc;

import java.util.Scanner;

public class R_Polinomial extends Regression {
	private double[][] pow_x;
	private double[][] pow_cd;
	private double[] a;
	private int m;
	
	private double[] sum_powX;
	private double[] sum_powCd;

	public R_Polinomial(int n, int m) {
		super(n, m);
		this.m = m;
		
		cd = c2 = null;
		pow_x = new double[n][m + 1];
		pow_cd = new double[n][m];
		sum_powX = new double[m + 1];
		a = new double[m + 1];
		sum_powCd = new double[m];
	}
	
	protected void setCd() {
		for (int i = 0; i < n; i++) {
			pow_cd[i][0] = xy_tb[i][0] * xy_tb[i][1];
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 0; j < n; j++) {
				pow_cd[j][i] = pow_cd[j][i - 1] * xy_tb[j][0];
			}
		}
	}

	protected void setC2() {
		for (int i = 0; i < n; i++) {
			pow_x[i][0] = Math.pow(xy_tb[i][0], 2);
		}
		
		for (int i = 1; i < m + 1; i++) {
			for (int j = 0; j < n; j++) {
				pow_x[j][i] = pow_x[j][i - 1] * xy_tb[j][0];
			}
		}
	}
	
	protected void setSum() {
		for (int i = 0; i < n; i++) {
			sum_y += xy_tb[i][1];
			sum_x += xy_tb[i][0];
			
			for (int j = 0; j < m + 1; j++) {
				sum_powX[j] += pow_x[i][j];
			}
			for (int j = 0; j < m; j++) {
				sum_powCd[j] += pow_cd[i][j];
			}
		}
	}
	
	public void setD2() {
		D2[0] = Math.pow(xy_tb[0][1] - G(xy_tb[0][0]), 2);
		for (int i = 1; i < n; i++) {
			D2[i] = D2[i - 1] + Math.pow(xy_tb[i][1] - G(xy_tb[i][0]), 2);
		}
	}
	
	private double G(double x) {
		double temp = 0;
		for (int i = 0; i < m + 1; i++) {
			temp += a[i] * Math.pow(x, i);
		}
		return temp;
	}
	
	public void calculate() {
		setCd();
		setC2();
		setSum();
		setAvg();
		setDt2();
		
		printMatrix();
		setKoef();
		setD2();
		setR2();
	}
	
	private void printMatrix () {
		// Hasil persamaan matriks
		System.out.println("Berdasarkan tabel, maka diperoleh:");
		
		System.out.printf("|%12d %12.4f ", n, sum_x);
		for (int i = 0; i < m - 1; i++) {
			System.out.printf("%12.4f ", sum_powX[i]);
		}
		System.out.printf("||%3s| = |%12.4f|\n", "a0", sum_y);
		
		for (int i = 1; i < m + 1; i++) {
			System.out.printf("|");
			for (int j = 0; j < m + 1; j++) {
				if (i == 1 && j == 0) {
					System.out.printf("%12.4f ", sum_x);
				}
				else {
					System.out.printf("%12.4f ", sum_powX[j + i - 2]);
				}
			}
			System.out.printf("||%3s| = |%12.4f|\n", "a" + i, sum_powCd[i - 1]);
		}
	}
	
	private void setKoef() {
		System.out.println("\nCari ax dengan metode Gaussian");
		Scanner scan = new Scanner(System.in);
		
		for (int i = 0; i < m + 1; i++) {
			System.out.printf("%-6s", "a" + i + " = ");
			a[i] = scan.nextDouble();
		}
		System.out.println();
	}
	
	public void printResult() {

		// Header
		System.out.println("Tabel:");
		System.out.printf("%4s %12s %12s ", "n", "Xi", "Yi");
		for (int i = 0; i < m + 1; i++) {
			System.out.printf("%12s ", "Xi^" + (i + 2));
		}
		for (int i = 0; i < m; i++) {
			System.out.printf("%12s ", "Xi^(" + (i + 1) + ").Yi");
		}
		System.out.printf("%12s %12s\n", "D2", "Dt2");
		
		
		// Table
		double tmp_D2 = 0;
		double tmp_Dt2 = 0;
		for (int i = 0; i < n; i++) {
			if (i > 0) {
				tmp_D2 = D2[i - 1];
				tmp_Dt2 = Dt2[i - 1];
			}
			System.out.printf("%4d %12.4f %12.4f ", 
					i + 1, xy_tb[i][0], xy_tb[i][1]);
			
			for (int j = 0; j < m + 1; j++) {
				System.out.printf("%12.4f ", pow_x[i][j]);
			}
			for (int j = 0; j < m; j++) {
				System.out.printf("%12.4f ", pow_cd[i][j]);
			}
			System.out.printf("%12.4f %12.4f\n", D2[i] - tmp_D2, Dt2[i] - tmp_Dt2);
		}
		
		// Sum
		System.out.printf("%4s %12.3f %12.3f ", "\u03A3", sum_x, sum_y);
		for (int i = 0; i < m + 1; i++) {
			System.out.printf("%12.3f ", sum_powX[i]);
		}
		for (int i = 0; i < m; i++) {
			System.out.printf("%12.3f ", sum_powCd[i]);
		}
		System.out.printf("%12.3f %12.3f\n\n", D2[n - 1], Dt2[n - 1]);
		
		// Format hasil
		System.out.printf("Hasil = G(x) = ");
		for (int i = 0; i < m + 1; i++) {
			System.out.printf("%.3f", a[i]);
			
			if (i != 0) {
				System.out.printf(".x^(" + i + ")");
			}
			if (i < m) {
				System.out.printf(" + ");
			}
		}
		System.out.printf("\nym   = %.3f\n", ym);
		System.out.printf("\nr^(2) = %.3f\n", r2);
		System.out.printf("r     = %.3f\n", Math.sqrt(Math.abs(r2)));
	}
}

/* Contoh Input
9
2
--------
1	1
2	1.5
3	2
4	3
5	4
6	5
7	8
8	10
9	13
--------
1.488
-0.452
0.191
*/
