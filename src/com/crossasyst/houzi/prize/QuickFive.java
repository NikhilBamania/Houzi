package com.crossasyst.houzi.prize;

import com.crossasyst.houzi.ticket.Ticket;

public class QuickFive {
	
	static QuickFive quickFive;
	boolean status;
	
	private QuickFive() {}
	
	public static QuickFive getQuickFive()
	{
		if(quickFive == null)
			quickFive = new QuickFive();
		return quickFive;
	}
	
	synchronized public boolean check(Ticket ticket)
	{
		if(!status && ticket.getCount() == 5)
			status = true;
		return status;
	}

	public boolean isStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "Quick Five";
	}
}
