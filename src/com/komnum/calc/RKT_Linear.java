package com.komnum.calc;

public class RKT_Linear extends Regression {
	private double a;
	private double b;
	
	public RKT_Linear(int n) {
		super(n);
	}

	private void setB() {
		b = (n * sum_cd - sum_x * sum_y) / (n * sum_c2 - Math.pow(sum_x, 2));
	}

	private void setA() {
		a = ym - (b * xm);
	}
	
	protected void setCd () {
		for (int i = 0; i < n; i++) {
			cd[i] = xy_tb[i][0] * xy_tb[i][1];
		}
	}
	
	protected void setD2() {
		D2[0] = Math.pow(xy_tb[0][1] - a - b * xy_tb[0][0], 2);
		for (int i = 1; i < n; i++) {
			D2[i] = D2[i - 1] + Math.pow(xy_tb[i][1] - a - b * xy_tb[i][0], 2);
		}
	}
	
	public void calculate() {
		setB();
		setA();
		setDt2();
		setD2();
		setR2();
	}
	
	protected void setC2() {
		for (int i = 0; i < n; i++) {
			c2[i] = Math.pow(xy_tb[i][0], 2);
		}
	}

	protected void setSum() {
		for (int i = 0; i < n; i++) {
			sum_x += xy_tb[i][0];
			sum_y += xy_tb[i][1];
			sum_cd += cd[i];
			sum_c2 += c2[i];
		}
	}
	
	public void printResult() {
		double tmp_D2 = 0;
		double tmp_Dt2 = 0;
		
		System.out.printf("%4s %10s %10s %10s %10s %10s %10s\n",
				"No", "Xi", "Yi", "Xi.Yi", "Xi2", "D2", "Dt2");
		
		for (int i = 0; i < n; i++) {
			if (i > 0) {
				tmp_D2 = D2[i - 1];
				tmp_Dt2 = Dt2[i - 1];
			}
			System.out.printf("%4d %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f\n",
					i + 1, xy_tb[i][0], xy_tb[i][1], cd[i], c2[i], 
					D2[i] - tmp_D2, Dt2[i] - tmp_Dt2);
		}
		System.out.printf("%4s %10.3f %10.3f %10.3f %10.3f %10.3f %10.3f\n",
				"\u03A3", sum_x, sum_y, sum_cd, sum_c2, D2[n - 1], Dt2[n - 1]);
		System.out.println();
		System.out.printf("%8s %5.3f\n", "Rerata x = ", xm);
		System.out.printf("%8s %5.3f\n", "Rerata y = ", ym);
		System.out.printf("%4s %5.3f\n", "b = ", b);
		System.out.printf("%4s %5.3f\n", "a = ", a);
		
		System.out.printf("%-4s = %4.3f + %.3fx\n", "G(x)", a, b);
		System.out.printf("r2 = %.3f\n", r2);
		System.out.printf("r  = %.3f\n", Math.sqrt(r2));
	}
}

/* Contoh input
10
1	4
2	6
3	8
4	10
5	14
6	16
7	20
8	22
9	24
10	28
*/
