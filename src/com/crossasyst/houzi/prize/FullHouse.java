package com.crossasyst.houzi.prize;

import com.crossasyst.houzi.ticket.Ticket;

public class FullHouse {
	
	static FullHouse fullHouse;
	boolean status;
	
	private FullHouse() {}
	
	public static FullHouse getFullHouse()
	{
		if(fullHouse == null)
			fullHouse = new FullHouse();
		return fullHouse;
	}
	
	synchronized public boolean check(Ticket ticket)
	{
		if(!status && ticket.getCount() == 15)
			status = true;
		return status;
	}

	public boolean isStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "Full House";
	}
}
