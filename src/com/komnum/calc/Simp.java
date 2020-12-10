package com.komnum.calc;

public abstract class Simp extends Integrasi {

	public Simp(int n, double a, double b, int dik) {
		super(n, a, b, dik);
	}

	public abstract void calculate();
	
	public void printResult() {
		System.out.printf("I = %.4f\n", I);
		
		if (dik == 1) {
			System.out.printf("Va = %.4f --> ", Va);
			System.out.printf("Er = %.4f%c\n", super.getEr(Va, I), '%');
		}
	}
}
