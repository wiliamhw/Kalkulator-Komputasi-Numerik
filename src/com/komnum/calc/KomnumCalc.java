package com.komnum.calc;
import java.util.Scanner;

public abstract class KomnumCalc {
	protected double[][] xy_tb; // row x column
	protected int n;
	
	public KomnumCalc(int n) {
		this.n = n;
		setxy_tb();
	}

	public void getxy_tb() {
		System.out.printf("%-8c %-8c\n", 'x', 'y');
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.printf("%-8.3f", xy_tb[i][j]);
			}
			System.out.println();
		}
	}
	
	public void setxy_tb() {
		xy_tb = new double[n][2];
		Scanner scanner = new Scanner(System.in);
		System.out.println("Masukan nilai x dan y");
		System.out.printf("%-8c %-8c\n", 'x', 'y');
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				xy_tb[i][j] = scanner.nextDouble();
			}
		}
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public double getEa(double Va, double Ve) {
		return Va - Ve;
	}
	
	public double getEr(double Va, double Ve) {
		double Er = (Va == 0) ? 100 : (getEa(Va, Ve) / Va) * 100;
		return Er;
	}
	
	public double roundTo(double num, int precision) {
		double temp = Math.pow(10, precision);
		return (double) Math.round(num * temp) / temp;
	}
	
	public abstract void printResult();
	public abstract void calculate();
}
