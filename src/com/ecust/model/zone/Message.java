package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="t_message")
public class Message implements Serializable{

	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	private long id;
	
	private String text;
	
	private int message_type;//1：好友留言，2：好友申请，3:邀请信息，4:系统消息
	
	@OneToOne
	@JoinColumn(name="friends")
	private Friends friends;//如是好友申请
	
	@ManyToOne
	@JoinColumn(name="from_user")
	private User from_user;
	
	@ManyToOne
	@JoinColumn(name="to_user")
	private User to_user;
	
	@ManyToMany(mappedBy="message_own")
	private Set<Chat> chats = new HashSet<Chat>();
	
	@OneToOne
	@JoinColumn(name="member")
	private SocietyMember member;
	
	private Date create_date;
	
	private boolean is_read;
	
	public Message(long id) {
		super();
		this.id = id;
	}

	public Message(long id, String text, int message_type, Friends friends,
			User from_user, Date create_date) {
		super();
		this.id = id;
		this.text = text;
		this.message_type = message_type;
		this.friends = friends;
		this.from_user = from_user;
		this.create_date = create_date;
	}

	public Message() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getFrom_user() {
		return from_user;
	}

	public void setFrom_user(User from_user) {
		this.from_user = from_user;
	}

	public User getTo_user() {
		return to_user;
	}

	public void setTo_user(User to_user) {
		this.to_user = to_user;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public boolean isIs_read() {
		return is_read;
	}

	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}

	public Friends getFriends() {
		return friends;
	}

	public void setFriends(Friends friends) {
		this.friends = friends;
	}

	public int getMessage_type() {
		return message_type;
	}

	public void setMessage_type(int message_type) {
		this.message_type = message_type;
	}

	public Set<Chat> getChats() {
		return chats;
	}

	public void setChats(Set<Chat> chats) {
		this.chats = chats;
	}

	public SocietyMember getMember() {
		return member;
	}

	public void setMember(SocietyMember member) {
		this.member = member;
	}

}
