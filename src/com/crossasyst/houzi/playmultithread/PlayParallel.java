package com.crossasyst.houzi.playmultithread;

import com.crossasyst.houzi.generator.NumberGenerator;
import com.crossasyst.houzi.ticket.Ticket;

public class PlayParallel {

	public void start() {

		NumberGenerator monitor = NumberGenerator.getNumberGenerator();
		// Generate Ticket
		Ticket ticket = Ticket.getTicket();

		System.out.println(ticket.toString());

		Boolean play = true;
		while (play)
		{
			synchronized (monitor) {
				byte value = monitor.getNumber();
				play = ticket.play(value);
				monitor.notify();
			}
			
			if (!play)
				break;
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Wait in PlayParallel");
				e.printStackTrace();
			}
		}

		if (ticket != null)
			ticket = null;
		if (monitor != null)
			monitor = null;
		System.gc();
		System.out.println("play ended");
		System.exit(0);

	}

}
