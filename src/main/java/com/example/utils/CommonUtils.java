package com.example.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

	public static String splitFileNameFromPath(String path) {
		if (StringUtils.isEmpty(path)) {
			return path;
		}

		int index = path.indexOf('/');
		if (index == -1) {
			index = path.indexOf('\\');
		}

		if (index == -1) {
			return path;
		}

		return path.substring(index + 1);
	}

	public static int valueOf(Integer i) {
		if (i == null) {
			return 0;
		}

		return i.intValue();
	}

	public static float valueOf(Float i) {
		if (i == null) {
			return 0f;
		}

		return i.floatValue();
	}


	public static double valueOf(Double i) {
		if (i == null) {
			return 0d;
		}

		return i.doubleValue();
	}
	public static String valueOf(String i) {
		if (i == null) {
			return "";
		}

		return i;
	}

	public static long valueOf(Long i) {
		if (i == null) {
			return 0L;
		}

		return i.longValue();
	}

	public static boolean valueOf(Boolean i) {
		if (i == null) {
			return false;
		}

		return i.booleanValue();
	}

	public static boolean isZero(Integer i) {
		if (i == null || i == 0) {
			return true;
		}

		return false;
	}

	public static boolean isZero(Long i) {
		if (i == null || i == 0) {
			return true;
		}

		return false;
	}

	public static boolean isZero(Float f) {
		if (f == null || f <= 0.0001) {
			return true;
		}

		return false;
	}

	public static boolean hasValue(Integer i) {
		if (i == null || i == 0) {
			return false;
		}

		return true;
	}

	public static boolean hasValue(Long i) {
		if (i == null || i == 0) {
			return false;
		}

		return true;
	}

	public static boolean isTrue(Boolean val) {
		if (val != null && val.booleanValue() == true) {
			return true;
		}

		return false;
	}

	public static boolean isFalse(Boolean val) {
		if (val == null || val.booleanValue() == false) {
			return true;
		}

		return false;
	}

	public static String filterEmoji(String source) {
		if (StringUtils.isNotBlank(source)) {
			return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
		} else {
			return source;
		}
	}

	public static String padStrToLen(String str, int len) {
		final String padding = "000000000000000000";
		str = (padding + str);
		str = str.substring(str.length() - len);

		return str;
	}

	public static String midStrByBytes(String str, int length) {
		if (str == null) {
			return "";
		}

		int bytes = length;
		int asciiBytes = length / 2;
		int count = 0; // 统计字节数
		String reStr = ""; // 返回字符串
		char[] tempChar = str.toCharArray();
		for (int i = 0; i < tempChar.length; i++) {
			String s1 = String.valueOf(tempChar[i]);
			byte[] b = s1.getBytes();
			count += b.length;
			if (count <= bytes && reStr.length() < asciiBytes) {
				reStr += tempChar[i];
			} else {
				break;
			}
		}
		return reStr;
	}

	public static String hideCharsOfIdCard(String str) {
		if (StringUtils.isEmpty(str)) {
			return str;
		}

		int len = str.length();
		if (len < 10) {
			return str;
		}

		final String padding = "************************";
		int preEnd = Math.min(6, len);
		StringBuilder bld = new StringBuilder();
		bld.append(str.substring(0, preEnd));
		int start = Math.max(preEnd, len - 4);
		int end = str.length();
		bld.append(padding.substring(0, start - preEnd));
		bld.append(str.substring(start, end));

		return bld.toString();
	}

	public static String toString(Float d) {
		return new EasyBigDecimal(d).toString();
	}

	public static String toString(Double d) {
		return new EasyBigDecimal(d).toString();
	}

	public static String toString(Float d, int precision) {
		return new EasyBigDecimal(d).toString(precision);
	}

	public static String toString(Double d, int precision) {
		return new EasyBigDecimal(d).toString(precision);
	}

	public static final char UNDERLINE = '_';

	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String underlineToBigCamel(String param) {
		if (StringUtils.isEmpty(param)) {
			return "";
		}

		String str = underlineToCamel(param);

		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static boolean floatEqual(Float a, Float b) {
		if (a == null) {
			return (b == null);
		} else if (b == null) {
			return false;
		}

		if ((b - a) < 0.00001f) {
			return true;
		}

		return false;
	}

	public static String underlineToCamel(String param) {
		if (StringUtils.isEmpty(param)) {
			return "";
		}

		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String underlineToCamel2(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 随机指定范围内N个不重复的数（包括上下限）
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param n
	 *            获取数量
	 * @return
	 */
	public static List<Integer> randomCommon(Integer min, Integer max, Integer n) {
		if (n > (max - min + 1) || max < min) {
			System.out.println("null");
			return null;
		}
		List<Integer> result = new ArrayList<>();
		Integer count = 0;
		while (count < n) {
			Integer num = (int) Math.round(Math.random() * (max - min) + min);
			boolean flag = true;
			for (Integer j = 0; j < n; j++) {
				if (result.contains(num)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result.add(num);
				count++;
			}
		}
		Collections.sort(result);
		return result;
	}
}
