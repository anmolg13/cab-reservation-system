package com.assignment.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.assignment.model.Booking;
import com.assignment.model.Driver;
import com.assignment.model.Rider;
import com.assignment.model.Vehicle;

@Repository
public class StorageDaoImpl implements StorageDao {
	private Map<String, Rider> riderStorage;
	private Map<String, Driver> driverStorage;
	private Map<String, Vehicle> vehicleStorage;
	private Map<String, Booking> bookingStorage;
	private long currentBookingId;

	public StorageDaoImpl() {
		this.riderStorage = new HashMap<>();
		this.driverStorage = new HashMap<>();
		this.vehicleStorage = new HashMap<>();
		this.bookingStorage = new HashMap<>();
		currentBookingId = 0;
	}

	public Boolean saveRider(Rider rider) {
		String id = rider.getCountryCode() + rider.getPhoneNumber();
		String riderId = id.toString();
		if (riderStorage.get(riderId) != null) {
			throw new RuntimeException("Rider already exists!!");
		}
		rider.setId(riderId);
		riderStorage.put(riderId, rider);
		return true;
	}

	public Boolean saveDriver(Driver driver) {
		String id = driver.getCountryCode() + (driver.getPhoneNumber());
		String driverId = id.toString();
		if (driverStorage.get(driverId) != null) {
			throw new RuntimeException("Driver already exists!!");
		}
		driver.setId(driverId);
		driverStorage.put(driverId, driver);
		return true;
	}

	public Boolean saveVehicle(Vehicle vehicle) {
		for (String carNumber : vehicleStorage.keySet()) {
			System.out.println("DriverId: " + vehicleStorage.get(carNumber).getDriverId());
			if (vehicleStorage.get(carNumber).getDriverId().equals(vehicle.getDriverId())) {
				throw new RuntimeException("You already have a vehicle registered!!");
			}
		}
		if(!(driverStorage.containsKey(vehicle.getDriverId()))) {
			throw new RuntimeException("Please register as a driver first");
		}
		if (vehicleStorage.get(vehicle.getCarNumber()) != null) {
			throw new RuntimeException("This vehicle already exists!!");
		}
		vehicle.setAvailable(true);
		vehicle.setLatitude(50.00);
		vehicle.setLongitude(50.00);
		vehicleStorage.put(vehicle.getCarNumber(), vehicle);
		return true;
	}

	public Boolean updateLocation(Vehicle vehicle) {
		Vehicle vehicle1 = vehicleStorage.get(vehicle.getCarNumber());
		if (vehicle1 == null) {
			throw new RuntimeException("No such vehicle found");
		}
		vehicle1.setLatitude(vehicle.getLatitude());
		vehicle1.setLongitude(vehicle.getLongitude());
		vehicleStorage.put(vehicle.getCarNumber(), vehicle1);
		return true;
	}

	public long getCurrentBookingId() {
		currentBookingId = currentBookingId + 1;
		return currentBookingId;
	}

	public Boolean book(Booking booking) {
		bookingStorage.put(booking.getBookingId(), booking);
		Rider rider = riderStorage.get(booking.getRiderUserId());
		System.out.println("Rider: " + rider.getName());
		List<String> bookings = new ArrayList<>();
		bookings = rider.getBookingIds();
		bookings.add(booking.getBookingId());
		rider.setBookingIds(bookings);
		riderStorage.put(booking.getRiderUserId(), rider);
		return true;
	}

	public Vehicle find(Double latitude, Double longitude) {
		Vehicle vehicle = null;
		Double minDistance = Double.MAX_VALUE;
		for (String id : vehicleStorage.keySet()) {
			if (vehicleStorage.get(id).isAvailable()) {
				Double lat1 = vehicleStorage.get(id).getLatitude();
				Double long1 = vehicleStorage.get(id).getLongitude();
				Double distance = (Math.sqrt((Math.pow(latitude - lat1, 2)) + (Math.pow(longitude - long1, 2))));
				System.out.println("Distance: " + distance);
				if (distance < minDistance) {
					vehicle = this.vehicleStorage.get(id);
				}
			}
		}
		System.out.println("Vehicle:" + vehicle);
		if (vehicle == null) {
			throw new RuntimeException("No vehicle available");
		}
		return vehicle;
	}

	public List<Booking> rideHistory(String riderUserId) {
		Rider rider = riderStorage.get(riderUserId);
		List<String> riderBookingIds = rider.getBookingIds();
		List<Booking> bookingHistory = new ArrayList<>();
		for (String bookingId : riderBookingIds) {
			Booking booking = this.bookingStorage.get(bookingId);
			bookingHistory.add(booking);
		}
		return bookingHistory;

	}

	public Boolean endTrip(Long timeStamp, String bookingId) {
		Booking booking = bookingStorage.get(bookingId);
		if (booking == null) {
			throw new RuntimeException("No such booking available for ending the trip");
		}
		if (booking.getStatus() == "COMPLETED") {
			throw new RuntimeException("Trip has already ended");
		}
		Vehicle vehicle = vehicleStorage.get(booking.getCarNumber());
		vehicle.setAvailable(true);
		booking.setStatus("COMPLETED");
		booking.setEndTime(timeStamp);
		return true;
	}

	public Vehicle getVehicle(String carNumber) {
		Vehicle vehicle = vehicleStorage.get(carNumber);
		if (vehicle == null) {
			throw new RuntimeException("Invalid car number entered");
		}
		return vehicle;
	}
	
	public Booking getBooking(String bookingId) {
		Booking booking = bookingStorage.get(bookingId);
		if (booking == null) {
			throw new RuntimeException("Invalid Booking ID");
		}
		return booking;
	}
}
