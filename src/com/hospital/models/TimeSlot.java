package com.hospital.models;

import java.io.Serializable;

public class TimeSlot  implements Serializable {

	private int startTime;
	private int endTime;
	
	public TimeSlot(int startTime,int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	// Getters & Setters :
	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TimeSlot [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}
