package com.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.model.Booking;

@Service
public interface BookingService {
	
	public Booking book(String riderUserId, Double latitude, Double longitude);

	public List<Booking> history(String riderUserId);

	public Booking endTrip(String bookingId);
	
	public Booking getBooking(String bookingId);
}
