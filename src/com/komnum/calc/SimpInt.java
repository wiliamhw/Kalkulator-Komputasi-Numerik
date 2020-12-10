package com.komnum.calc;

public class SimpInt extends Simp {
	private double sum38;
	private double I38;
	private double sum13;
	private double I13;
	private int m;

	public SimpInt(int n, double a, double b, int dik) {
		super(n, a, b, dik);
	}
	
	public void _Simp38m(int start, int end) {
		sum38 = xy_tb[start][1];
		
		for (int i = start + 1; i < end - 2; i += 3) {
			sum38 += 3 * (xy_tb[i][1] + xy_tb[i + 1][1]) + 2 * xy_tb[i + 2][1];
		}
		sum38 += 3 * (xy_tb[end - 2][1] + xy_tb[end - 1][1]) + xy_tb[end][1];
		I38 = d_X * sum38 * 3 / 8;
	}
	
	public void _Simp13m(int start, int end) {
		sum13 = xy_tb[start][1];
		
		for (int i = start + 1; i < end - 1; i += 2) {
			sum13 += 4 * xy_tb[i][1] + 2 * xy_tb[i + 1][1];
		}
		sum13 += 4 * xy_tb[end - 1][1] + xy_tb[end][1];
		I13 = d_X * sum13 / 3;
	}
	
	public void calculate() {
		if (n <= 1) throw new ArithmeticException("n harus lebih besar dari 1");
		
		boolean even = (n % 2 == 0) ? true : false;
		if (even) {
			m = n;
			_Simp13m(0, m);
		}
		else {
			m = 2;
			_Simp13m(0, m);
			_Simp38m(m, n);
		}
		sum = sum38 + sum13;
		I = I38 + I13;
	}
	
	public void printResult() {
		System.out.printf("Intergrasi simpson gabungan (1/3 dan 3/8) equispaced dengan %d pias:\n", n);
		System.out.printf("%d pias pertama dengan simpson 1/3:\n", m);
		System.out.printf("\tI13 = (%.4f) . [%.4f / 3] = %.4f\n", d_X, sum13, I13);
		System.out.println("Pias selanjutnya dengan simpson 3/8:");
		System.out.printf("\tI38 = (%.4f) . [%.4f * 3 / 8] = %.4f\n", d_X, sum38, I38);
		System.out.println("I = I13 + I38");
		super.printResult();
	}

}

/*
3
2
3
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