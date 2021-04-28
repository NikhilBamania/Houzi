package com.crossasyst.houzi.prize;

public class MidRow {
	
	static MidRow midRow;
	boolean status;
	
	private MidRow() {}
	
	public static MidRow getMidRow()
	{
		if(midRow == null)
			midRow = new MidRow();
		return midRow;
	}
	
	synchronized public boolean check(byte count)
	{
		if(!status && count == 5)
			status = true;
		return status;
	}

	public boolean isStatus() {
		return status;
	}
	
	@Override
	public String toString() {
		return "MidRow";
	}
}
