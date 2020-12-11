package com.komnum.calc;

import java.util.Scanner;

public class Kuadratur extends Integrasi {
	private double dxd;

	protected void setD_X() {
		return;
	}
	
	protected void setXY() {
		xy_tb = new double[n + 1][2];
		c = new double[n + 1];
		oldX = new double[n + 1];
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Masukan nilai c dan xd");
		System.out.printf("%-8c %-8s\n", 'c', "xd");
		
		for (int i = 0; i < n + 1; i++) {
			c[i] = scanner.nextDouble();
			oldX[i] = scanner.nextDouble();
			xy_tb[i][0] = convertXi(oldX[i]);
			xy_tb[i][1] = F_integral(xy_tb[i][0]);
		}
	}
	
	protected void setVa() {
		System.out.printf("F'b = %.4f\n", F_integral(b));
		System.out.printf("F'a = %.4f\n", F_integral(a));
		this.Va = F_integral(b) - F_integral(a);
	}
	
	public Kuadratur(int n, double a, double b, int dik) {
		super(n, a, b, dik);
		if (n + 1 < 2 || n + 1 > 6) throw new IllegalArgumentException ("n minimal bernilai 2 dan maksimal bernilai 6");
		setdxd();
	}
	
	private double convertXi(double x) {
		return ((b + a) + (b - a) * x) / 2;
	}
	
	private void setdxd() {
		dxd =  (b - a) / 2;
	}
	
	public void calculate() {
		res = new double[n + 1];
		for (int i = 0; i < n + 1; i++) {
			res[i] = c[i] * dxd * xy_tb[i][1];
			sum += res[i];
		}
		I = sum;
	}
	
	
	public void printResult() {
		System.out.printf("Intergrasi kuadratur dengan %d titik:\n", n + 1);
		
		for (int i = 0; i < n + 1; i++) {
			System.out.printf("x%-3d = %8.4f ", i, oldX[i]);
			System.out.printf("%s ∫ f(%.4f) %.4f d(xd) = %.4f\n", "-->", xy_tb[i][0], dxd, dxd * xy_tb[i][1]);
		}
		
		System.out.printf("Itot = %8.4f", res[0]);
		for (int i = 1; i < n + 1; i++) {
			System.out.printf(" + %8.4f", res[i]);
		}
		System.out.printf("\nItot = %8.4f\n", I);
		
		if (dik == 1) {
			System.out.printf("Va = %.4f --> ", Va);
			System.out.printf("Er = %.4f%c\n", getEr(Va, I), '%');
		}
	}
}

/*
3
1
2
4
0
---------------
1	-0.577350269
1	0.577350269
*/
