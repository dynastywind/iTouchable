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
public class CheckPasswordAction extends BaseAction{
	
	private String password;
	
	private int pass;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	@Action("checkPassword")
	public String execute() throws Exception {
		long id = (Long)session.get("user");
		if(userDao.isPassword(id, password)){
			pass = 1;//验证通过
		}else{
			pass = 0;//失败
		}	
		return SUCCESS;
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
