package com.krish.hystrix.enterpriseorder;

import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// @SpringBootTest
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class EOControllerTest {

	@Test
	public void testCreateOrder() {
		try {
			StopWatch watch = new StopWatch();
			HttpUriRequest request = new HttpGet("http://localhost:" + "" + "9090" + "/eo/create");

			// When
			HttpResponse httpResponse;
			watch.start();
			httpResponse = HttpClientBuilder.create().build().execute(request);
			watch.stop();
			// Then
			// System.out.println("StatusCode:"+httpResponse.getStatusLine().getStatusCode());
			System.out.println(">>>>>>>Response:" + EntityUtils.toString(httpResponse.getEntity())
					+ "  Response time(hh:mm:SS:mS): " + watch.toString());
			assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
			// The format used is ISO 8601-like, hours:minutes:seconds.milliseconds.
			// System.out.println("Response time : "+watch.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testCreateOrders() {
		for (int i=0;i<10;i++) {
			testCreateOrder();
		}
		assertTrue(true);
	}

}
