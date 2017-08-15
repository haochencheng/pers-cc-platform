package pers.cc.common.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	/**
	 * 判断是否是空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return "".equals(str) || str == null;

	}

	/**
	 * 判断是否不是空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !"".equals(str) && str != null;
	}

	/**
	 * 判断str在strArr[]是否存在
	 * 
	 * @param str
	 * @return
	 */
	public static boolean existstrArr(String str, String[] strArr) {
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].equals(str))
				return true;
		}
		return false;
	}

	/**
	 * 初始化模糊查询
	 * 
	 * @param str
	 * @return
	 */
	public static String formatLike(String str) {
		if (isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}

	/**
	 * 过滤集合中的空格
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> filterWhite(List<String> list) {
		List<String> resultList = new ArrayList<>();
		for (String l : list) {
			if (isNotEmpty(l)) {
				resultList.add(l);
			}
		}
		return resultList;
	}
}
