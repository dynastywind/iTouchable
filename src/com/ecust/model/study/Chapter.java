package com.ecust.model.study;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ecust.model.zone.Course;
import com.ecust.model.zone.Student;

/**
 * 课程章节
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_chapter")
public class Chapter implements Serializable{
	
	@Id
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	private int orders;//章节次序
	
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;
	
	private String name;
	
	private Date date;
	
	@OneToMany(mappedBy="chapter",fetch=FetchType.EAGER)
	private Set<Concept> concepts = new HashSet<Concept>();
	
	@OneToMany(mappedBy="chapter",fetch=FetchType.EAGER)
	private Set<Question> questions = new HashSet<Question>();
	
	@OneToMany(mappedBy="recentLearn")
	private Set<Student> recentStu = new HashSet<Student>();	//最近一次学习此章节的学生
	
	@ManyToMany
	@JoinTable(name="t_chapter_student",joinColumns={@JoinColumn(name="chapter_id",referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="student_id",referencedColumnName="id")})
	private Set<Student> learned_students=new HashSet<Student>();
	
	private int test_count;//测试总次数

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public int getTest_count() {
		return test_count;
	}

	public void setTest_count(int test_count) {
		this.test_count = test_count;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public Chapter(long id) {
		super();
		this.id = id;
	}

	public Chapter() {
		super();
	}

	public Chapter(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Set<Concept> getConcepts() {
		return concepts;
	}

	public void setConcepts(Set<Concept> concepts) {
		this.concepts = concepts;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Set<Student> getRecentStu() {
		return recentStu;
	}

	public void setRecentStu(Set<Student> recentStu) {
		this.recentStu = recentStu;
	}

	public Set<Student> getLearned_students() {
		return learned_students;
	}

	public void setLearned_students(Set<Student> learned_students) {
		this.learned_students = learned_students;
	}
	
}
