package com.test.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpReq;
	public static Response response;
	
	//for fetching single user data
	public static String empId = "24";
	
	public static Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		logger = Logger.getLogger(TestBase.class);
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	
	}
	


}
