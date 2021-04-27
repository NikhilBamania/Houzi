package com.crossasyst.houzi.ticket;

import lombok.Getter;

@Getter
public class Ticket {

	//2D array of Cell to represent Ticket
	private Cell[][] cell = new Cell[3][9];
	private byte count;
	private byte midcount;
	
	// other class can not create a object using new
	private Ticket() {}
	
	@Override
	public String toString() {
		String ticket = "-------------------------------------------------------------------------\n";
		
		for(Cell[] row : cell)
		{
			ticket += "|";
			for(Cell cell : row)
			{
				String value;
				String checked;
				if(cell != null)
				{
					if(cell.isChecked())
						checked = "(x)";
					else
						checked = "(o)";
					value = String.valueOf(cell.getValue());
				}
				else
				{
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
	
	//method to supply a object
	public static Ticket getTicket()
	{
		Ticket ticket = new Ticket();	//object creation
		ticket.fillTicket();
		return ticket;					//object supplied
	}
	
	//method to fill the cells of Ticket
	private void fillTicket()
	{
		TicketFiller ticketFiller = new TicketFiller();
		cell = ticketFiller.fill(cell);
	}
	
	public void tick(byte value)
	{
		for(int i=0; i<cell.length; i++)
			for(int j=0; j<cell[i].length; j++)
			{
				if(cell[i][j] != null && value == cell[i][j].getValue())
				{
					cell[i][j].setChecked(true);
					count++;
					if(i==1)
						midcount++;
					break;
				}
			}
	}

}
