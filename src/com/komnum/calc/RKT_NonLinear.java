package com.komnum.calc;

public abstract class RKT_NonLinear extends Regression  {
	protected double a;
	protected double b;
	
	protected double[] p;
	protected double[] q;
	protected double A;
	protected double B;
	
	protected double pm;
	protected double qm;
	
	protected double sum_p;
	protected double sum_q;
	
	public RKT_NonLinear(int n) {
		super(n);
	}
	
	protected void setCd() {
		p = new double[n];
		q = new double[n];
		setPQ();
		for (int i = 0; i < n; i++) {
			cd[i] = q[i] * p[i];
		}
	}
	
	protected void setB() {
		B = (n * sum_cd - sum_q * sum_p) / 
				(n * sum_c2 - Math.pow(sum_q, 2));
	}
	
	protected void setA() {
		A = pm - B * qm;
	}
	
	protected void setC2() {
		for (int i = 0; i < n; i++) {
			c2[i] = Math.pow(q[i], 2);
		}
	}
	
	protected void setSum() {
		for (int i = 0; i < n; i++) {
			sum_x += xy_tb[i][0];
			sum_y += xy_tb[i][1];
			sum_cd += cd[i];
			sum_c2 += c2[i];			
			sum_p += p[i];
			sum_q += q[i];
		}
	}
	
	protected void setAvg() {
		super.setAvg();
		pm = sum_p / n;
		qm = sum_q / n;
	}
	
	public void printResult() {
		double tmp_D2 = 0;
		double tmp_Dt2 = 0;
		
		System.out.printf("%4s %10s %10s %10s %10s %10s %10s %10s %10s\n",
				"No", "Xi", "Yi", "Qi", "Pi", "Qi.Pi", 
				"Qi2", "D2", "Dt2");
		
		for (int i = 0; i < n; i++) {
			if (i > 0) {
				tmp_D2 = D2[i - 1];
				tmp_Dt2 = Dt2[i - 1];
			}
			System.out.printf("%4d %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f %10.4f\n",
					i + 1, xy_tb[i][0], xy_tb[i][1], q[i], p[i], cd[i], c2[i], 
					D2[i] - tmp_D2, Dt2[i] - tmp_Dt2);
		}
		System.out.printf("%4s %10.3f %10.3f %10.3f %10.3f %10.3f %10.3f %10.3f %10.3f\n",
				"\u03A3", sum_x, sum_y, sum_q, sum_p, sum_cd, sum_c2, D2[n - 1], Dt2[n - 1]);
		System.out.println();
		System.out.printf("%8s %5.3f\n", "Rerata y = ", ym);
		System.out.printf("%8s %5.3f\n", "Rerata q = ", qm);
		System.out.printf("%8s %5.3f\n", "Rerata p = ", pm);
		System.out.printf("%4s %5.3f\n", "B = ", B);
		System.out.printf("%4s %5.3f\n", "A = ", A);
		System.out.printf("%4s %5.3f\n", "a = ", a);
		System.out.printf("%4s %5.3f\n", "b = ", b);
		System.out.printf("%4s %5.3f\n", "r2: ", r2);
		System.out.printf("%4s %5.3f\n", "r: ", Math.sqrt(r2));
	}
	
	protected abstract void setPQ();
	protected abstract void setD2();
	public abstract void calculate();
}
