package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ecust.model.zone.Course;

@SuppressWarnings("serial")
@Entity
@Table(name="t_society")
public class Society implements Serializable{
	
	@Id
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	@Column(length=36)
	private String name;
	
	@Column(length=2)
	private int society_type;//类型：1-自然班、2-教学班、3-学习小组
	
	private String society_info;//简单介绍
	
	@OneToMany(mappedBy="society_own")
	private Set<SocietyMember> member = new HashSet<SocietyMember>();//成员
	
	private Date createdate;
	
	@OneToMany(mappedBy="society_own")
	private Set<SocietyNotice> notice = new HashSet<SocietyNotice>();//公告
	
	@OneToMany(mappedBy="society_own")
	private Set<Activity> activity = new HashSet<Activity>();//动态、发布的话题
	
	@ManyToOne
	@JoinColumn(name="course_own")
	private Course course_own; //课程
	
	public Society() {
		super();
	}
	
	public Society(long id) {
		super();
		this.id = id;
	}

	public Society(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<SocietyMember> getMember() {
		return member;
	}

	public void setMember(Set<SocietyMember> member) {
		this.member = member;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Set<SocietyNotice> getNotice() {
		return notice;
	}

	public void setNotice(Set<SocietyNotice> notice) {
		this.notice = notice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSociety_type() {
		return society_type;
	}

	public void setSociety_type(int society_type) {
		this.society_type = society_type;
	}

	public String getSociety_info() {
		return society_info;
	}

	public void setSociety_info(String society_info) {
		this.society_info = society_info;
	}

	public Course getCourse_own() {
		return course_own;
	}

	public void setCourse_own(Course course_own) {
		this.course_own = course_own;
	}

	public Set<Activity> getActivity() {
		return activity;
	}

	public void setActivity(Set<Activity> activity) {
		this.activity = activity;
	}
	
	
}
