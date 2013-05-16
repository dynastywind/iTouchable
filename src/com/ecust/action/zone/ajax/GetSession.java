package com.ecust.action.zone.ajax;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ecust.action.common.BaseAction;

@SuppressWarnings("serial")
@Namespace("/")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetSession extends BaseAction{

	private String sessionString;
	
	@Action("getSession")
	public String execute() throws Exception {
		try {
			setSessionString(session.get("user").toString());
		} catch (Exception e) {
			setSessionString("");
		}
		return SUCCESS;
	}
	
	public String getSessionString() {
		return sessionString;
	}

	public void setSessionString(String sessionString) {
		this.sessionString = sessionString;
	}


}
