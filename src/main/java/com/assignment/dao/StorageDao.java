package com.assignment.dao;

import java.util.List;

import com.assignment.model.Booking;
import com.assignment.model.Driver;
import com.assignment.model.Rider;
import com.assignment.model.Vehicle;

public interface StorageDao {
	public Boolean saveRider(Rider rider);
	public Boolean saveDriver(Driver driver);
	public Boolean saveVehicle(Vehicle vehicle);
	public Boolean updateLocation(Vehicle vehicle);
	public long getCurrentBookingId();
	public Boolean book(Booking booking);
	public Vehicle find(Double latitude, Double longitude);
	public List<Booking> rideHistory(String riderUserId);
	public Boolean endTrip(Long timeStamp, String bookingId);
	public Vehicle getVehicle(String carNumber);
	public Booking getBooking(String bookingId);
}
