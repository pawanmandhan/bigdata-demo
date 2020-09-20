package com.demo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Test {

	public static void main(String[] args) {
		lastQuarter();
	}

	private static void lastQuarter() {
		LocalDateTime time = LocalDateTime.parse("2020-09-19T15:30:59");
		// LocalDateTime time = LocalDateTime.now();
		System.out.println("currentTime " + time);
		
		LocalDateTime lastQuarter = time.truncatedTo(ChronoUnit.HOURS).plusMinutes(15 * (time.getMinute() / 15));
		System.out.println("lastQuarter " + lastQuarter);
		
		Instant instant = lastQuarter.atZone(ZoneId.systemDefault()).toInstant();
		long timeInMillis = instant.toEpochMilli();
		System.out.println("lastquarter in millis " + timeInMillis);
	}
}
