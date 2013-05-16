package com.ecust.model.temp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.ecust.model.zone.Comment;

public class ActivityTemp {
	
	private long activity_id;
	
	private Date activity_date;
	
	private String activity_text;
	
	private int activity_share_count;
	
	private String activity_ip;
	
	private boolean activity_is_finish;
	
	private String activity_a_type;
	
	private boolean activity_is_essence;
	
	private boolean activity_in_zone;
	
	private long user_own_id;
	
	private String user_own_name;
	
	private String user_own_picture;
	
	private Set<Comment> comment = new HashSet<Comment>();
	
	public ActivityTemp(long activity_id, Date activity_date,
			String activity_text, int activity_share_count, String activity_ip,
			boolean activity_is_finish, String activity_a_type,
			boolean activity_is_essence, boolean activity_in_zone,
			long user_own_id, String user_own_name, String user_own_picture,
			Set<Comment> comment) {
		super();
		this.activity_id = activity_id;
		this.activity_date = activity_date;
		this.activity_text = activity_text;
		this.activity_share_count = activity_share_count;
		this.activity_ip = activity_ip;
		this.activity_is_finish = activity_is_finish;
		this.activity_a_type = activity_a_type;
		this.activity_is_essence = activity_is_essence;
		this.activity_in_zone = activity_in_zone;
		this.user_own_id = user_own_id;
		this.user_own_name = user_own_name;
		this.user_own_picture = user_own_picture;
		this.comment = comment;
	}

	public long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}

	public Date getActivity_date() {
		return activity_date;
	}

	public void setActivity_date(Date activity_date) {
		this.activity_date = activity_date;
	}

	public String getActivity_text() {
		return activity_text;
	}

	public void setActivity_text(String activity_text) {
		this.activity_text = activity_text;
	}

	public int getActivity_share_count() {
		return activity_share_count;
	}

	public void setActivity_share_count(int activity_share_count) {
		this.activity_share_count = activity_share_count;
	}

	public String getActivity_ip() {
		return activity_ip;
	}

	public void setActivity_ip(String activity_ip) {
		this.activity_ip = activity_ip;
	}

	public boolean isActivity_is_finish() {
		return activity_is_finish;
	}

	public void setActivity_is_finish(boolean activity_is_finish) {
		this.activity_is_finish = activity_is_finish;
	}

	public String getActivity_a_type() {
		return activity_a_type;
	}

	public void setActivity_a_type(String activity_a_type) {
		this.activity_a_type = activity_a_type;
	}

	public boolean isActivity_is_essence() {
		return activity_is_essence;
	}

	public void setActivity_is_essence(boolean activity_is_essence) {
		this.activity_is_essence = activity_is_essence;
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

	public boolean isActivity_in_zone() {
		return activity_in_zone;
	}

	public void setActivity_in_zone(boolean activity_in_zone) {
		this.activity_in_zone = activity_in_zone;
	}

	//学习动态的构造函数
	public ActivityTemp(long activity_id, Date activity_date,
			String activity_text, int activity_share_count, String activity_ip, String activity_a_type,
			long user_own_id, String user_own_name, String user_own_picture) {
		super();
		this.activity_id = activity_id;
		this.activity_date = activity_date;
		this.activity_text = activity_text;
		this.activity_share_count = activity_share_count;
		this.activity_ip = activity_ip;
		this.activity_a_type = activity_a_type;
		this.user_own_id = user_own_id;
		this.user_own_name = user_own_name;
		this.user_own_picture = user_own_picture;
	}

	//话题和所有动态的构造函数
	public ActivityTemp(long activity_id, Date activity_date,
			String activity_text, int activity_share_count, String activity_ip,
			boolean activity_is_finish, String activity_a_type,
			boolean activity_is_essence, boolean activity_in_zone,
			long user_own_id, String user_own_name, String user_own_picture) {
		super();
		this.activity_id = activity_id;
		this.activity_date = activity_date;
		this.activity_text = activity_text;
		this.activity_share_count = activity_share_count;
		this.activity_ip = activity_ip;
		this.activity_is_finish = activity_is_finish;
		this.activity_a_type = activity_a_type;
		this.activity_is_essence = activity_is_essence;
		this.activity_in_zone = activity_in_zone;
		this.user_own_id = user_own_id;
		this.user_own_name = user_own_name;
		this.user_own_picture = user_own_picture;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

	
}
