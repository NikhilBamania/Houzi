package com.crossasyst.houzi.play;

import com.crossasyst.houzi.generator.NumberGenerator;
import com.crossasyst.houzi.ticket.Ticket;

public class Main{

	static Ticket ticket1 = null;
	static Ticket ticket2 = null;
	static NumberGenerator monitor = null;
	
	
	public static void main(String[] args) {
		ticket1 = Ticket.getTicket();
		ticket2 = Ticket.getTicket();
		
		
		monitor = NumberGenerator.getNumberGenerator();
		
		boolean start = true;
		
		System.out.println("ticket1\n" + ticket1);
		System.out.println();
		System.out.println("ticket2\n" + ticket2);
		
		while(start)
		{
			byte number = monitor.generateNumber();
			start = ticket1.play(number) && ticket2.play(number);
		}
		destruct();
		System.gc();
		System.out.println("play ended");
		
	}
	
	private static void destruct() {
		if(ticket1 != null)
			ticket1 = null;
		if(ticket2 != null)
			ticket2 = null;
		if(monitor != null)
			monitor = null;
	}
}
