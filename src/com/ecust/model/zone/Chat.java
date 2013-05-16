package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("serial")
@Entity
@Table(name="t_chat")
public class Chat implements Serializable{

	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="from_user")
	private User from_user;
	
	@ManyToOne
	@JoinColumn(name="to_user")
	private User to_user;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="t_message_chat",
			joinColumns = {@JoinColumn(name="chat_id",referencedColumnName="id")},
			inverseJoinColumns = {@JoinColumn(name="message_id",referencedColumnName="id")})
	private Set<Message> message_own = new HashSet<Message>();//提示消息
	
	private Date date;
	
	private String text;
	
	private boolean is_read = false;
	
	public Chat() {
		super();
	}
	
	public Chat(long id) {
		super();
		this.id = id;
	}

	public Chat(Set<Message> message_own) {
		super();
		this.message_own = message_own;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean isIs_read() {
		return is_read;
	}

	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}

	public Set<Message> getMessage_own() {
		return message_own;
	}

	public void setMessage_own(Set<Message> message_own) {
		this.message_own = message_own;
	}
	
	public void addMessage(Message message){
		if(!this.message_own.contains(message)){
			this.message_own.add(message);
		}
	}

	public void removeMessage(Message message){
		this.message_own.remove(message);
	}
}
