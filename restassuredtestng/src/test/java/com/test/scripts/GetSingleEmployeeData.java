package com.test.scripts;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetSingleEmployeeData extends TestBase{
	
	@BeforeClass
	public void getSingleEmployee() throws InterruptedException {

		logger.info("################# Started get single employee ######################");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpReq = RestAssured.given();
		response = httpReq.request(Method.GET, "/employee/"+empId);
		Thread.sleep(3000);

	}
	
	@Test
	public void checkResponseBody() {
		logger.info("############### checking response body ###############################");

		String responseBody = response.getBody().asString();
		logger.info("responseBody --------> " + responseBody);
		Assert.assertEquals(responseBody.contains(empId), true);

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
	
	@Test
	public void checkContentType() {
		
		logger.info("########### checking content type ##########");
		
		String contentType = response.header("Content-Type");
		logger.info("Content type is "+contentType);
		
		Assert.assertEquals(contentType, "application/json");
	}
	
	
	@Test
	public void checkServerType()
	{
		logger.info("############## check server type ############");
		
		String server = response.header("Server");
		logger.info("Server type is ---------->"+server);
		
		Assert.assertEquals(server, "nginx/1.16.0");
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("######## End of the test case #############");
	}


}
