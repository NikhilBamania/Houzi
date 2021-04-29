package com.crossasyst.houzi.generator;

import com.crossasyst.houzi.ticket.Cell;

public class TicketLocationGenerator {
	
	//generate random location
	public static byte generateLocation(Cell[] row, byte divisor)
	{
		byte location;
		do {
			location = TicketNumberGenerator.generateValue(divisor);
		}while(row[location] != null);			//check for the non repeating location
		return location;
	}
}