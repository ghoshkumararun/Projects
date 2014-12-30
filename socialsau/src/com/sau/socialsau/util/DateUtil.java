package com.sau.socialsau.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtil {

	public static java.sql.Date getCurrentDate() {
		java.util.Date now = new java.util.Date();
		return new java.sql.Date(now.getTime());
	}
	
	public static java.sql.Timestamp getCurrentDateTime() {
		java.util.Date now = new java.util.Date();
		return new java.sql.Timestamp(now.getTime());
	}
	
	public static java.sql.Time getCurrentTime() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Time(today.getTime());
	}
	
	public static java.sql.Timestamp setDateTime(java.util.Date utilDate) {
		return new java.sql.Timestamp(utilDate.getTime());
	}
	
	public static java.sql.Date setDate(java.util.Date utilDate) {
		return new java.sql.Date(utilDate.getTime());
	}
	
	public static java.sql.Date setCustomDate(String dateStr, String formatDate) {
		DateFormat df = new SimpleDateFormat(formatDate, Locale.US);
		try {
			java.util.Date utilDate = df.parse(dateStr);
			return new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public static java.sql.Timestamp setCustomDatetime(String dateStr, String formatDate) {
		DateFormat df = new SimpleDateFormat(formatDate, Locale.US);
		try {
			java.util.Date utilDate = df.parse(dateStr);
			return new java.sql.Timestamp(utilDate.getTime());
		} catch (ParseException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public static java.util.Date setStrInputToUtilDate(String strInput, String formatDate) {
		DateFormat df = new SimpleDateFormat(formatDate, Locale.US);
		try {
			return df.parse(strInput);
		} catch (ParseException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public static void main(String[] args) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MMMM-dd", Locale.US);
		System.out.println(df.format(new java.util.Date()));
	}
	
}
