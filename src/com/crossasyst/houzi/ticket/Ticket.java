package com.crossasyst.houzi.ticket;

import com.crossasyst.houzi.prize.FullHouse;
import com.crossasyst.houzi.prize.MidRow;
import com.crossasyst.houzi.prize.QuickFive;

import lombok.Getter;

@Getter
public class Ticket {

	// 2D array of Cell to represent Ticket
	private Cell[][] cells = new Cell[3][9];
	private byte count;
	private byte midcount;

	private QuickFive quickFive = QuickFive.getQuickFive();
	private MidRow midRow = MidRow.getMidRow();
	private FullHouse fullHouse = FullHouse.getFullHouse();

	// other class can not create a object using new
	private Ticket() {
	}

	@Override
	public String toString() {
		String ticket = "-------------------------------------------------------------------------\n";

		for (Cell[] row : cells) {
			ticket += "|";
			for (Cell cell : row) {
				String value;
				String checked;
				if (cell != null) {
					if (cell.isChecked())
						checked = "(x)";
					else
						checked = "(o)";
					value = String.valueOf(cell.getValue());
				} else {
					value = "";
					checked = " ";
				}
				ticket += value + checked + "\t" + "|";
			}
			ticket += "\n";
			ticket += "-------------------------------------------------------------------------\n";

		}
		return ticket;
	}

	// method to supply a object
	public static Ticket getTicket() {
		Ticket ticket = new Ticket(); // object creation
		ticket.fillTicket();
		return ticket; // object supplied
	}

	// method to fill the cells of Ticket
	private void fillTicket() {
		TicketFiller ticketFiller = new TicketFiller();
		cells = ticketFiller.fill(cells);
	}

	private void tick(byte value) {
		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j] != null && value == cells[i][j].getValue()) {
					cells[i][j].setChecked(true);
					count++;
					if (i == 1)
						midcount++;
					break;
				}
			}
	}

	// playing method to play a ticket
	public boolean play(byte number)
	{
		tick(number);
		
		synchronized (quickFive)
		{
			if(!quickFive.isStatus() && quickFive.check(count))
			{
				System.out.println("Ticket won Quick Five");
				System.out.println(this.toString());
			}
			quickFive.notifyAll();
		}
		
		synchronized (midRow)
		{
			if(!midRow.isStatus() && midRow.check(midcount))
			{
				System.out.println("Ticket won Middle Row");
				System.out.println(this.toString());
			}
			midRow.notifyAll();
		}

		synchronized(fullHouse)
	
		{
			if (!fullHouse.isStatus() && fullHouse.check(count))
			{
				System.out.println("Ticket won Full House");
				System.out.println(this.toString());
				this.destroy();
				System.gc();
				return false;
			}
			fullHouse.notifyAll();
		}

		return true;
	}

	private void destroy() {
		if (quickFive != null)
			quickFive = null;
		if (midRow != null)
			midRow = null;
		if (fullHouse != null)
			fullHouse = null;
		if (cells != null)
			cells = null;

	}

}
