package com.sfg8qv.ApplicationGatewayZuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZuulController {
	
	@RequestMapping (value = "/available", method=RequestMethod.GET )
	public String saveFlightSearchInfo(  )
	{
				
		return "Test Flight";
	}

}
