package com.komnum.calc;

public class Simp38 extends Simp {

	public Simp38(int n, double a, double b, int dik) {
		super(n, a, b, dik);
	}
	
	public void calculate() {
		sum = xy_tb[0][1];
		
		for (int i = 1; i < n - 2; i += 3) {
			sum += 3 * (xy_tb[i][1] + xy_tb[i + 1][1]) + 2 * xy_tb[i + 2][1];
		}
		sum += 3 * (xy_tb[n - 2][1] + xy_tb[n - 1][1]) + xy_tb[n][1];
		I = d_X * sum * 3 / 8;
	}
	
	public void printResult() {
		System.out.printf("Intergrasi simpson 3/8 equispaced dengan %d pias:\n", n);
		System.out.printf("I = (%.4f) . [%.4f * 3 / 8]\n", d_X, sum);
		super.printResult();
	}
}

/*
Dik: Fungsi
3
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