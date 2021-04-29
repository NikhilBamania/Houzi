package com.crossasyst.houzi.play;

import java.util.ArrayList;
import java.util.List;

import com.crossasyst.houzi.ticket.Ticket;

public class TicketArray {

	 public List<Ticket> getTickets(int n)
	 {
		 List<Ticket> tickets = new ArrayList<Ticket>();
		 
		 while(n-- != 0)
			 tickets.add(Ticket.getTicket());
		 
		 return tickets;
	 }

}
