package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="t_friends")
public class Friends implements Serializable{
	
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	private long id; 
	
	@ManyToOne
	@JoinColumn(name="owner")
	private User owner;//自己
	
	@ManyToOne
	@JoinColumn(name="friends")
	private User friends;//好友
	
	private int level;//好友亲密程度
	@ManyToOne
	@JoinColumn(name="friendsgroup_own")
	private FriendsGroup friendsgroup_own;//属于分组
	
	@OneToOne(mappedBy="friends",cascade = {CascadeType.REMOVE})
	private Message message;//好友申请消息
	
	private boolean is_Link;//是否建立好友关系
	
	private Date date;//好友关系建立时间
	
	public Friends() {
		super();
	}
	
	public Friends(long id) {
		super();
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getFriends() {
		return friends;
	}

	public void setFriends(User friends) {
		this.friends = friends;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public FriendsGroup getFriendsgroup_own() {
		return friendsgroup_own;
	}

	public void setFriendsgroup_own(FriendsGroup friendsgroup_own) {
		this.friendsgroup_own = friendsgroup_own;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public boolean isIs_Link() {
		return is_Link;
	}

	public void setIs_Link(boolean is_Link) {
		this.is_Link = is_Link;
	}


}
