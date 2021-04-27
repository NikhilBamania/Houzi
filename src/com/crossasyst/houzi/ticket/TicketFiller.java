package com.crossasyst.houzi.ticket;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class TicketFiller {
	
	private Set<Byte> hasFilledWith = new HashSet<Byte>();
	
	public Cell[][] fill(Cell[][] cell)
	{
		//loop to fill first 2 rows
		for(int i=0; i<cell.length-1; i++)
			cell[i] = fillRow(cell[i], (byte)5);		//Rule: a row can have values in only 5 cells
		
		cell[cell.length-1] = fillLastRow(cell);		//Rule: last column should be in range 80-90
		
		return cell;
	}
	
	private Cell[] fillRow(Cell[] row, byte numberOfLocations)
{
		//calling the fill method to fill row
		RandomLocationNumberFiller randomLocationNumberFiller = RandomLocationNumberFiller.getRandomLocationNumberFiller();
		randomLocationNumberFiller.setHasFilledWith(this.hasFilledWith);
		row = randomLocationNumberFiller.start(row, numberOfLocations);
		this.hasFilledWith = randomLocationNumberFiller.getHasFilledWith();	
		return row;
	}
	

	
	
	private Cell[] fillLastRow(Cell[][] ticket)
	{
		//getting the empty columns
		Queue<Byte> emptyColumn = emptyColumn(ticket);			//Rule: a column must have atleast 1 value
		
		RandomLocationNumberFiller randomLocationNumberFiller = RandomLocationNumberFiller.getRandomLocationNumberFiller();
		
		Cell[] lastRow = ticket[ticket.length-1];
		
		//filling empty columns
		for(byte location : emptyColumn)
		{
			randomLocationNumberFiller.setHasFilledWith(this.hasFilledWith);
			lastRow = randomLocationNumberFiller.fill(lastRow, location);
			this.hasFilledWith = randomLocationNumberFiller.getHasFilledWith();
		}
		lastRow = fillRow(lastRow, (byte)(5-emptyColumn.size()));
		return lastRow;
	}
	
	private Queue<Byte> emptyColumn(Cell[][] ticket)
	{	
		Queue<Byte> emptyColumns = new PriorityQueue<>();
		
		for(byte i=0; i<ticket[0].length ; i++)
			if(ticket[0][i] == null && ticket[1][i] == null)
				emptyColumns.add(i);
//		
//		byte[] emptyArr = new byte[emptyColumns.size()];
//		for(int i=0; i<emptyArr.length; i++)
//			emptyArr[i] = emptyColumns.poll();
		
		return emptyColumns;
	}


}
