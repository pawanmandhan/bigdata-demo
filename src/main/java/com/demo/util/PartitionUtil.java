package com.demo.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class PartitionUtil {

	public static void main(String[] args) {
		lastQuarter(1600387980000l);
	}

	public static Long lastQuarter(long epochTime) {
		LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochTime), ZoneId.systemDefault());	
		// LocalDateTime time = LocalDateTime.now();
//		System.out.println("currentTime " + time);
		
		LocalDateTime lastQuarter = time.truncatedTo(ChronoUnit.HOURS).plusMinutes(15 * (time.getMinute() / 15));
//		System.out.println("lastQuarter " + lastQuarter);
		
		Instant instant = lastQuarter.atZone(ZoneId.systemDefault()).toInstant();
		long timeInMillis = instant.toEpochMilli();
//		System.out.println("lastquarter in millis " + timeInMillis);
		return timeInMillis;
	}
}
