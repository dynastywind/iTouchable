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
@Results({@Result(name="success",type="redirect",location="/index.jsp")})
public class LogoutAction extends BaseAction {

	@Override
	@Action("logout")
	public String execute() throws Exception {
		session.remove("user");
		return SUCCESS;
	}

}
