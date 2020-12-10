package com.komnum.calc;

public class Simp13 extends Simp {

	public Simp13(int n, double a, double b, int dik) {
		super(n, a, b, dik);
	}

	public void calculate() {
		sum = xy_tb[0][1];
		
		for (int i = 1; i < n - 1; i += 2) {
			sum += 4 * xy_tb[i][1] + 2 * xy_tb[i + 1][1];
		}
		sum += 4 * xy_tb[n - 1][1] + xy_tb[n][1];
		I = d_X * sum / 3;
	}
	
	public void printResult() {
		System.out.printf("Intergrasi simpson 1/3 equispaced dengan %d pias:\n", n);
		System.out.printf("I = (%.4f) . [%.4f / 3]\n", d_X, sum);
		super.printResult();
	}
}

/*
Dik: Fungsi
4
4
0
Dik: Tabel x dan F(x)
7
7
1
1
1	1.8287
2	5.6575
3	11.4862
4	19.3149
5	29.1437
6	40.9724
7	54.8011
*/
