package com.ecust.util;

import java.io.BufferedReader;
import java.io.Serializable;
import java.sql.Time;

import com.ecust.model.zone.Student;

public class TestReport implements Serializable{
	private static final long serialVersionUID = -3601918385576817387L;
	private long courseId;
	private Student student;
	private Time time;
	private BufferedReader reader;
	private String id;
	public TestReport(){
		
	}
	public TestReport(long courseId, Student student, Time time, BufferedReader reader, String id) {
		this.courseId=courseId;
		this.student=student;
		this.time = time;
		this.reader = reader;
		this.id=id;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public BufferedReader getReader() {
		return reader;
	}
	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
