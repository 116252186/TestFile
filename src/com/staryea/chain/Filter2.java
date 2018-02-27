package com.staryea.chain;

public class Filter2 implements IChain {

	private IChain next;

	public Filter2(IChain next) {
		this.next = next;
	}

	@Override
	public Object doNext() {
		
		System.out.println("i'm filter2");
		
		if (next != null)
			return next.doNext();
		return "filter2";
	}

}
