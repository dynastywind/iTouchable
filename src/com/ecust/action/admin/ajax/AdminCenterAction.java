package com.ecust.action.admin.ajax;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.AdminDao;
import com.ecust.model.zone.Admin;

/**
 * 登录到学习大厅
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace("/admin")
@Results({@Result(name="success",location="/admin/index.jsp"),@Result(name="error",type="redirect",location="/index.jsp")})
public class AdminCenterAction extends BaseAction{
	
	@Autowired
	private AdminDao adminDao;
	
	private Admin admin;
	
	@Action("adminCenter")
	public String execute() throws Exception {
		String account = session.get("admin").toString();
		admin = adminDao.getAdminByCount(account);
		return SUCCESS;
		
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	
}
