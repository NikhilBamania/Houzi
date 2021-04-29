package com.crossasyst.houzi.generator;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class NumberGenerator {
	
	//tracking checked numbers
	private Set<Byte> container = new HashSet<Byte>();
	static NumberGenerator numberGenerator = null;
	byte number;
	
	private NumberGenerator() {}
	
	public static NumberGenerator getNumberGenerator()
	{
		if(numberGenerator == null)
			numberGenerator = new NumberGenerator();
		return numberGenerator;
	}
	
	public byte generateNumber()
	{
		do
		{
			number = (byte) (TicketNumberGenerator.generateValue((byte)90) + 1);
		}while(container.contains(number));
		
		container.add(number);		
		return number;		
	}
	
	public void startNumberGenerator()
	{
		while(container.size() != 90)
		{
			synchronized (numberGenerator)
			{
				try
				{
					Thread.sleep(1000);
					numberGenerator.wait();
				}catch (InterruptedException e)
				{
					System.out.println("Wait in Number Generator");
					e.printStackTrace();
				}

				generateNumber();
				System.out.println(number);
				
				numberGenerator.notifyAll();
			}
		}
	}

}
