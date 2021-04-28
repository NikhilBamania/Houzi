package com.crossasyst.houzi.prize;

public class QuickFive {
	
	static QuickFive quickFive;
	boolean status;
	
	private QuickFive() {}
	
	public static QuickFive getQuickFive()
	{
		if(quickFive == null)
			quickFive = new QuickFive();
		return quickFive;
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
		return "Quick Five";
	}
}
