package com.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.dao.StorageDao;
import com.assignment.model.Vehicle;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	StorageDao storage;

	public Boolean registerVehicle(Vehicle vehicle) {
		storage.saveVehicle(vehicle);
		return true;
	}

	public Vehicle findVehicle(Double latitude, Double longitude) {
		Vehicle vehicle = new Vehicle();
		vehicle = storage.find(latitude, longitude);
		return vehicle;
	}

	public Boolean updateLocation(Vehicle vehicle) {
		storage.updateLocation(vehicle);
		return true;
	}

	public Vehicle getVehicle(String carNumber) {
		return storage.getVehicle(carNumber);
	}
}
