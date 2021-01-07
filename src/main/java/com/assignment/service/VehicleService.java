package com.assignment.service;

import com.assignment.model.Vehicle;

public interface VehicleService {
	public Boolean registerVehicle(Vehicle vehicle);
	public Vehicle findVehicle(Double latitude, Double longitude);
	public Boolean updateLocation(Vehicle vehicle);
	public Vehicle getVehicle(String carNumber);
}
