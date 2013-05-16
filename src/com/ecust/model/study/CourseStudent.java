package com.ecust.model.study;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ecust.model.zone.Course;
import com.ecust.model.zone.Student;

/**
 * 学生-课程关系的中间类，用于存储学生某课程的学习情况
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_course_student")
public class CourseStudent implements Serializable {
	
	@Id
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="student")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;
	
	
	private int exp;//经验值
	
	private int active;	//活跃天数
	
	private Time timeInSolving;	//回答标准试题的总时间
	
	private int timesToSolve;	//回答标准试题的次数
	
	private Date date;	//选课时间

	private int rights;  //答对题目数
	
	private int wrongs;  //答错题目数
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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

	public int getRights() {
		return rights;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	public int getWrongs() {
		return wrongs;
	}

	public void setWrongs(int wrongs) {
		this.wrongs = wrongs;
	}
	
}
