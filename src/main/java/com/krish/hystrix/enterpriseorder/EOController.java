package com.krish.hystrix.enterpriseorder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EOController {

	@RequestMapping("/create")
	@Produces({MediaType.TEXT_PLAIN})
	public String createOrder() {
		return "Created!!";
	}
}
