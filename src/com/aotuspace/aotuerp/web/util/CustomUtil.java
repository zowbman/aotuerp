package com.aotuspace.aotuerp.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * Title:CustomUtil
 * Description:自定义工具类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-15 下午3:04:19
 *
 */
public class CustomUtil {
	
	//将String以逗号','隔开的字符串转为List
	public static List<String> stringToList(String string) {
		List<String> list = new ArrayList<String>();
		String[] s = string.split(",");
		//System.out.println(s[0]);
		for (String s1 : s) {
			list.add(s1);
		}
		return list;
	}
	
	//将String以逗号','隔开的字符转为Integer数组
	public static Integer[] stringToIntegers(String string) {
		String[] s = string.split(",");
		int index = 0;
		Integer[] integers = new Integer[s.length];
		for (String s1 : s) {
			integers[index++] = Integer.valueOf(s1);
		}
		return integers;
	}
		

	//讲String以逗号','隔开的字符串转为Collection
	public static Collection<String> stringToCollection(String string) {
		Collection<String> collection = new HashSet<String>();
		String[] s = string.split(",");
		for (String s1 : s) {
			collection.add(s1);
		}
		return collection;
	}
	
	//讲String[]以'|'隔开的字符串转为String
	public static String stringsToString(String[] strings) {
		if (strings == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : strings) {
			if (flag) {
				result.append("|");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	//讲Integer[]以'|'隔开的字符串转为string
	public static String integersToString(Integer[] integers) {
		if (integers == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (Integer integer : integers) {
			if (flag) {
				result.append("|");
			} else {
				flag = true;
			}
			result.append(integer);
		}
		return result.toString();
	}
	
	//获取当前日期(yyyyMMdd)
	//参数格式
	public static String getCurrCalendar(String format){
		Date date= new Date(System.currentTimeMillis());  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(date);  
	}
	
}
