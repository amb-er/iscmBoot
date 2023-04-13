package com.armitage.server.iscm.common;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

public class Test7

{
	private static String s = "";

	public static void main(String[] args)

	{
		String str = "210826123745";

		System.out.println(shuffleForSortingString(str));

	}

//随机打乱一个字符串的排序方式

	public static String shuffleForSortingString(String s) {
		char[] c = s.toCharArray();

		List lst = new ArrayList();

		for (int i = 0; i < c.length; i++) {
			lst.add(c[i]);

		}

		Collections.shuffle(lst);

		String resultStr = "";

		for (int i = 0; i < lst.size(); i++) {
			resultStr += lst.get(i);

		}
		return resultStr;

	}

//打印字符串中所有排序(包含单个的)

//	public static void execute(char[] array, List list) {
//		for (int i = 0; i < array.length; i++) {
//			if (list.contains(array[i])) {
//				continue;
//
//			}
//
//			list.add(array[i] + "");
//
//			if (list.size() == array.length) {
//				String str = "";
//
//				for (int n = 0; n < list.size(); n++) {
//					str += list.get(n);
//
//				}
//
//				System.out.println(str);
//
//			} else {
//				execute(array, list);
//
//			}
//
//			list.remove(list.size() - 1);
//
//		}
//
//	}

//打印字符串中所有排序(就是一个字符串中的排序方式)

//	public static String permutation(char[] str, int i) {
//		if (i >= str.length)
//
//			return s;
//
//		if (i == str.length - 1) {
//			s += "'" + String.valueOf(str) + "',";
//
//			System.out.println(String.valueOf(str));
//
//		} else {
//			for (int j = i; j < str.length; j++) {
//				char temp = str[j];
//
//				str[j] = str[i];
//
//				str[i] = temp;
//
//				permutation(str, i + 1);
//
//				temp = str[j];
//
//				str[j] = str[i];
//
//				str[i] = temp;
//
//			}
//
//		}
//
//		return s != "" ? "select * from tableName where name in(" + s.substring(0, s.length() - 1) + ")" : s;
//
//	}

}
