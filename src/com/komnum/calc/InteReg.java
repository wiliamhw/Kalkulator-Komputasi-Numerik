package com.komnum.calc;
import java.util.Scanner;

public abstract class InteReg extends Root {
	protected double[][] xy_tb; // row x column
	
	public InteReg(int n) {
		super(n);
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
	
	public abstract void printResult();
	public abstract void calculate();
}
