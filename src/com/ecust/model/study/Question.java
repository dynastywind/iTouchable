package com.ecust.model.study;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ecust.model.zone.Course;

public class Question implements Serializable{
	
	private static final long serialVersionUID = -2716183545677952291L;
	
	private long id;
	
	@ManyToOne
	@JoinColumn(name="course")
	private Course course;	//所属课程（与所属章节重复，仅是为了查询方便）
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="course")
	private Chapter chapter;	//所属章节
	
	private short difficulty;	//难度：1-5
	
	private short type;	//用途类型：1练习题、2测试题
	
	private short sub_type; //子类的类型：1填空题，2选择题，3编程题
	
	private String content;
	
	private String explanation;
	
	private Set<ConceptQuestion> concept_qs = new HashSet<ConceptQuestion>();//与知识点的对应关系
	
	private int right_count;//正确次数
	
	private int error_count;//错误次数（正确率由“正确次数”和“错误次数”实时计算得出）
	
	private Date date;
	
	public Question(){
		
	}
	public short getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(short difficulty) {
		this.difficulty = difficulty;
	}
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Question(long id) {
		super();
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getRight_count() {
		return right_count;
	}
	public void setRight_count(int right_count) {
		this.right_count = right_count;
	}
	public int getError_count() {
		return error_count;
	}
	public void setError_count(int error_count) {
		this.error_count = error_count;
	}
	public short getSub_type() {
		return sub_type;
	}
	public void setSub_type(short sub_type) {
		this.sub_type = sub_type;
	}
	public Set<ConceptQuestion> getConcept_qs() {
		return concept_qs;
	}
	public void setConcept_qs(Set<ConceptQuestion> concept_qs) {
		this.concept_qs = concept_qs;
	}
}
