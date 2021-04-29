package com.crossasyst.houzi.playmultithread;

import java.util.Scanner;

import com.crossasyst.houzi.generator.NumberGenerator;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of Players : ");
		int n = sc.nextInt();
		
		PlayParallel parallel = new PlayParallel();
		Runnable playTickets = ()->parallel.start();
		
		NumberGenerator monitor = NumberGenerator.getNumberGenerator();
		Runnable callNumbers = ()->monitor.startNumberGenerator();
		
		for(int i=0; i<n; i++)
		{
			Thread thread = new Thread(playTickets);
			thread.setPriority(8);
			thread.start();
		}
		
		Thread numberCaller = new Thread(callNumbers);
		numberCaller.setPriority(2);
		numberCaller.start();
		
		
		if(sc != null)
			sc.close();

	}

}
