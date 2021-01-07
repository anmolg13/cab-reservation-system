package com.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class Rider extends PersonalInfo{
	private List<String> bookingIds=new ArrayList<>();
	
	public List<String> getBookingIds() {
		return bookingIds;
	}
	public void setBookingIds(List<String> bookingIds) {
		this.bookingIds = bookingIds;
	}
	
}
