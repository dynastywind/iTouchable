package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ecust.model.study.Chapter;
import com.ecust.model.study.Concept;
import com.ecust.model.study.CourseStudent;
import com.ecust.model.study.Question;


@SuppressWarnings("serial")
@Entity
@Table(name="t_course")
public class Course implements Serializable{

	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	private long id;
	
	private String name;
	
	private Date date;//创建时间
	
	@OneToMany(mappedBy="course_own")
	private Set<Teacher> teacher = new HashSet<Teacher>();//授课教师
	
	@OneToMany(mappedBy="course", fetch=FetchType.EAGER)
	private Set<CourseStudent> students = new HashSet<CourseStudent>();//学习该课程的学生
	
	@OneToMany(mappedBy="course_own")
	private Set<Activity> movement = new HashSet<Activity>();//学生学习动态
	
	@OneToMany(mappedBy="course_own")
	private Set<Society> society = new HashSet<Society>();//课程下的学习大厅
	
	@OneToMany(mappedBy="course")
	private Set<Chapter> chapters = new HashSet<Chapter>();//课程章节
	
	@OneToMany(mappedBy="course", fetch=FetchType.EAGER)
	private Set<Question> questions = new HashSet<Question>();//课程题目
	
	@OneToMany(mappedBy="course")
	private Set<Concept> knowledges = new HashSet<Concept>();//课程知识点

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Set<Society> getSociety() {
		return society;
	}

	public void setSociety(Set<Society> society) {
		this.society = society;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<Teacher> teacher) {
		this.teacher = teacher;
	}

	public Set<CourseStudent> getStudents() {
		return students;
	}

	public void setStudents(Set<CourseStudent> students) {
		this.students = students;
	}

	public Course(long id) {
		super();
		this.id = id;
	}

	public Course() {
		super();
	}

	public Course(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Set<Activity> getMovement() {
		return movement;
	}

	public void setMovement(Set<Activity> movement) {
		this.movement = movement;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Set<Concept> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(Set<Concept> Knowledges) {
		this.knowledges = Knowledges;
	}

}
