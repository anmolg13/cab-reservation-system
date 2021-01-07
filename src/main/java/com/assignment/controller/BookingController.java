package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.model.Booking;
import com.assignment.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService booking;

	@PostMapping(value = "/bookRide", produces = MediaType.APPLICATION_JSON_VALUE)
	public Booking book(@RequestParam String riderUserId, @RequestParam Double latitude,
			@RequestParam Double longitude) {
		return booking.book(riderUserId, latitude, longitude);
	}

	@GetMapping(value = "/fetchHistory", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Booking> history(@RequestParam String riderUserId) {
		return booking.history(riderUserId);
	}

	@PostMapping(value = "/endTrip", produces = MediaType.APPLICATION_JSON_VALUE)
	public Booking endTrip(@RequestParam String bookingId) {
		return booking.endTrip(bookingId);

	}

}
