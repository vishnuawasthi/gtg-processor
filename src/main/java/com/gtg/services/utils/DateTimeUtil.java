package com.gtg.services.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
	
	static final long ONE_SECOND_IN_MILLIS=1000;//millisecs
	
	public static Date getApiKeyExpiredTime() {
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 40);
		Long time =  calendar.getTime().getTime();
		//String date = simpleDateFormat.format(time);
		//System.out.println("date : "+date);
		return new Date (time);
	}
	
}
