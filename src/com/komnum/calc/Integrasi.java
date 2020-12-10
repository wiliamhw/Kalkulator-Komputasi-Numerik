package com.komnum.calc;

import java.util.Scanner;

public abstract class Integrasi extends Root {
	protected double d_X;
	protected double a; // batas atas
	protected double b; // batas bawah
	protected double sum;
	protected double I;
	protected double Va;
	
	protected int dik;
	
	protected double F(double x) {
		return Math.exp(x);
	}
	
	protected double F_integral(double x) {
		return Math.exp(x);
	}
	
	
	private void setD_X() {
		if (dik == 1) {
			this.d_X = (b - a) / n;
		}
		else {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Selisih x: ");
			this.d_X = scanner.nextDouble();
		}
	}
	
	private void setXY() {
		if (d_X == 0) throw new ArithmeticException("batas atas = batas bawah");
		xy_tb = new double[n + 1][2];
		
		if (dik == 1) {
			for (int i = 0; i < n + 1; i++) {
				xy_tb[i][0] = a + i * d_X;
			}
			for (int i = 0; i < n + 1; i++) {
				xy_tb[i][1] = F(xy_tb[i][0]);
			}
		} 
		else {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Masukan nilai x dan y");
			System.out.printf("%-8c %-8c\n", 'x', 'y');
			
			for (int i = 0; i < n + 1; i++) {
				for (int j = 0; j < 2; j++) {
					xy_tb[i][j] = scanner.nextDouble();
				}
			}
		}
	}
	
	protected void setVa() {
		this.Va = F_integral(xy_tb[n][0]) - F_integral(xy_tb[0][0]);
	}
	
	public Integrasi(int n, double a, double b, int dik) {
		super(n);
		this.a = a;
		this.b = b;
		this.dik = dik;
		setD_X();
		setXY();
		if (dik == 1) setVa();
		else Va = 0;
	}

	public abstract void printResult();
	public abstract void calculate();
}

// n = banyak pias --> n = banyak titik
// 	  n pias --> n + 1 titikn
// setx
// sety
// set bisa diinput dari persamaan dan tabel
