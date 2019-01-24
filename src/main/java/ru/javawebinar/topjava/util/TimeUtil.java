package ru.javawebinar.topjava.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;

public class TimeUtil {

	private static final Logger log = LoggerFactory.getLogger(TimeUtil.class);

	public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
		return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
	}
}
