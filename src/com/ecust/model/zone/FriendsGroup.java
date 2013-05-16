package com.ecust.model.zone;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 好友分组
 * @author lbz
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name="t_friendsgroup")
public class FriendsGroup implements Serializable{
	
	@Id
	@GenericGenerator(name="generator",strategy="increment")
	@GeneratedValue(generator="generator")
	private long id;
	
	@Column(length=36)
	private String group_name;
	
	@ManyToOne
	@JoinColumn(name="user_own")
	private User user_own;//分组属于用户
	
	@OneToMany(mappedBy="friendsgroup_own",fetch = FetchType.LAZY)
	private Set<Friends> friends_list = new HashSet<Friends>();//分组包含的用户
	
	public FriendsGroup() {
		super();
	}
	
	public FriendsGroup(long id) {
		super();
		this.id = id;
	}
	
	public FriendsGroup(long id, String group_name) {
		super();
		this.id = id;
		this.group_name = group_name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public User getUser_own() {
		return user_own;
	}

	public void setUser_own(User user_own) {
		this.user_own = user_own;
	}

	public Set<Friends> getFriends_list() {
		return friends_list;
	}

	public void setFriends_list(Set<Friends> friends_list) {
		this.friends_list = friends_list;
	}

}
