package com.crossasyst.houzi.play;

import java.util.ArrayList;
import com.crossasyst.houzi.ticket.Ticket;

public class TicketArray {

	 public ArrayList<Ticket> getTickets(int n)
	 {
		 ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		 
		 while(n-- != 0)
			 tickets.add(Ticket.getTicket());
		 
		 return tickets;
	 }

}
