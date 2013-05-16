package com.ecust.model.zone;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 管理员角色
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="t_admin")
public class Admin implements Serializable {
	
	@Id
	@GenericGenerator(name = "generator",strategy = "increment")
	@GeneratedValue(generator = "generator")
	private long id;
	
	private String account;//帐号，英文数字
	
	private String password;

	private Date last_login;
	
	private int login_times;
	
	private Date date;

	public Admin() {
		super();
	}

	public Admin(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public int getLogin_times() {
		return login_times;
	}

	public void setLogin_times(int login_times) {
		this.login_times = login_times;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}