package com.ecust.model.temp;

import java.util.Date;

public class MessageTemp {

	private long message_id;
	
	private String message_text;
	
	private int message_type;
	
	private Date create_date;
	
	private boolean is_read;
	
	private long friends_id;
	
	private boolean friends_isLink;
	
	private long from_user_id;
	
	private String from_user_name;
	
	private String from_user_picture;
	
	private long member_id;

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	public String getMessage_text() {
		return message_text;
	}

	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}

	public int getMessage_type() {
		return message_type;
	}

	public void setMessage_type(int message_type) {
		this.message_type = message_type;
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

	public long getFriends_id() {
		return friends_id;
	}

	public void setFriends_id(long friends_id) {
		this.friends_id = friends_id;
	}

	public boolean isFriends_isLink() {
		return friends_isLink;
	}

	public void setFriends_isLink(boolean friends_isLink) {
		this.friends_isLink = friends_isLink;
	}

	public long getFrom_user_id() {
		return from_user_id;
	}

	public void setFrom_user_id(long from_user_id) {
		this.from_user_id = from_user_id;
	}

	public String getFrom_user_name() {
		return from_user_name;
	}

	public void setFrom_user_name(String from_user_name) {
		this.from_user_name = from_user_name;
	}

	public String getFrom_user_picture() {
		return from_user_picture;
	}

	public void setFrom_user_picture(String from_user_picture) {
		this.from_user_picture = from_user_picture;
	}

	public MessageTemp(long message_id, String message_text, int message_type,
			Date create_date, boolean is_read, long friends_id,
			boolean friends_isLink, long from_user_id, String from_user_name,
			String from_user_picture) {
		super();
		this.message_id = message_id;
		this.message_text = message_text;
		this.message_type = message_type;
		this.create_date = create_date;
		this.is_read = is_read;
		this.friends_id = friends_id;
		this.friends_isLink = friends_isLink;
		this.from_user_id = from_user_id;
		this.from_user_name = from_user_name;
		this.from_user_picture = from_user_picture;
	}

	public MessageTemp() {
		super();
	}
	
	//大厅邀请信息
	public MessageTemp(long message_id, long from_user_id, long member_id) {
		super();
		this.message_id = message_id;
		this.from_user_id = from_user_id;
		this.member_id = member_id;
	}

	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}
	
	
}
