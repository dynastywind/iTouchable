package com.ecust.model.temp;

import java.util.Date;

public class CommentTemp {
	
	private long comment_id;
	
	private String comment_text;
	
	private Date createdate;
	
	private long user_own_id;
	
	private String user_own_name;
	
	private String user_own_picture;
	
	private long user_at_id;
	
	private String user_at_name;
	
	private long activity_own_id;
	
	public CommentTemp() {
		super();
	}

	public CommentTemp(long comment_id, String comment_text, Date createdate,
			long user_own_id, String user_own_name, String user_own_picture,
			long user_at_id, String user_at_name, long activity_own_id) {
		super();
		this.comment_id = comment_id;
		this.comment_text = comment_text;
		this.createdate = createdate;
		this.user_own_id = user_own_id;
		this.user_own_name = user_own_name;
		this.user_own_picture = user_own_picture;
		this.user_at_id = user_at_id;
		this.user_at_name = user_at_name;
		this.setActivity_own_id(activity_own_id);
	}


	public long getComment_id() {
		return comment_id;
	}

	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public long getUser_own_id() {
		return user_own_id;
	}

	public void setUser_own_id(long user_own_id) {
		this.user_own_id = user_own_id;
	}

	public String getUser_own_name() {
		return user_own_name;
	}

	public void setUser_own_name(String user_own_name) {
		this.user_own_name = user_own_name;
	}

	public String getUser_own_picture() {
		return user_own_picture;
	}

	public void setUser_own_picture(String user_own_picture) {
		this.user_own_picture = user_own_picture;
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

	public long getActivity_own_id() {
		return activity_own_id;
	}

	public void setActivity_own_id(long activity_own_id) {
		this.activity_own_id = activity_own_id;
	}
	
	

}
