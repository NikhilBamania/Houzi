package com.crossasyst.houzi.play;

import java.util.ArrayList;
import java.util.Scanner;

import com.crossasyst.houzi.generator.NumberGenerator;
import com.crossasyst.houzi.ticket.Ticket;

public class StartPlay {
	NumberGenerator monitor = null;
	ArrayList<Ticket> tickets = null;
	TicketArray ticketArray = null;
	Scanner sc = null;
	
	public void start() {
		
		
		ticketArray = new TicketArray();
		sc = new Scanner(System.in);
		System.out.println("Enter the total number of tickets");
		int n = sc.nextInt();
		
		tickets = ticketArray.getTickets(n);
		
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
		
		destruct();
		System.gc();
		System.out.println("play ended");
		
	}
	
	private void destruct() {
		if(sc != null)
			sc.close();
		if(ticketArray != null)
			ticketArray = null;
		if(tickets != null)
			tickets = null;
		if(monitor != null)
			monitor = null;
	}

}
