package com.ecust.model.zone;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 教师角色
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("t")
public class Teacher extends User {
	
	@ManyToOne
	@JoinColumn(name="course_own")
	private Course course_own;
	
	private String title;//职称
	
	public Course getCourse_own() {
		return course_own;
	}

	public void setCourse_own(Course course_own) {
		this.course_own = course_own;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Teacher() {
		super();
	}

	public Teacher(long id) {
		super(id);
	}

	public Teacher(long id, String id_number, String name, String school,
			String college, boolean state, Date last_login, int point,
			Date date, Course course_own, String title) {
		super(id, id_number, name, school, college, state, last_login, point,
				date);
		this.course_own = course_own;
		this.title = title;
	}
	
}