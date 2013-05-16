package com.ecust.action.zone.ajax;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.FriendsGroupDao;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/user")
@Results(@Result(type="json"))
public class UpdateGroupNameAction extends BaseAction{

	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	private long group_id;
	
	private String group_name;
	
	@Action("updateGroupName")
	public String execute() throws Exception{
		friendsGroupDao.updateGroupName(group_id, group_name);
		return SUCCESS;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
}
