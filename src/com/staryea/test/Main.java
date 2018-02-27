package com.staryea.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main implements Callable {

	public static void main(String[] args) {
		try {
			Future f0 = pool.submit(new Main());
			Future f1 = pool.submit(new Main());
			Future f2 = pool.submit(new Main());
			Future f3 = pool.submit(new Main());

			System.out.println("main thread go on");

			System.out.println(f0.get());
			System.out.println(f1.get());
			System.out.println(f2.get());
			System.out.println(f3.get());

			System.out.println("main thread stop");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ExecutorService pool = Executors.newFixedThreadPool(5);

	@Override
	public Object call() throws Exception {

		System.out.println(Thread.currentThread().getName() + "start...");

		Thread.sleep(5000);

		return "ttttttttt";
	}

}
