package com.ecust.action.hall;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ecust.action.common.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/user/hall")
@Results({@Result(name="success",location="/user/studyhall/activity.jsp"),@Result(name="error",type="redirect",location="/index.jsp")})
public class GetActivityAction extends BaseAction{
	
	@Action("getActivity")
	public String execute() throws Exception {
		
		return SUCCESS;
	}


}
