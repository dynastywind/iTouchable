package com.ecust.model.temp;

/**
 * 用于查询好友信息
 * @author lbz
 *
 */
public class FriendsTemp {

	private long friends_id;
	
	private long own_id;
	
	private long user_id;//好友的id

	private String user_name;
	
	private String user_gender;
	
	private String user_college;
	
	private String user_picture;
	
	private boolean user_state;
	
	private int level;

	public long getFriends_id() {
		return friends_id;
	}

	public void setFriends_id(long friends_id) {
		this.friends_id = friends_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public String getUser_college() {
		return user_college;
	}

	public void setUser_college(String user_college) {
		this.user_college = user_college;
	}

	public String getUser_picture() {
		return user_picture;
	}

	public void setUser_picture(String user_picture) {
		this.user_picture = user_picture;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public FriendsTemp(long friends_id, long user_id, String user_name,
			String user_gender, String user_college, String user_picture, boolean user_state,
			int level) {
		super();
		this.friends_id = friends_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_gender = user_gender;
		this.user_college = user_college;
		this.user_picture = user_picture;
		this.user_state = user_state;
		this.level = level;
	}

	public FriendsTemp() {
		super();
	}

	public boolean isUser_state() {
		return user_state;
	}

	public void setUser_state(boolean user_state) {
		this.user_state = user_state;
	}

	public long getOwn_id() {
		return own_id;
	}

	public void setOwn_id(long own_id) {
		this.own_id = own_id;
	}

	public FriendsTemp(long friends_id, long own_id, long user_id) {
		super();
		this.friends_id = friends_id;
		this.own_id = own_id;
		this.user_id = user_id;
	}
	
}
