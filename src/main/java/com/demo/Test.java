package com.demo;

import java.time.LocalDateTime;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		LocalDateTime date = LocalDateTime.now();

		System.out.println(date.getHour());
		System.out.println(date.getMinute());
		System.out.println(Math.ceil((double) date.getMinute() / 15.0) + "");

//		LocalDateTime localDate =
//			    LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault());
		Date now = new Date();
//		now.getTime();
//		long ut3 = now.getTime() / (1000L * 1000 * 60 * 60);
//		System.out.println(now.getTime());

		Date now2 = new Date(1599937677717l);
		System.out.println(now2);
		long ut3 = now2.getMinutes();
		System.out.println((int) Math.ceil((double) ut3 / 15.0));
	}
}
