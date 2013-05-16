package com.ecust.action.hall;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/user/hall")
@Results({@Result(name="success",location="/user/studyhall/admin.jsp"),@Result(name="error",type="redirect",location="/index.jsp")})
public class AdminHallAction extends HallBaseAction{
	
	private long society_id;
	
	@Action("adminHall")
	public String execute() throws Exception {
		if(validateAdminAuthority(society_id)){ //二级权限
			return SUCCESS;
		}else{
			return ERROR;
		}
	}


	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}

	

}
