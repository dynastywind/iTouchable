package com.ecust.action.zone;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.User;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("base")
@Results({@Result(name="success",location="/user/studentCenter.jsp"),@Result(name="error",type="redirect",location="/index.jsp")})
public class StudentCenterAction extends BaseAction{

	@Autowired
	private UserDao userDao;
	
	private User student;
	
	@Override
	@Action("studentCenter")
	public String execute() {
		try {
			long user_id = (Long) session.get("user");
			student = userDao.getSimpleUserById(user_id);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

}
