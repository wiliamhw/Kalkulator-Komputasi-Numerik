package com.komnum.calc;

public abstract class Interpolasi extends InteReg {
	protected double x_target;
	
	public Interpolasi(int n, double x_target) {
		super(n);
		this.x_target = x_target;
	}

	public double getX_target() {
		return x_target;
	}

	public void setX_target(double x_target) {
		this.x_target = x_target;
	}

	public abstract void printResult();
	public abstract void calculate();
}
