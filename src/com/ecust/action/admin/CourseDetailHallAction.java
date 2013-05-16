package com.ecust.action.admin;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ecust.action.common.BaseAction;

@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/admin")
@Results({@Result(name="success",location="/admin/courseDetail_hall.jsp"),@Result(name="error",type="redirect",location="/index.jsp")})
public class CourseDetailHallAction extends BaseAction{
	
	@Action("courseDetailHall")
	public String execute() throws Exception {
		return SUCCESS;
	}


}
