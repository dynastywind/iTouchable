package com.ecust.model.temp;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@SuppressWarnings("serial")
public class CourseStudentTemp implements Serializable {
	
	private long id;

	private long s_id;
	
	private String s_name;
	
	private String s_class;
	
	private String s_id_number;
	
	private int exp;//经验值
	
	private int active;	//活跃天数
	
	private Time timeInSolving;	//回答标准试题的总时间
	
	private int timesToSolve;	//回答标准试题的次数
	
	private Date date;	//选课时间

	public CourseStudentTemp(long id, long s_id, String s_name, String s_class,
			String s_id_number, int exp, int active, Time timeInSolving,
			int timesToSolve, Date date) {
		super();
		this.id = id;
		this.s_id = s_id;
		this.s_name = s_name;
		this.s_class = s_class;
		this.s_id_number = s_id_number;
		this.exp = exp;
		this.active = active;
		this.timeInSolving = timeInSolving;
		this.timesToSolve = timesToSolve;
		this.date = date;
	}

	public CourseStudentTemp() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getS_id() {
		return s_id;
	}

	public void setS_id(long s_id) {
		this.s_id = s_id;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getS_class() {
		return s_class;
	}

	public void setS_class(String s_class) {
		this.s_class = s_class;
	}

	public String getS_id_number() {
		return s_id_number;
	}

	public void setS_id_number(String s_id_number) {
		this.s_id_number = s_id_number;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Time getTimeInSolving() {
		return timeInSolving;
	}

	public void setTimeInSolving(Time timeInSolving) {
		this.timeInSolving = timeInSolving;
	}

	public int getTimesToSolve() {
		return timesToSolve;
	}

	public void setTimesToSolve(int timesToSolve) {
		this.timesToSolve = timesToSolve;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
