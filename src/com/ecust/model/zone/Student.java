package com.ecust.model.zone;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ecust.model.study.Chapter;
import com.ecust.model.study.CourseStudent;
import com.ecust.model.study.Master;

/**
 * 学生角色
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("s")
public class Student extends User {
	
	private int exp;
	
	@ManyToMany(mappedBy="learned_students")
	private Set<Chapter> chapters=new HashSet<Chapter>();
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="recentLearn")
	private Chapter recentLearn;	//最近学的一章的编号
	
	@OneToMany(mappedBy="student", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Master> master;	//对知识点的掌握度
	
	@OneToMany(mappedBy="student", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<CourseStudent> courses = new HashSet<CourseStudent>();	//学习的课程学习情况
	
	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

	public Chapter getRecentLearn() {
		return recentLearn;
	}

	public void setRecentLearn(Chapter recentLearn) {
		this.recentLearn = recentLearn;
	}

	public Set<Master> getMaster() {
		return master;
	}

	public void setMaster(Set<Master> master) {
		this.master = master;
	}

	public Student(long id, String id_number, String name, String school,
			String college, String grade, boolean state, Date last_login,
			int point, int login_times, Date date) {
		super(id, id_number, name, school, college, grade, state, last_login,
				point, login_times, date);
	}

	public Student() {
		super();
	}

	public Set<CourseStudent> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseStudent> courses) {
		this.courses = courses;
	}

	public Student(long id) {
		super(id);
	}
}