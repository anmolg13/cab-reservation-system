package com.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Vehicle;
import com.assignment.service.VehicleService;

@RestController
public class VehicleController {

	@Autowired
	VehicleService vehicleService;

	@PostMapping(value = "/registerVehicle", produces = MediaType.APPLICATION_JSON_VALUE)
	public Vehicle registerVehicle(@RequestParam String driverId, @RequestParam String carNumber,
			@RequestParam String type) {
		Vehicle vehicle = new Vehicle();
		vehicle.setCarNumber(carNumber);
		vehicle.setDriverId(driverId);
		vehicle.setType(type);
		vehicleService.registerVehicle(vehicle);
		return vehicle;
	}

//	@GetMapping(value = "/findVehicle", produces = MediaType.APPLICATION_JSON_VALUE)
//	public Vehicle findVehicle(@RequestParam Double latitude, @RequestParam Double longitude) {
//		return vehicleService.findVehicle(latitude, longitude);
//	}

	@PostMapping(value = "/updateLocation", produces = MediaType.APPLICATION_JSON_VALUE)
	public Vehicle updateLocation(@RequestParam String carNumber,@RequestParam Double latitude, @RequestParam Double longitude) {
		Vehicle vehicle=vehicleService.getVehicle(carNumber);
		vehicle.setLatitude(latitude);
		vehicle.setLongitude(longitude);
		vehicleService.updateLocation(vehicle);
		return vehicle;
	}
}
