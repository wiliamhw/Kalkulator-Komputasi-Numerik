package com.komnum.calc;

public class R_Polinomial extends Regression {
	private double[][] pow_x;
	private double[][] pow_cd;
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
	
	protected void setD2() {
		// input a1, a2, a3
	}
	
	public void calculate() {
		setCd();
		setC2();
		setSum();
		setAvg();
	}
	
	public void printResult() {
		
		// Tabel
		System.out.printf("%4s %12s %12s ", "n", "Xi", "Yi");
		for (int i = 0; i < m + 1; i++) {
			System.out.printf("%12s ", "Xi^" + (i + 2));
		}
		for (int i = 0; i < m; i++) {
			System.out.printf("%12s ", "Xi^(" + (i + 1) + ").Yi");
		}
		System.out.println();
		
		for (int i = 0; i < n; i++) {
			System.out.printf("%4d %12.4f %12.4f ", 
					i + 1, xy_tb[i][0], xy_tb[i][1]);
			
			for (int j = 0; j < m + 1; j++) {
				System.out.printf("%12.4f ", pow_x[i][j]);
			}
			for (int j = 0; j < m; j++) {
				System.out.printf("%12.4f ", pow_cd[i][j]);
			}
			System.out.println();
		}
		
		// Sum
		System.out.printf("%4s %12.4f %12.4f ", "\u03A3", sum_x, sum_y);
		for (int i = 0; i < m + 1; i++) {
			System.out.printf("%12.4f ", sum_powX[i]);
		}
		for (int i = 0; i < m; i++) {
			System.out.printf("%12.4f ", sum_powCd[i]);
		}
		System.out.printf("\n\n");
		
		
		// Hasil persamaan
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
		
		// Format hasil
		System.out.printf("G(x) = ");
		for (int i = 0; i < m + 1; i++) {
			System.out.printf("%3s", "a" + i);
			
			if (i != 0) {
				System.out.printf(".x^(" + i + ")");
			}
			if (i < m) {
				System.out.printf(" + ");
			}
		}
		System.out.println("\n\nCari ax dengan eliminasi Gaussian");
	}
}

/* Contoh Input
6
2
--------
0	2.1
1	7.7
2	13.6
3	27.2
4	40.9
5	61.1
*/
