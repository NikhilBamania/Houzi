package com.crossasyst.houzi.generator;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class NumberGenerator {
	
	//tracking checked numbers
	private Set<Byte> container = new HashSet<Byte>();
	static NumberGenerator numberGenerator = null;
	
	private NumberGenerator() {}
	
	public static NumberGenerator getNumberGenerator()
	{
		if(numberGenerator == null)
			numberGenerator = new NumberGenerator();
		return numberGenerator;
	}
	
	
	public byte generateNumber()
	{
		byte number;
		do
		{
			number = (byte) (TicketNumberGenerator.generateValue((byte)90) + 1);
		}while(container.contains(number));
		
		container.add(number);		
		return number;		
	}

}
