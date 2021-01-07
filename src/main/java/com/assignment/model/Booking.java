package com.assignment.model;

public class Booking {
	private String bookingId;
    private String riderUserId;
    private String carNumber;
    private long startTime;
    private long endTime;
    private String status;
    
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getRiderUserId() {
		return riderUserId;
	}
	public void setRiderUserId(String riderUserId) {
		this.riderUserId = riderUserId;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
