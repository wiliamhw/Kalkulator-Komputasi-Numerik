package com.komnum.calc;

public class Trapezoid extends Integrasi {
	// equispaced
	private double K;
	private double d_X2;
	private double Fin;
	private double Fi0;
	private int type;

	public Trapezoid(int n, double a, double b, int dik, int type) {
		super(n, a, b, dik);
		this.type = type;
		res = new double[n + 1];
	}
	
	public void calculate() {
		if (type == 1) {
			double tmp = 0;
			res[0] = xy_tb[0][1];
			sum = res[0];
			
			for (int i = 1; i < n; i++) {
				res[i] = xy_tb[i][1];
				tmp += res[i];
			}
			sum += tmp * 2;
			res[n] = xy_tb[n][1];
			sum += res[n];
			I = d_X * sum / 2;
		}
		else {
			for (int i = 1; i <= n; i++) {
				res[i - 1] = (xy_tb[i][0] - xy_tb[i - 1][0]) *
						(xy_tb[i][1] + xy_tb[i - 1][1]) / 2;
				sum += res[i - 1];
			}
			I = sum;
		}
	}
	
	private void getK() {
		this.d_X2 = Math.pow(d_X, 2);
		this.Fi0 = F_integral(xy_tb[0][0]);
		this.Fin = F_integral(xy_tb[n][0]);
		
		this.K = d_X2 / 12 * (Fin - Fi0);
	}
	
	public void printResult() {
		String tmp = (type == 1) ? "equispaced" : "non-equispaced";
		System.out.printf("Intergrasi trapezoida %s dengan %d pias:\n", tmp, n);
		
		System.out.printf("I = ");
		if (type == 1) System.out.printf("(%.4f)/2 . (", d_X);
		System.out.printf("%8.4f", res[0]);
		for (int i = 1; i < n; i++) {
			if (i == 1) {
				System.out.printf(" + 2(%8.4f", res[i]);
			}
			else {
				System.out.printf(" + %8.4f", res[i]);
			}
			if (i == n - 1) {
				System.out.printf(")");
			}
		}
		if (type == 1) System.out.printf("+ %8.4f)", res[n]);
		System.out.printf("\nI = %8.4f\n", I);
		
		
		if (dik == 1 && type == 1) {
			System.out.printf("Va = %.4f --> ", Va);
			System.out.printf("Er = %.4f%c\n", super.getEr(Va, I), '%');
		
			System.out.printf("\nKoreksi Ujung:\n");
			getK();
			System.out.printf("K = %.4f / 12 [%.4f - %.4f]\n", d_X2, Fin, Fi0);
			System.out.printf("K = %.4f\n", K);
			System.out.printf("Ik = %.4f\n", I - K);
			System.out.printf("Erk = %.4f%c\n", super.getEr(Va, I - K), '%');
		}
	}
}

// if insert by table --> no koreksi ujung + no Va

/*
Dik: Fungsi
1
1
4
4
0

Dik: Tabel x dan F(x)
1
2
7
7
1
------------
1	1.8287
2	5.6575
3	11.4862
4	19.3149
5	29.1437
6	40.9724
7	54.8011
*/
