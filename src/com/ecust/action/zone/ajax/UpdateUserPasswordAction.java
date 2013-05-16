package com.ecust.action.zone.ajax;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.UserDao;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/user")
@Results(@Result(type="json"))
public class UpdateUserPasswordAction extends BaseAction{

	@Autowired
	private UserDao userDao;
	
	private String password;
	
	private int pass;
	
	private String old_pass;
	
	@Action("updateUserPassword")
	public String execute() throws Exception{
		long id = (Long)session.get("user");
		if(userDao.isPassword(id, old_pass)){
			userDao.updateUserPassword(id, password);
			setPass(1);//验证通过
		}else{
			setPass(0);//失败
		}	
		return SUCCESS;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOld_pass() {
		return old_pass;
	}

	public void setOld_pass(String old_pass) {
		this.old_pass = old_pass;
	}

	public int getPass() {
		return pass;
	}

	public void setPass(int pass) {
		this.pass = pass;
	}
}
