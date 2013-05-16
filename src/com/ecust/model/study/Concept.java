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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ecust.model.zone.Course;

/**
 * 课程知识点
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_concept")
public class Concept implements Serializable{
	
	@Id
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	private String name;
	
	private Date date;	//添加时间
	
	private int weight;	//重要性：1-5（用于设置知识点的默认权值）
	
	private float master;	//所有学生对知识点的总掌握度
	
	private int answer_sum;//回答有关该概念问题的总次数
	
	@OneToMany(mappedBy="concept",fetch=FetchType.EAGER)
	private Set<ConceptQuestion> concept_q = new HashSet<ConceptQuestion>();
	
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;//所属课程
	
	@ManyToOne
	@JoinColumn(name="chapter")
	private Chapter chapter;//所属章节

	public long getId() {
		return id;
	}

	public Concept(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Concept(long id) {
		super();
		this.id = id;
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

	public float getMaster() {
		return master;
	}

	public void setMaster(float master) {
		this.master = master;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Concept() {
	}

	public Concept(long id, String name, int weight) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weight;
	}

	public Set<ConceptQuestion> getConcept_q() {
		return concept_q;
	}

	public void setConcept_q(Set<ConceptQuestion> concept_q) {
		this.concept_q = concept_q;
	}

	public int getAnswer_sum() {
		return answer_sum;
	}

	public void setAnswer_sum(int answer_sum) {
		this.answer_sum = answer_sum;
	}
	
}
