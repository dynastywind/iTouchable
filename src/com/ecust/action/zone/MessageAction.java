package com.ecust.action.zone;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ecust.action.common.BaseAction;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("base")
@Results({@Result(name="success",location="/user/message.jsp")})
public class MessageAction extends BaseAction{

	@Action("message")
	public String execute() throws Exception {
		return SUCCESS;
	}
}
