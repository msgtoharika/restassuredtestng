package com.test.scripts;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class DeleteEmployee extends TestBase{
	
	
	@BeforeClass
	public void getAllEmployees() throws InterruptedException {

		logger.info("################# Started get all employees ######################");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpReq = RestAssured.given();
		response = httpReq.request(Method.DELETE, "/delete/"+empId);
		Thread.sleep(3000);

	}
	
	
	@Test
	public void checkResponseBody() {
		logger.info("############### checking response body ###############################");

		String responseBody = response.getBody().asString();
		logger.info("responseBody --------> " + responseBody);
		Assert.assertTrue(responseBody.contains("Successfully! Record has been deleted"));

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
