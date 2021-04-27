package com.crossasyst.houzi.generator;

public class TicketNumberGenerator {
	
	//random integer number generator method
	public static byte generateValue(byte divisor) {
		double random = (Math.random() * 1000) % divisor;
		byte randomByte = (byte) random;
		return randomByte;
	}

}
