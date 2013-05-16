package com.ecust.action.zone.ajax;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.ActivityDao;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class DelTopicAction extends BaseAction {

	@Autowired
	private ActivityDao activityDao;
	
	private long activity_id;
	
	@Action("delTopic")
	public String execute() throws Exception {
		activityDao.delete(activity_id);
		return SUCCESS;
	}


	public long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}
}
