package com.komnum.calc;

public abstract class Regression extends KomnumCalc {
	protected double[] cd; // x*y or q*p
	protected double[] c2;
	
	protected double sum_cd;
	protected double sum_c2;
	protected double sum_x;
	protected double sum_y;
	
	protected double xm; // x mean
	protected double ym;
	
	protected double r2;
	protected double[] Dt2;
	protected double[] D2;
	
	public Regression(int n) {
		super(n);
		cd = new double[n];
		c2 = new double[n];
		Dt2 = new double[n];
		D2 = new double[n];
		
		setCd();
		setC2();
		setSum();
		setAvg();
	}
	
	public Regression(int n, int m) {
		super(n);
		cd = new double[n];
		c2 = new double[n];
		Dt2 = new double[n];
		D2 = new double[n];
	}
	
	protected void setR2() {
		r2 = (Dt2[n -1] - D2[n - 1]) / Dt2[n - 1];
	}
	
	protected void setDt2() {
		Dt2[0] = Math.pow(xy_tb[0][1] - ym, 2);
		for (int i = 1; i < n; i++) {
			Dt2[i] = Dt2[i - 1] + Math.pow(xy_tb[i][1] - ym, 2);
		}
	}
	
	protected void setAvg() {
		xm = sum_x / n;
		ym = sum_y / n;
	}
	
	protected abstract void setCd();
	protected abstract void setC2();
	protected abstract void setSum();
	
	protected abstract void setD2();
	public abstract void printResult();
	public abstract void calculate();
}
