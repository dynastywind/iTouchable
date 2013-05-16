package com.ecust.model.study;

import java.sql.Time;

public class Test {
	
	private String id;
	
	private String courseNo;
	
	private String testAddress;
	
	private Time time;
	
	private float averageScore;
	
	private int doneTimes;
	
	public Test(){
		
	}
	public Test(String id, String courseNo, String testAddress, Time time,
			float averageScore, int doneTimes) {
		super();
		this.id = id;
		this.courseNo = courseNo;
		this.testAddress = testAddress;
		this.time = time;
		this.averageScore = averageScore;
		this.doneTimes = doneTimes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getTestAddress() {
		return testAddress;
	}
	public void setTestAddress(String testAddress) {
		this.testAddress = testAddress;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public float getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}
	public int getDoneTimes() {
		return doneTimes;
	}
	public void setDoneTimes(int doneTimes) {
		this.doneTimes = doneTimes;
	}
}
