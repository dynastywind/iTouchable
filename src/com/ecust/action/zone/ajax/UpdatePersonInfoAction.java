package com.ecust.action.zone.ajax;

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
@ParentPackage("json-default")
@Namespace("/user")
@Results(@Result(type="json"))
public class UpdatePersonInfoAction extends BaseAction{

	@Autowired
	private UserDao userDao;
	
	private User user;
	
	@Action("updatePersonInfo")
	public String execute() throws Exception{
		if(!user.getName().equals("")){ //姓名不能为空
			long user_id = (Long) session.get("user");
			userDao.updatePersonInfo(user_id, user.getName(), user.getGender(), user.getBirth(), user.getSchool(), user.getCollege(),
					user.getCity(), user.getGrade(), user.getClasses(), user.getEmail(), user.getTel(), user.getQq());
		}
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}
