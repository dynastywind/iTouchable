package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户模型
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name = "user_type",
		discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("user")
@Table(name="t_user")
public class User implements Serializable{
	
	@Id
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	private boolean is_teacher = false;
	
	@Column(length=16)
	private String id_number;
	@Column(length=64)
	private String name;
	@Column(length=32)
	private String password;
	@Column(length=8)
	private String gender;
	
	private Date birth;
	@Column(length=64)
	private String school;
	@Column(length=64)
	private String college;
	@Column(length=32)
	private String city;
	
	private String grade;//年级，如 2010
	
	@Column(length=32)
	private String classes;

	//是否在线
	private boolean state;
	@Column(length=64)
	private String email;
	@Column(length=12)
	private String tel;
	@Column(length=16)
	private String qq;
	@Column(length=128)
	private String picture;
	
	private Date last_login;
	
	private int rank;
	
	private int point;
	
	private int friend_count;
	
	private int login_times;
	
	private Date date;	//帐号添加时间
	
	@OneToMany(mappedBy="owner",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
	private Set<Friends> owner = new HashSet<Friends>();//对应关系
	
	@OneToMany(mappedBy="friends",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
	private Set<Friends> friends = new HashSet<Friends>();//好友关系
	
	@OneToMany(mappedBy="user_own",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
	private Set<FriendsGroup> friendsgroup = new HashSet<FriendsGroup>();//好友分组
	
	@OneToMany(mappedBy="user_own",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
	private Set<Activity> activity = new HashSet<Activity>();//用户发布的话题、学习行为
	
	@OneToMany(mappedBy="user_own",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
	private Set<Comment> comment_own = new HashSet<Comment>();//发布话题的评论
	
	@OneToMany(mappedBy="from_user",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
	private Set<Message> send_message = new HashSet<Message>();//发出的消息
	
	@OneToMany(mappedBy="to_user",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE })
	private Set<Message> receive_message = new HashSet<Message>();//接收的消息
	
	@OneToMany(mappedBy="from_user",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
	private Set<Chat> send_chat = new HashSet<Chat>();//发出的聊天消息
	
	@OneToMany(mappedBy="to_user",fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE})
	private Set<Chat> receive_chat = new HashSet<Chat>();//接收的聊天消息
	
	@ManyToMany(mappedBy="user_own")
	private Set<SocietyMember> join_society = new HashSet<SocietyMember>();//加入的集合
	
	public User() {
		super();
	}
	
	public User(long id) {
		super();
		this.id = id;
	}

	public User(long id, String id_number, String name, String school,
			String classes, int rank, int point,String picture) {
		super();
		this.id = id;
		this.id_number = id_number;
		this.name = name;
		this.school = school;
		this.classes = classes;
		this.rank = rank;
		this.point = point;
		this.picture = picture;
	}
	
	

	public User(long id, String name, String gender, String college,
			boolean state, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.college = college;
		this.state = state;
		this.picture = picture;
	}
	
	public User(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Chat> getSend_chat() {
		return send_chat;
	}

	public void setSend_chat(Set<Chat> send_chat) {
		this.send_chat = send_chat;
	}

	public Set<Chat> getReceive_chat() {
		return receive_chat;
	}

	public void setReceive_chat(Set<Chat> receive_chat) {
		this.receive_chat = receive_chat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getSchool() {
		return school;
	}
	
	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public int getRank() {
		return rank;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getFriend_count() {
		return friend_count;
	}

	public void setFriend_count(int friend_count) {
		this.friend_count = friend_count;
	}

	public int getLogin_times() {
		return login_times;
	}

	public void setLogin_times(int login_times) {
		this.login_times = login_times;
	}

	public Set<Friends> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friends> friends) {
		this.friends = friends;
	}

	public Set<FriendsGroup> getFriendsgroup() {
		return friendsgroup;
	}

	public void setFriendsgroup(Set<FriendsGroup> friendsgroup) {
		this.friendsgroup = friendsgroup;
	}

	public Set<Friends> getOwner() {
		return owner;
	}

	public void setOwner(Set<Friends> owner) {
		this.owner = owner;
	}

	public boolean isIs_teacher() {
		return is_teacher;
	}

	public void setIs_teacher(boolean is_teacher) {
		this.is_teacher = is_teacher;
	}

	public Set<Comment> getComment_own() {
		return comment_own;
	}

	public void setComment_own(Set<Comment> comment_own) {
		this.comment_own = comment_own;
	}

	public Set<SocietyMember> getJoin_society() {
		return join_society;
	}

	public void setJoin_society(Set<SocietyMember> join_society) {
		this.join_society = join_society;
	}

	public Set<Message> getSend_message() {
		return send_message;
	}

	public void setSend_message(Set<Message> send_message) {
		this.send_message = send_message;
	}

	public Set<Message> getReceive_message() {
		return receive_message;
	}

	public void setReceive_message(Set<Message> receive_message) {
		this.receive_message = receive_message;
	}

	public Set<Activity> getActivity() {
		return activity;
	}

	public void setActivity(Set<Activity> activity) {
		this.activity = activity;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	//用户个人资料
	public User(boolean is_teacher, String id_number, String name,
			String gender, Date birth, String school, String college,
			String city, String grade, String classes, String email,
			String tel, String qq) {
		super();
		this.is_teacher = is_teacher;
		this.id_number = id_number;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.school = school;
		this.college = college;
		this.city = city;
		this.grade = grade;
		this.classes = classes;
		this.email = email;
		this.tel = tel;
		this.qq = qq;
	}

	//用于后台管理学生
	public User(long id, String id_number, String name, String school,
			String college, String grade, boolean state, Date last_login,
			int point, int login_times ,Date date) {
		super();
		this.id = id;
		this.id_number = id_number;
		this.name = name;
		this.school = school;
		this.college = college;
		this.grade = grade;
		this.state = state;
		this.last_login = last_login;
		this.point = point;
		this.login_times = login_times;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	//用于后台管理教师
	public User(long id, String id_number, String name, String school,
			String college, boolean state, Date last_login, int point, Date date) {
		super();
		this.id = id;
		this.id_number = id_number;
		this.name = name;
		this.school = school;
		this.college = college;
		this.state = state;
		this.last_login = last_login;
		this.point = point;
		this.date = date;
	}

}
