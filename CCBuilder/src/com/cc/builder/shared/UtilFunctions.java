package com.cc.builder.shared;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class UtilFunctions {
	
	// Return current date
	public static Long currentDate(){
		return (new Date()).getTime();
	}

	// Return current date as String
	public static String currentDateStr(){
		Long date = new Long((new Date()).getTime());
		return date2Str(date);
	}
	
	// Convert a long date format to String
	public static String date2Str(Long date){
		return (new Date(date)).toString();
	}
/*	
	// Get current Date 
	public static Calendar getCurrentDate(){
		// get the supported ids for GMT-06:00 (Chicago)
		 String[] ids = TimeZone.getAvailableIDs(-6 * 60 * 60 * 1000);
		 // if no ids were returned, something is wrong. get out.
		 if (ids.length == 0)
		     return null;

		 // create a Chicago Standard Time time zone
		 SimpleTimeZone pdt = new SimpleTimeZone(-6 * 60 * 60 * 1000, ids[0]);

		 // set up rules for daylight savings time
		 pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		 pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);

		 // create a GregorianCalendar with the Pacific Daylight time zone
		 // and the current date and time
		 Calendar calendar = new GregorianCalendar(pdt);
		 Date trialTime = new Date();
		 calendar.setTime(trialTime);
		 
		 return calendar;
	}
*/
}
