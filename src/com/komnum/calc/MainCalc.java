package com.komnum.calc;

import java.util.Scanner;

public class MainCalc {

	public static void main(String[] args) {
		int m = 0;
		Scanner scanner = new Scanner(System.in);
		KomnumCalc calc = null;
		
		System.out.println("1. Interpolasi");
		System.out.println("2. Regresi");
		int opCode = scanner.nextInt();
		
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
				int n = scanner.nextInt();
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
						int n = scanner.nextInt();
						
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
					int n = scanner.nextInt();
					
					if (opCode == 1) {
						calc = new RKT_Linear(n);
					}
					else if (opCode == 3) {
						System.out.print("Masukan orde polinomial: ");
						m = scanner.nextInt();
						
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
		
		
		if (calc != null) {
			calc.calculate();
			calc.printResult();
		} else {
			System.out.println("Wrong opCode");
		}
		scanner.close();
	}

}
