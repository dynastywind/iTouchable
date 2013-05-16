package com.ecust.model.study;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 课程知识点与题目关系中间类
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_question_concept")
public class ConceptQuestion implements Serializable{
	
	@Id
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="question_id")
	private Question question;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="concept_id")
	private Concept concept;
	
	private float weight; //知识点题中的权重

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public ConceptQuestion(long id) {
		super();
		this.id = id;
	}

	public ConceptQuestion() {
		super();
	}

}
