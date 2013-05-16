package com.ecust.model.temp;

import java.util.Date;

public class ChatTemp {
	
	private long chat_id;
	
	private Date chat_date;
	
	private String chat_text;
	
	private boolean chat_read;
	
	private long from_user_id;
	
	private String from_user_name;
	
	private String from_user_picture;
	
	private long to_user_id;
	
	private String to_user_name;
	
	private String to_user_picture;

	public long getChat_id() {
		return chat_id;
	}

	public void setChat_id(long chat_id) {
		this.chat_id = chat_id;
	}

	public Date getChat_date() {
		return chat_date;
	}

	public void setChat_date(Date chat_date) {
		this.chat_date = chat_date;
	}

	public String getChat_text() {
		return chat_text;
	}

	public void setChat_text(String chat_text) {
		this.chat_text = chat_text;
	}

	public boolean isChat_read() {
		return chat_read;
	}

	public void setChat_read(boolean chat_read) {
		this.chat_read = chat_read;
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

	public long getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(long to_user_id) {
		this.to_user_id = to_user_id;
	}

	public String getTo_user_name() {
		return to_user_name;
	}

	public void setTo_user_name(String to_user_name) {
		this.to_user_name = to_user_name;
	}

	public String getTo_user_picture() {
		return to_user_picture;
	}

	public void setTo_user_picture(String to_user_picture) {
		this.to_user_picture = to_user_picture;
	}

	public ChatTemp(long chat_id, Date chat_date, String chat_text,
			boolean chat_read, long from_user_id, String from_user_name,
			String from_user_picture, long to_user_id, String to_user_name,
			String to_user_picture) {
		super();
		this.chat_id = chat_id;
		this.chat_date = chat_date;
		this.chat_text = chat_text;
		this.chat_read = chat_read;
		this.from_user_id = from_user_id;
		this.from_user_name = from_user_name;
		this.from_user_picture = from_user_picture;
		this.to_user_id = to_user_id;
		this.to_user_name = to_user_name;
		this.to_user_picture = to_user_picture;
	}
	
	

}
