package com.komnum.calc;

import java.util.Scanner;

public class MainCalc {
	
	public static void main(String[] args) {	
		int opCode, n;
		Scanner scanner = new Scanner(System.in);
		Root calc = null;
		
		System.out.println("1. Interpolasi");
		System.out.println("2. Regresi");
//		System.out.println("3. Integrasi");
		opCode = scanner.nextInt();
		
		// Pilih materi
		if (opCode == 1) 
		{
			System.out.println("1. Polinomial");
			System.out.println("2. NGF");
			System.out.println("3. NGB");
			System.out.println("4. Lagrange");
			opCode = scanner.nextInt();
			
			// Pilih metode
			if (opCode > 0 && opCode < 5) 
			{
				System.out.print("Masukan n: ");
				n = scanner.nextInt();
				System.out.print("Masukan x target: ");
				double x = scanner.nextDouble();
				
				if (opCode == 1) {
					calc = new Polinomial(n, x);
				} 
				else if (opCode == 2) {
					calc = new NGF(n, x);
				} 
				else if (opCode == 3) {
					calc = new NGB(n, x);
				}
				else {
					calc = new Lagrange(n, x);
				}
			}
		} 
		else if (opCode == 2) {
			System.out.println("1. RKT Linear");
			System.out.println("2. RKT Non-Linear");
			System.out.println("3. Polinomial");
			opCode = scanner.nextInt();
			
			if (opCode > 0 && opCode < 4) {
				if (opCode == 2) {
					System.out.println("1. Fungsi Eksponensial (Transformasi ln)");
					System.out.println("2. Fungsi Berpangkat (Transformasi log)");
					opCode = scanner.nextInt();
					
					if (opCode == 1 || opCode == 2) {
						System.out.print("Masukan n: ");
						n = scanner.nextInt();
						
						if (opCode == 1) {
							 calc = new Eksponensial(n);
						}
						else {
							calc = new Berpangkat(n);
						}
					}
				}
				else {
					System.out.print("Masukan n: ");
					n = scanner.nextInt();
					
					if (opCode == 1) {
						calc = new RKT_Linear(n);
					}
					else if (opCode == 3) {
						System.out.print("Masukan orde polinomial: ");
						int m = scanner.nextInt();
						
						if (n >= m + 1) {
							calc = new R_Polinomial(n, m);
						}
						else {
							System.out.println("Regresi tidak dapat dilakukan");
						}
					}
				}
			}
		}
		else if (opCode == 3) {
			int simp = 0;
			System.out.println("1. Trapezoida");
			System.out.println("2. Simpson");
			System.out.println("3. Kuadratur");
			opCode = scanner.nextInt();
			
			if (opCode == 2) {
				System.out.println("1. Simpson 1/3");
				System.out.println("2. Simpson 3/8");
				System.out.println("3. Simpson gabungan");
				simp = scanner.nextInt();
			}
			
			// AKAN DIGANTI (setelah implementasi metode kaudratur dgn dik == 2)
			int dik = 1;
//			if (opCode != 3) {
//				System.out.println("Diketahui:");
//				System.out.println("1. Fungsi F(x)");
//				System.out.println("2. Tabel x dan F(x)");
//				dik = scanner.nextInt();
//			} else {
//				dik = 1;
//			}
			int tmp;
			
			// n = banyak pias
			if (dik == 1 && opCode != 3) {
				System.out.print("Masukan banyak pias: ");
				tmp = 0;
			} else {
				System.out.print("Masukan banyak titik: ");
				tmp = 1;
			}
			n = scanner.nextInt();
			n -= tmp;
			
			System.out.print("Masukan batas atas: ");
			double b = scanner.nextDouble();
			System.out.print("Masukan batas bawah: ");
			double a = scanner.nextDouble();
			
			// Pilih metode
			if (opCode == 1) {
				int type = 1;
				if (dik == 2) {
					System.out.println("1. Equispaced");
					System.out.println("2. Non-Equispaced (DANGER --> belum dites)");
					type = scanner.nextInt();
				}
				calc = new Trapezoid(n, a, b, dik, type);
			}
			else if (opCode == 2) {
				if (simp == 1) {
					calc = new Simp13(n, a, b, dik);
				}
				else if (simp == 2) {
					calc = new Simp38(n, a, b, dik);
				}
				else if (simp == 3) {
					calc = new SimpInt(n, a, b, dik);
				}
			}
			else if (opCode == 3) {
				calc = new Kuadratur(n, a, b, dik);
			}
		}
		
		if (calc != null) {
			calc.calculate();
			calc.printResult();
		} else {
			System.out.println("Wrong opCode");
		}
		scanner.close();
	}

}
