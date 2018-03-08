package com.krish.hystrix.enterpriseorder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EOController {

	@RequestMapping("/create")
	@Produces({ MediaType.TEXT_PLAIN })
	public String createOrder() {
		// Call Address Order Validator mS
		String response = "";
		try {
			StopWatch watch = new StopWatch();
			HttpUriRequest request = new HttpGet("http://localhost:" + "" + "9091" + "/eo/validate");
			HttpResponse httpResponse;
			watch.start();
			httpResponse = HttpClientBuilder.create().build().execute(request);
			watch.stop();
			response = EntityUtils.toString(httpResponse.getEntity());
			//System.out.println(">>>>>>>Response:" + response + "  Response time(hh:mm:SS:mS): " + watch.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response.contains("Order Validated"))
			return "Order Created!!";
		else
			throw new InternalServerErrorException();
	}
}
