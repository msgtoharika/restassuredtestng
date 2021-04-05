package com.test.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class TestUtils {
	
	public static String empName() {
		String name = RandomStringUtils.randomAlphabetic(5);
		return name;
	}
	
	public static String empSalary() {
		String salary = RandomStringUtils.randomNumeric(5);
		return salary;
	}
	
	public static String empAge() {
		String age = RandomStringUtils.randomNumeric(2);
		return age;
	}

}
