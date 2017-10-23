package individual.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;



public class TimeUtil {
	public static String getTime(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String time = df.format(c.getTime());
		String temp = time.substring(time.lastIndexOf("-")-5);
		temp = temp.replaceAll("-", ":");
		String temp2 = time.substring(0, time.lastIndexOf("-")-5);
		time = temp2.concat(temp);
		return time;
	}
}
