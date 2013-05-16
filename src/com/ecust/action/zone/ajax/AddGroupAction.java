package com.ecust.action.zone.ajax;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.FriendsGroup;
import com.ecust.model.zone.User;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/user")
@Results(@Result(type="json"))
public class AddGroupAction extends BaseAction {
	
	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	@Autowired
	private UserDao userDao;

	private String group_name = "";

	@Action("addGroup")
	public String execute() throws Exception{
		if(!group_name.equals("")){
			User user = userDao.get((Long)session.get("user"));//得到当前用户
			FriendsGroup friendsGroup = new FriendsGroup();
			friendsGroup.setGroup_name(group_name);
			friendsGroup.setUser_own(user);
			friendsGroupDao.save(friendsGroup);
		}
		
		return SUCCESS;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

}