package org.pop.rs.common;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class Utils {

	public static String generateId(String str) {
		if (StringUtils.isEmpty(str)) {
			return "1";
		}
		return (Integer.valueOf(str) + 1) + "";
	}

	public static Float formatFloat(Float f) {
		if (f == null)
			f = new Float(0);
		int scale = 2;
		int roundingMode = 4;
		BigDecimal bd = new BigDecimal((double) f);
		bd = bd.setScale(scale, roundingMode);
		return bd.floatValue();
	}

	public static Float formatFloat(String sf) {
		Float f = Float.valueOf(sf);
		return formatFloat(f);
	}

	public static boolean isSameDay(Date date1, Date date2) {
		if(date1 == null || date2 == null) return false;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		int year1 = c1.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH) + 1;
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		int year2 = c2.get(Calendar.YEAR);
		int month2 = c2.get(Calendar.MONTH) + 1;
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		if (year1 == year2 && month1 == month2 && day1 == day2)
			return true;
		else
			return false;
	}

	public static boolean isBetween(Date start, Date end, Date date) {
		if(start == null || end == null || date == null ) return false;
		if (date.after(start) && date.before(end))
			return true;
		else
			return false;
	}

	public static Date getTomorrowDate(Date date) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.DAY_OF_MONTH, 1);
		return c1.getTime();
	}

	public static boolean isValidDate(String date){
		if(StringUtils.isEmpty(date)){
			return false;
		}
		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat("DD-MMM-YYYY");
		try{
			Date dDate = (Date) dateFormat.parse(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}


	public static boolean isValidTime(String time){
		try{
			LocalTime.parse(time);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Date date1 = new Date();
		Date date2 = new Date();
		date2.setTime(date1.getTime() + 2 * 60 * 60 * 1000);
		System.out.println(isSameDay(date1, date2));

		Date date3 = getTomorrowDate(date1);
		System.out.println(isSameDay(date1, date3));
		System.out.println(date3);

		/*Farmer farmer = new Farmer();
		farmer.setFarm_info(new Farm("111", null, null, null));
		farmer.setFid("keyasda");
		System.out.println(containKeyword(farmer, "kEy"));
		System.out.println(containKeyword(farmer, "111"));
*/
	}
}
