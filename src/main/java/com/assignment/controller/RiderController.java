package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Rider;
import com.assignment.service.RiderService;

@RestController
public class RiderController {
	
	@Autowired
	private RiderService riderService;
	
	@PostMapping(value = "/registerRider",produces = MediaType.APPLICATION_JSON_VALUE)
	public Rider registerRider(@RequestParam String name, @RequestParam String countryCode,@RequestParam String phoneNumber) {
		Rider rider=new Rider();
		rider.setCountryCode(countryCode);
		rider.setName(name);
		rider.setPhoneNumber(phoneNumber);
		riderService.registerRider(rider);
		return rider;
	}

}
