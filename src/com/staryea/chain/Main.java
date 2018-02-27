package com.staryea.chain;

public class Main {

	public static void main(String[] args) {

		Filter2 filter2 = new Filter2(null);
		Filter1 filter1 = new Filter1(filter2);

		System.out.println(filter1.doNext());

	}

}
