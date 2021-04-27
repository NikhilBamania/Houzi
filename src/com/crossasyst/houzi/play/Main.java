package com.crossasyst.houzi.play;

import com.crossasyst.houzi.generator.NumberGenerator;
import com.crossasyst.houzi.prize.FullHouse;
import com.crossasyst.houzi.prize.MidRow;
import com.crossasyst.houzi.prize.QuickFive;
import com.crossasyst.houzi.ticket.Ticket;

public class Main{

	static Ticket ticket1 = null;
	static Ticket ticket2 = null;
	static NumberGenerator monitor = null;
	static QuickFive quickFive = null;
	static MidRow midRow = null;
	static FullHouse fullHouse = null;
	
	public static void main(String[] args) {
		ticket1 = Ticket.getTicket();
		ticket2 = Ticket.getTicket();
		
		quickFive = QuickFive.getQuickFive();
		midRow = MidRow.getMidRow();
		fullHouse = FullHouse.getFullHouse();
		monitor = NumberGenerator.getNumberGenerator();
		
		boolean start = true;
		
		System.out.println("ticket1\n" + ticket1);
		System.out.println();
		System.out.println("ticket2\n" + ticket2);
		
		while(start)
		{
			byte number = monitor.generateNumber();
			start = play(ticket1, number) && play(ticket2, number);
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
		if(quickFive != null)
			quickFive = null;
		if(midRow != null)
			midRow = null;
		if(fullHouse != null)
			fullHouse = null;
		
	}

	private static boolean play(Ticket ticket, byte number)
	{
		ticket.tick(number);
		
		if(!quickFive.isStatus())
		{
			if(quickFive.check(ticket))
			{
				System.out.println("Ticket won Quick Five");
				System.out.println(ticket);
			}
		}
		else if(!midRow.isStatus())
		{
			if(midRow.check(ticket))
			{
				System.out.println("Ticket won Middle Row");
				System.out.println(ticket);
			}
		}
		if(!fullHouse.isStatus())
		{
			if(fullHouse.check(ticket))
			{
				System.out.println("Ticket won Full House");
				System.out.println(ticket);
				return false;
			}
		}
		
		return true;
	}
}
