package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 大厅成员关系类
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_society_member")
public class SocietyMember implements Serializable{

	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="society_own")
	private Society society_own;
	
	@ManyToOne
	@JoinColumn(name="user_own")
	private User user_own;
	
	private int role;//角色 0:普通成员、1:管理员、2：厅主（初始为大厅创建者）、3：指导教师
	
	private boolean link;//是否建立联系
	
	@OneToOne(mappedBy="member")
	private Message message_own; //申请消息
	
	private Date date;//加入时间
	
	private int level;//活跃度
	
	private boolean silence;//是否被禁言
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Society getSociety_own() {
		return society_own;
	}

	public void setSociety_own(Society society_own) {
		this.society_own = society_own;
	}

	public User getUser_own() {
		return user_own;
	}

	public void setUser_own(User user_own) {
		this.user_own = user_own;
	}

	public boolean isLink() {
		return link;
	}

	public void setLink(boolean link) {
		this.link = link;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isSilence() {
		return silence;
	}

	public void setSilence(boolean silence) {
		this.silence = silence;
	}

	public SocietyMember() {
		super();
	}

	public SocietyMember(long id) {
		super();
		this.id = id;
	}

	public Message getMessage_own() {
		return message_own;
	}

	public void setMessage_own(Message message_own) {
		this.message_own = message_own;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	
}
