package com.crossasyst.houzi.ticket;

import java.util.HashSet;
import java.util.Set;
import com.crossasyst.houzi.generator.TicketLocationGenerator;
import com.crossasyst.houzi.generator.TicketNumberGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//A class to fill random number in Random Location of Array
public class RandomLocationNumberFiller {
	
	//to keep track of duplicates
	private Set<Byte> hasFilledWith = new HashSet<Byte>();
	
	static RandomLocationNumberFiller randomLocationNumberFiller = null;
	
	private RandomLocationNumberFiller() {}
	
	//singleton design
	public static RandomLocationNumberFiller getRandomLocationNumberFiller()
	{
		if (randomLocationNumberFiller == null)
			randomLocationNumberFiller = new RandomLocationNumberFiller();
		return randomLocationNumberFiller;
	}
	
	//method to call to fill the array
	public Cell[] start(Cell[] line, byte spaceToFill)
	{
		while(spaceToFill != 0)
		{
			//for location divide by 9
			byte divisor = 9;
			
			//generate Location
			byte location = TicketLocationGenerator.generateLocation(line, divisor);
			
			line = fill(line, location);
			
			//updator
			spaceToFill--;
		}
		return line;
	}

	public Cell[] fill(Cell[] line, byte location) {
		byte divisor;
		byte value;
		//for last column value divide by 11 to get 90
		if(location == 8)
			divisor = 11;
		//for rest value divide by 10
		else
			divisor = 10;
		
		//to get a non 0 integer
		do{
			value = TicketNumberGenerator.generateValue(divisor);
			value += (location * 10);
		}while(value == 0 || hasFilledWith.contains(value));
		
		//storing value
		hasFilledWith.add(value);
		
		//Cannot invoke "com.crossasyst.ticket.Cell.setValue(byte)" because "line[location]" is null
		//line[location].setValue(value);	
		
		line = byteTOCell(line, location, value);
		return line;
	}
	
	
	private Cell[] byteTOCell(Cell[] line, byte location, byte value)
	{
		line[location] = setCell(value);
		
		return line;
	}
	
	private Cell setCell(byte value)
	{
		Cell temp = new Cell();
		temp.setValue(value);
		
		return temp;
	}

	

}
