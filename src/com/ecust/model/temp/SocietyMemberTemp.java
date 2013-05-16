package com.ecust.model.temp;

import java.util.Date;

/**
 * 用于查询大厅成员信息
 * @author lbz
 *
 */
public class SocietyMemberTemp {

	private long member_id;
	
	private Date member_date;
	
	private int member_level;
	
	private boolean member_silence;
	
	private int member_role;
	
	private long invitor_id;//邀请发出者
	
	private long user_id;
	
	private String user_name;
	
	private String user_picture;
	
	private String user_gender;
	
	private String user_college;
	
	private String user_grade;
	
	private long society_id;
	
	private String society_name;

	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}

	public Date getMember_date() {
		return member_date;
	}

	public void setMember_date(Date member_date) {
		this.member_date = member_date;
	}

	public int getMember_level() {
		return member_level;
	}

	public void setMember_level(int member_level) {
		this.member_level = member_level;
	}

	public boolean isMember_silence() {
		return member_silence;
	}

	public void setMember_silence(boolean member_silence) {
		this.member_silence = member_silence;
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

	public String getUser_picture() {
		return user_picture;
	}

	public void setUser_picture(String user_picture) {
		this.user_picture = user_picture;
	}

	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}

	public String getSociety_name() {
		return society_name;
	}

	public void setSociety_name(String society_name) {
		this.society_name = society_name;
	}
	
	//成员的简单信息
	public SocietyMemberTemp(int member_role, long user_id,
			String user_name) {
		super();
		this.member_role = member_role;
		this.user_id = user_id;
		this.user_name = user_name;
	}
	
	
	
	

	public SocietyMemberTemp() {
	}
	
	//成员的详细信息
	public SocietyMemberTemp(long member_id, Date member_date,
			int member_level, boolean member_silence, int member_role,
			long user_id, String user_name, String user_picture,
			String user_gender, String user_college, String user_grade) {
		super();
		this.member_id = member_id;
		this.member_date = member_date;
		this.member_level = member_level;
		this.member_silence = member_silence;
		this.member_role = member_role;
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_picture = user_picture;
		this.user_gender = user_gender;
		this.user_college = user_college;
		this.user_grade = user_grade;
	}
	
	public int getMember_role() {
		return member_role;
	}

	public void setMember_role(int member_role) {
		this.member_role = member_role;
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

	public String getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(String user_grade) {
		this.user_grade = user_grade;
	}

	public long getInvitor_id() {
		return invitor_id;
	}

	public void setInvitor_id(long invitor_id) {
		this.invitor_id = invitor_id;
	}
	
	

	
}
