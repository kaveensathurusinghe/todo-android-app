package com.example.todo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	private DateUtils() {
		// Utility class
	}

	public static String formatDate(long millis) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
		return sdf.format(new Date(millis));
	}

	public static long parseDate(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
		Date date = sdf.parse(dateString);
		if (date == null) {
			throw new ParseException("Unable to parse date", 0);
		}
		return date.getTime();
	}
}
