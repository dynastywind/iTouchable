package com.ecust.action.zone.ajax;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.UserDao;

/**
 * 学生登录
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results({@Result(type="json")})
public class UserLoginAction extends BaseAction{
	
	private String id;
	
	private String password;
	
	private int pass;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	@Action("userLogin")
	public String execute() throws Exception {
		
		if(userDao.isExist(id)){
			if(userDao.validateLogin(id, password)){
				session.put("user", userDao.getIdByNumber(id));//将用户id保存至session
				pass = 1;//登录成功
			}else{
				pass = 0;//帐号或密码错误
			}
		}else{
			pass = 2;//帐号不存在
		}
		return SUCCESS;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}

}
