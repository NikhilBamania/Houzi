package com.crossasyst.houzi.play;

import java.util.List;
import java.util.Scanner;

import com.crossasyst.houzi.generator.NumberGenerator;
import com.crossasyst.houzi.ticket.Ticket;

public class StartPlay {

	NumberGenerator monitor = null;
	List<Ticket> tickets = null;
	TicketArray ticketArray = null;
	Scanner sc = null;
	
	public void start() {
		
		sc = new Scanner(System.in);
		System.out.print("Enter number of Tickets: ");
		int n = sc.nextInt();
		System.out.println();
		ticketArray = new TicketArray();
		tickets = ticketArray.getTickets(n);
		
		
		//print ticket
		for(int i=0; i<tickets.size(); i++)
			System.out.println(tickets.get(i).toString());
		
		
		NumberGenerator numberGenerator = NumberGenerator.getNumberGenerator();
		
		boolean play = true;
		while(play)
		{
			byte value = numberGenerator.generateNumber();
			for(int i=0; i<tickets.size(); i++)
			{
				play = tickets.get(i).play(value);
				if(play == false)
					break;
			}
		}
	}
}
