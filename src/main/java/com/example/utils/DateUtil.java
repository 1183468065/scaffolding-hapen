package com.example.utils;

import com.example.enums.WorkdayEnum;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	private static final String DATE_FMT_YHMHM = "yyyy-MM-dd HH:mm";

	private static final Object lockObject = new Object();
	private static Map<String, ThreadLocal<SimpleDateFormat>> simpleDateFormatMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	public static DateFormat getDateFormatter(String pattern) {

		ThreadLocal<SimpleDateFormat> threadLocal = simpleDateFormatMap.get(pattern);

		// 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
		if (threadLocal == null) {
			synchronized (lockObject) {
				threadLocal = simpleDateFormatMap.get(pattern);
				if (threadLocal == null) {
					// 只有Map中还没有这个pattern的simpleDateFormatMap才会生成新的simpleDateFormatMap并放入map
					System.out.println("put new sdf of pattern " + pattern + " to map");

					// 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
					// SimpleDateFormat
					threadLocal = new ThreadLocal<SimpleDateFormat>() {
						@Override
						protected SimpleDateFormat initialValue() {
							System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
							return new SimpleDateFormat(pattern);
						}
					};
					simpleDateFormatMap.put(pattern, threadLocal);
				}
			}
		}

		return threadLocal.get();
	}

	public static class DatePattern {
		public static final String isoDateTime = "yyyy-MM-dd HH:mm:ss";
		public static final String isoDate = "yyyy-MM-dd";
		public static final String slashDate = "yyyy/MM/dd";
		public static final String slashTime = "yyyy/MM/dd HH:mm:ss";
		public static final String yyyyMMdd = "yyyyMMdd";
		public static final String yyyyMM = "yyyyMM";
		public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
		public static final String HHmmss = "HH:mm:ss";
		public static final String HHmm = "HH:mm";
		public static final String MMDD = "MM/dd";
		public static final String MMDD_WITHOUT_SEPARATOR = "MMdd";
	}

	public static class DateFormatter {
		public static final DateFormat dateTimeFormat = getDateFormatter(DatePattern.isoDateTime);
		public static final DateFormat dateFormat = getDateFormatter(DatePattern.isoDate);
		public static final DateFormat slashDateFormat = getDateFormatter(DatePattern.slashDate);
		public static final DateFormat slashTimeFormat = getDateFormatter(DatePattern.slashTime);
		public static final DateFormat HHmmFormat = getDateFormatter(DatePattern.HHmm);
		public static final DateFormat HHmmssFormat = getDateFormatter(DatePattern.HHmmss);
		public static final DateFormat MMDDFormat = getDateFormatter(DatePattern.MMDD);
		public static final DateFormat yyyyMMFormat = getDateFormatter(DatePattern.yyyyMM);

	}

	public static String dateToStr(Date date) {
		return dateToStr(date, DATE_FMT_YHMHM);
	}

	public static String dateToStr(Date date, String pattern) {
		if (date == null)
			return null;
		DateFormat formatter = getDateFormatter(pattern);
		return formatter.format(date);
	}

	public static Date parseDate(String str) {
		if (StringUtils.isEmpty(str))
			return null;

		try {
			DateFormat fmt = null;
			if (NumberUtils.isNumber(str)) {
				return new Date(Long.parseLong(str));
			} else if (str.indexOf("-") > -1) {
				if (str.indexOf(":") > -1) {
					fmt = DateFormatter.dateTimeFormat;
				} else {
					fmt = DateFormatter.dateFormat;
				}
			} else if (str.indexOf("/") > -1) {
				if (str.indexOf(":") > -1) {
					fmt = DateFormatter.slashTimeFormat;
				} else {
					fmt = DateFormatter.slashDateFormat;
				}
			} else {
				return null;
			}

			return fmt.parse(str);
		} catch (Exception e) {
			logger.error("error parse date:{}, {}", str, e);
			throw new RuntimeException(e);
		}
	}

	public static Date parseDate(String str, String pattern) {
		if (StringUtils.isEmpty(str))
			return null;

		try {
			return getDateFormatter(pattern).parse(str);
		} catch (ParseException e) {
		}

		return null;
	}

	public static boolean isSameDay(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
				&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 通过传入的两个时间以及对应的单位，判断两者时间的关系，两者的差值与millisecond 做比较，如果大于则返回正数，小于则返回负数。等于则返回0
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @param millisecond
	 *            毫秒数
	 * @return
	 */
	public static long compareTime(Date date1, Date date2, Long millisecond) {
		return date1.getTime() - date2.getTime() - millisecond;
	}

	public static boolean isToday(Date dt) {
		return isSameDay(dt, Calendar.getInstance().getTime());
	}

	public static boolean isAfterNow(Date dt) {
		return Calendar.getInstance().getTime().before(dt);
	}

	public static boolean isBeforeNow(Date dt) {
		return Calendar.getInstance().getTime().after(dt);
	}

	public static Calendar clearTimeField(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar changeToLastMill(Calendar cal) {
		clearTimeField(cal);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MILLISECOND, -1);
		return cal;
	}

	public static Date changeToLastMill(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		clearTimeField(calendar);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MILLISECOND, -1);
		return calendar.getTime();
	}

	public static Date changeToFirstMill(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		clearTimeField(calendar);
		return calendar.getTime();
	}

	public static int validRange(Date starttime, Date aftertime) {
		Date now = Calendar.getInstance().getTime();
		if (now.after(aftertime)) {
			return 1;
		} else if (now.before(starttime)) {
			return -1;
		}
		return 0;
	}

	public static Date getNowTime() {
		return new Date();
	}

	public static String getNowTimeStr() {
		return dateToStr(new Date());
	}

	public static Calendar getNowCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 获取传入日期所在周的周几，1对应周一，2对应周二......7对应周日
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;// 获取传入日期是这个星期的第几天(周一是第一天，对应值为1，以此类推)
		if (dayOfWeek == 0) {
			dayOfWeek = 7;// 周日对应的是0，转换成7
		}
		return dayOfWeek;
	}

	/**
	 * 获取传入日期对应的在工作日间的枚举
	 * 
	 * @param date
	 * @return
	 */
	public static WorkdayEnum getWorkDayByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;// 获取传入日期是这个星期的第几天(周一是第一天，对应值为1，以此类推)
		switch (dayOfWeek) {
		case 0:
			return WorkdayEnum.WEEKDAY;
		case 1:
			return WorkdayEnum.MONDAY;
		case 2:
			return WorkdayEnum.TUESDAY;
		case 3:
			return WorkdayEnum.WEDNESDAY;
		case 4:
			return WorkdayEnum.THURSDAY;
		case 5:
			return WorkdayEnum.FRIDAY;
		case 6:
			return WorkdayEnum.SATURDAY;

		}
		return null;
	}

	/**
	 * 根据日期获取所在周是此月的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static Integer getWeekOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
		return weekOfMonth;
	}

	/**
	 * 根据传入日期获取所在周的7天日期
	 * 
	 * @param date
	 * @return List<Date>
	 */
	public static List<String> convertWeekDate(Date date) {
		Calendar cal = getNowCalendar();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值

		List<String> list = new ArrayList<>();
		list.add(dateToStr(cal.getTime(), DateUtil.DatePattern.isoDate));// 周一日期
		for (int i = 0; i < 6; i++) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
			list.add(dateToStr(cal.getTime(), DateUtil.DatePattern.isoDate));// 添加周二至周日的日期
		}
		return list;
	}

	public static Date parseToDate(Long timeStamp) {
		Date date = new Date(timeStamp);
		return date;
	}
}
