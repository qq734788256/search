package wyl.search.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SysTimeUtil {
	
	/**
	 * 时间戳
	 * @author wangyunlong
	 * @date 2016年10月24日 下午3:54:20
	 * @return
	 */
	public static int getSystemTime() {
		return (int) (System.currentTimeMillis() / 1000);
	}
	/**
	 * 把PHP时间戳转换成正常日期时间
	 * 
	 * @param timestampString
	 * @return
	 */
	public static String phpTime2Date(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
		return date;
	}

	/**
	 * String类型Date（yyyy-MM-dd HH:mm:ss）转换成PHP时间戳
	 * 
	 * @param stringDate
	 * @return
	 */
	public static int dateToInt(String stringDate) {
		int intTime = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d;
		try {
			d = sdf.parse(stringDate);
			long l = d.getTime();
			String str = String.valueOf(l);
			intTime = Integer.parseInt(str.substring(0, 10));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return intTime;
	}
	/**
	 * String类型Date(yyyy-MM-dd)转换成PHP时间戳
	 * 
	 * @param stringDate
	 * @return
	 */
	public static int dayToInt(String stringDate) {
		int intTime = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sdf.parse(stringDate);
			long l = d.getTime();
			String str = String.valueOf(l);
			intTime = Integer.parseInt(str.substring(0, 10));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return intTime;
	}
	/**
	 * 获取当前时间
	 * @author wangyunlong
	 * @date 2016年10月14日 下午4:26:04
	 * @return
	 */
	public static String getNow(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String time = "1970-01-01 00:00:00";
		time = sdf.format(d);
		return time;
	}
	/**
	 * 获取当天时间
	 * @author wangyunlong
	 * @date 2016年10月24日 下午3:51:21
	 * @param dateformat
	 * @return
	 */
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		return hehe;
	}
}
