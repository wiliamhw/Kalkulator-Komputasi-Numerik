package com.komnum.calc;

public class Trapezoid extends Integrasi {
	// equispaced
	private double K;
	private double d_X2;
	private double Fin;
	private double Fi0;

	public Trapezoid(int n, double a, double b, int dik) {
		super(n, a, b, dik);
	}
	
	public void calculate() {
		sum = xy_tb[0][1];
		
		for (int i = 1; i < n; i++) {
			sum += 2 * xy_tb[i][1];
		}
		sum += xy_tb[n][1];
		I = d_X * sum / 2;
	}
	
	private void getK() {
		this.d_X2 = Math.pow(d_X, 2);
		this.Fi0 = F_integral(xy_tb[0][0]);
		this.Fin = F_integral(xy_tb[n][0]);
		
		this.K = d_X2 / 12 * (Fin - Fi0);
	}
	
	public void printResult() {
		System.out.printf("Intergrasi trapezoida equispaced dengan %d pias:\n", n);
		System.out.printf("I = (%.4f) . [%.4f / 2]\n", d_X, sum);
		System.out.printf("I = %.4f\n", I);
		
		if (dik == 1) {
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
4
4
0

Dik: Tabel x dan F(x)
7
7
1
1	1.8287
2	5.6575
3	11.4862
4	19.3149
5	29.1437
6	40.9724
7	54.8011
 */
