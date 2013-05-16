package com.ecust.model.study;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ecust.model.zone.Student;

/**
 * 学生对知识点概念的掌握度（相当于把“学生”与“知识点”的多对多关系拆分为两个一对多关系）
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_master")
public class Master implements Serializable{
	
	@Id
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	private float master;	//由测试题得出的掌握度 （取值：-1-1）
	
	private int familiar;	//由学习社区得出的关心熟悉程度（取值：0-100） 

	@ManyToOne
	@JoinColumn(name="concept")
	private Concept concept;	//知识点概念
	
	@ManyToOne
	@JoinColumn(name="student")
	private Student student;
	
	private Date date;	//学生在测试中第一次遇到知识点的时间
	
	public Master(){
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getMaster() {
		return master;
	}

	public void setMaster(float master) {
		this.master = master;
	}

	public int getFamiliar() {
		return familiar;
	}

	public void setFamiliar(int familiar) {
		this.familiar = familiar;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Master(long id) {
		super();
		this.id = id;
	}
}
