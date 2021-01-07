package com.assignment.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.dao.StorageDao;
import com.assignment.model.Booking;
import com.assignment.model.Vehicle;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private StorageDao storage;

	@Autowired
	private VehicleService vehicleService;

	public Booking book(String riderUserId, Double latitude, Double longitude) {
		List<Booking> bookings=storage.rideHistory(riderUserId);
		for(Booking riderBooking: bookings) {
			if(riderBooking.getStatus().equals("STARTED")) {
				throw new RuntimeException("You already have an ongoing trip. Please wait for the trip to end");
			}
		}
		Vehicle vehicle = vehicleService.findVehicle(longitude, latitude);
		vehicle.setAvailable(false);
		Booking booking = new Booking();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		LocalDateTime time = LocalDateTime.now();
//		Timestamp timestamp = Timestamp.valueOf(time);
//		Long startTime = timestamp.getTime();
		//booking.setStartTime(startTime);
		booking.setBookingId(Long.toString(storage.getCurrentBookingId()));
		booking.setRiderUserId(riderUserId);
		booking.setStatus("STARTED");
		booking.setStartTime(timestamp.getTime());
		booking.setCarNumber(vehicle.getCarNumber());
		storage.book(booking);
		return booking;
	}

	public List<Booking> history(String riderUserId) {
		List<Booking> bookingHistory = new ArrayList<>();
		bookingHistory = storage.rideHistory(riderUserId);
		return bookingHistory;
	}

	public Booking endTrip(String bookingId) {
//		LocalDateTime time = LocalDateTime.now();
//		Timestamp timestamp = Timestamp.valueOf(time);
//		Long endTime = timestamp.getTime();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		storage.endTrip(timestamp.getTime(), bookingId);
		return storage.getBooking(bookingId);
	}

	public Booking getBooking(String bookingId) {
		return storage.getBooking(bookingId);
	}
//	public static void main(String args[]) {
//		BookingServiceImpl bsi=new BookingServiceImpl();
//		bsi.storage=new StorageDaoImpl();
//		bsi.book("100", 67.00, 100.00);
//	}
}
