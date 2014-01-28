package gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
	
	public static java.util.Date parseDate(String someDate) {
		
		DateFormat df = new SimpleDateFormat("yyyy-dd-kk", Locale.ENGLISH);
		java.util.Date result = null;
	    try {
			 result =  df.parse(someDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	    
	    return result;
	}
	
	public static String dateToString(java.util.Date date) {
		
		return new java.sql.Date(date.getTime()).toString();
	}
}
