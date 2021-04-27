package com.crossasyst.houzi.generator;

import java.util.Set;

public class DuplicateChecker{
	
	public static boolean isRepeated(Set<Byte> jar, byte value)
	{
		if(jar.contains(value))
			return true;
		return false;
	}
}
