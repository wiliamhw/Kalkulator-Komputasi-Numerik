package com.komnum.calc;

public abstract class Root {
	protected int n;
	protected double[][] xy_tb; // row x column y

	public Root(int n) {
		this.n = n;
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
		double Er = (Va == 0) ? 100 : Math.abs((getEa(Va, Ve) / Va) * 100);
		return Er;
	}
	
	public double roundTo(double num, int precision) {
		double temp = Math.pow(10, precision);
		return (double) Math.round(num * temp) / temp;
	}
	
	public abstract void printResult();
	public abstract void calculate();
}
