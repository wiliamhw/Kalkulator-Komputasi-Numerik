package com.komnum.calc;

public class Eksponensial extends RKT_NonLinear {

	public Eksponensial (int n) {
		super(n);
	}
	
	protected void setPQ() {
		for (int i = 0; i < n; i++) {
			q[i] = xy_tb[i][0];
			p[i] = Math.log(xy_tb[i][1]);
		}
	}
	
	protected void setD2() {
		D2[0] = Math.pow(xy_tb[0][1] - 
				a * Math.exp(b * xy_tb[0][0]), 2);
		for (int i = 1; i < n; i++) {
			D2[i] = D2[i - 1] + Math.pow(xy_tb[i][1] - 
					a * Math.exp(b * xy_tb[i][0]), 2);
		}
	}
	
	public void calculate() {
		setB();
		setA();
		b = B;
		a = Math.exp(A);
		setDt2();
		setD2();
		setR2();
	}
	
	public void printResult() {
		super.printResult();
		System.out.printf("%4s %3.3f e^(%.3f x)\n", "y = ", a, b);
	}
}

/*
5
1	0.5
2	1.7
3	3.4
4	5.7
5	8.4
 */
