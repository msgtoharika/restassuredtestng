package com.test.scripts;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class GetAllEmployees extends TestBase {

	@BeforeClass
	public void getAllEmployees() throws InterruptedException {

		logger.info("################# Started get all employees ######################");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpReq = RestAssured.given();
		response = httpReq.request(Method.GET, "/employees");
		Thread.sleep(3000);

	}

	@Test
	public void checkResponseBody() {
		logger.info("############### checking response body ###############################");

		String responseBody = response.getBody().asString();
		logger.info("responseBody --------> " + responseBody);
		Assert.assertEquals(responseBody != null, true);

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
	public void checkResponseTime() {
		
		logger.info("########### checking response time ######################");
		
		long responseTime = response.getTime();
		logger.info("time taken to give response ------>"+responseTime);
		
		if(responseTime > 2000) {
			logger.warn("Response is taking more than 2000 ms");
		}
		
		Assert.assertTrue(responseTime < 20000);
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
	
	@Test
	public void checkContentEncoding() {
		
		logger.info("######## checking content encoding type ##########");
		
		String contentEncoding = response.header("Content-Encoding");
		logger.info("contentEncoding ----> "+contentEncoding);
		
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@Test
	public void checkContentLength() {
		
		logger.info("############# checking content length #############");
		
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ------> "+contentLength);
		
		
		if(Integer.parseInt(contentLength) < 200) {
			logger.warn("Content length is not as expected");
		}
		
		Assert.assertEquals(Integer.parseInt(contentLength)>200 , true);
		
	}
	
	@Test
	public void verifyCookies() {
		
		logger.info("############# checking cookies ################");
		
		String cookie = response.getCookie("ezCMPCCS");
		logger.info("Cookie value ------>"+cookie);
		
		//Assert.assertEquals(cookie.equals("true"), true);
		
	}
	
	
	@AfterClass
	public void tearDown() {
		logger.info("######## End of the test case #############");
	}

}
