package za.co.polygon.service;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;

public class Testing {

	public static void main(String[] args) throws ParseException {
//		String name = "2015-10-23T00:00:00 00";
//		String ans = name.substring(0, 10);
		DateFormat df = DateFormat.getDateInstance();
//		Date date = df.parse("23/10/2015");
//		System.out.println("Date parse: "+date.toString());
//		
		Date d = new Date();
		String dfm = df.format(d);
		Date nd = df.parse("Wed Oct 28 00:00:00 SAST 2015");
		System.out.println("Date formated :"+dfm+"\nDate parsed :"+nd);
	}

}
