package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ecust.model.zone.Course;

/**
 * 话题、学习行为
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_activity")
public class Activity implements Serializable{

	@Id
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	private Date date;
	
	private String text;
	
	@OneToMany(mappedBy="activity_own",cascade = {CascadeType.REMOVE})
	@OrderBy("createdate ASC")
	private Set<Comment> comment = new HashSet<Comment>();//话题的评论

	private int share_count;
	
	@Column(length=64)
	private String ip;
	
	private String a_type; //a:所有、 t:topic话题、m:movement学习动态
	
	@ManyToOne
	@JoinColumn(name="user_own")
	private User user_own;
	
	private boolean is_finish = false;//是否已解决
	
	private boolean is_essence = false;//是否精华讨论
	
	private boolean in_zone = true;//针对话题，true发布在学习社区，false代表发布在学习大厅
	
	@ManyToOne
	@JoinColumn(name="society_own")
	private Society society_own;//发布位置，0:学习社区，>0学习大厅的id
	
	@ManyToOne
	@JoinColumn(name="course_own")
	private Course course_own;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	public int getShare_count() {
		return share_count;
	}

	public void setShare_count(int share_count) {
		this.share_count = share_count;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getA_type() {
		return a_type;
	}

	public void setA_type(String a_type) {
		this.a_type = a_type;
	}

	public Activity(long id) {
		super();
		this.id = id;
	}

	public Activity() {
		super();
	}

	public User getUser_own() {
		return user_own;
	}

	public void setUser_own(User user_own) {
		this.user_own = user_own;
	}

	public boolean isIs_finish() {
		return is_finish;
	}

	public void setIs_finish(boolean is_finish) {
		this.is_finish = is_finish;
	}

	public boolean isIs_essence() {
		return is_essence;
	}

	public void setIs_essence(boolean is_essence) {
		this.is_essence = is_essence;
	}

	public Course getCourse_own() {
		return course_own;
	}

	public void setCourse_own(Course course_own) {
		this.course_own = course_own;
	}

	public Society getSociety_own() {
		return society_own;
	}

	public void setSociety_own(Society society_own) {
		this.society_own = society_own;
	}

	public boolean isIn_zone() {
		return in_zone;
	}

	public void setIn_zone(boolean in_zone) {
		this.in_zone = in_zone;
	}
	
}
