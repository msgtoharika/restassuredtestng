package com.test.scripts;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.base.TestBase;
import com.test.utils.TestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class PutUpdateExisitingUser extends TestBase {
	
	String empName = TestUtils.empName();
	String empSalary = TestUtils.empSalary();
	String empAge = TestUtils.empAge();
	
	@BeforeClass
	public void updateExistingUser() {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpReq = RestAssured.given();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", empName);
		jsonObj.put("age", empAge);
		jsonObj.put("salary", empSalary );
		
		httpReq.header("Content-Type", "application/json");
		httpReq.body(jsonObj.toJSONString());
	
		
		response = httpReq.request(Method.PUT, "/update/"+empId);
		
		
	}
	
	@Test
	public void checkResponseBody() {
		logger.info("############### checking response body ###############################");

		String responseBody = response.getBody().asString();
		logger.info("responseBody --------> " + responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertTrue(responseBody.contains("Successfully! Record has been updated."));

	}

	@Test
	public void checkStatusCode() {
		logger.info("############## checking status code #################");

		int statusCode = response.getStatusCode();
		logger.info("Status code is ---------> " + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void checkStatusLine() {

		logger.info("############## checking status line ################");

		String statusLine = response.getStatusLine();
		logger.info("status line is --------> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}
	
	
	
	@AfterClass
	public void tearDown() {
		logger.info("######## End of the test case #############");
	}

	
	

}
