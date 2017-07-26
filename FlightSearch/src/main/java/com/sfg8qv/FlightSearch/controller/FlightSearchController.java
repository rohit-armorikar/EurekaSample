package com.sfg8qv.FlightSearch.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.documentdb.DocumentClientException;
import com.sfg8qv.FlightSearch.service.FlightService;

@RestController
public class FlightSearchController {
	
	@Autowired
	private FlightService service;
	
	@RequestMapping (value = "/search", method=RequestMethod.POST )
	public String saveFlightSearchInfo( @RequestBody FlightSearchData data )
	{
		try
		{
		service.saveFlightDetails(data);
		}
		catch(DocumentClientException exp)
		{
			exp.getMessage();
		}
		catch(IOException exp)
		{
			exp.getMessage();
		}
		// Save this data to H2 DB
		System.out.println("Save flight details to DB");
		
		return "Test Flight";
	}
	
	@RequestMapping (value = "/search", method=RequestMethod.GET )
	public String saveFlightSearchInfo(  )
	{
				
		return "Test Flight";
	}

}
