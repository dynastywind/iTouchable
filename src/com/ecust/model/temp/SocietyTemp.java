package com.ecust.model.temp;

import java.util.Date;

public class SocietyTemp {
	
	private long society_id;
	
	private String society_name;
	
	private int society_type;
	
	private String society_info;
	
	private Date society_createdate;
	
	private long course_id;
	
	private String course_name;

	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}

	public String getSociety_name() {
		return society_name;
	}

	public void setSociety_name(String society_name) {
		this.society_name = society_name;
	}

	public int getSociety_type() {
		return society_type;
	}

	public void setSociety_type(int society_type) {
		this.society_type = society_type;
	}

	public String getSociety_info() {
		return society_info;
	}

	public void setSociety_info(String society_info) {
		this.society_info = society_info;
	}

	public Date getSociety_createdate() {
		return society_createdate;
	}

	public void setSociety_createdate(Date society_createdate) {
		this.society_createdate = society_createdate;
	}

	public long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public SocietyTemp() {
		super();
	}

	public SocietyTemp(long society_id, String society_name, int society_type,
			String society_info, Date society_createdate, long course_id,
			String course_name) {
		super();
		this.society_id = society_id;
		this.society_name = society_name;
		this.society_type = society_type;
		this.society_info = society_info;
		this.society_createdate = society_createdate;
		this.course_id = course_id;
		this.course_name = course_name;
	}

	public SocietyTemp(long society_id, String society_name, int society_type,
			String course_name) {
		super();
		this.society_id = society_id;
		this.society_name = society_name;
		this.society_type = society_type;
		this.course_name = course_name;
	}
	
	
	
}
