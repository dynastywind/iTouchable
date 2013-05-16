package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="t_comment")
public class Comment implements Serializable {
	
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	private long id;

	@ManyToOne
	@JoinColumn(name="user_own")
	private User user_own;
	
	private long user_at_id;//@回复的用户id
	
	private String user_at_name;//@回复的用户名
	
	private String comment_text;
	
	@ManyToOne
	@JoinColumn(name="activity_own")
	private Activity activity_own;//回应的话题
	
	private Date createdate;
	
	@Column(length=32)
	private String ip;
	
	public Comment() {
		super();
	}
	
	public Comment(long id) {
		super();
		this.id = id;
	}


	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public User getUser_own() {
		return user_own;
	}

	public void setUser_own(User user_own) {
		this.user_own = user_own;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUser_at_id() {
		return user_at_id;
	}

	public void setUser_at_id(long user_at_id) {
		this.user_at_id = user_at_id;
	}

	public String getUser_at_name() {
		return user_at_name;
	}

	public void setUser_at_name(String user_at_name) {
		this.user_at_name = user_at_name;
	}

	public Activity getActivity_own() {
		return activity_own;
	}

	public void setActivity_own(Activity activity_own) {
		this.activity_own = activity_own;
	}

}
