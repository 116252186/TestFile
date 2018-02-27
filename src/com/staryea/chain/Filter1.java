package com.staryea.chain;

public class Filter1 implements IChain {

	private IChain next;

	public Filter1(IChain next) {
		this.next = next;
	}

	@Override
	public Object doNext() {
		System.out.println("-------->> i'm filter1");

		if (next != null)
			return next.doNext();
		return "filter1";
	}

}
