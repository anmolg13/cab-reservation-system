package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Driver;
import com.assignment.service.DriverService;

@RestController
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	@PostMapping(value = "/registerDriver",produces = MediaType.APPLICATION_JSON_VALUE)
	public Driver registerDriver(@RequestParam String name, @RequestParam String countryCode,@RequestParam String phoneNumber) {
		Driver driver=new Driver();
		driver.setCountryCode(countryCode);
		driver.setName(name);
		driver.setPhoneNumber(phoneNumber);
		driverService.registerDriver(driver);
		return driver;
	}

}
