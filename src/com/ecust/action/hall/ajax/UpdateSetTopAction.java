package com.ecust.action.hall.ajax;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.SocietyNoticeDao;

/**
 * 
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class UpdateSetTopAction  extends BaseAction {

	@Autowired
	private SocietyNoticeDao societyNoticeDao;
	
	private long society_id;
	
	private boolean is_top;
	
	private long notice_id;
	
	@Override
	@Action("setTop")
	public String execute() throws Exception {
		societyNoticeDao.setTop(society_id, notice_id, !is_top);
		return SUCCESS;
	}


	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}


	public boolean isIs_top() {
		return is_top;
	}


	public void setIs_top(boolean is_top) {
		this.is_top = is_top;
	}


	public long getNotice_id() {
		return notice_id;
	}


	public void setNotice_id(long notice_id) {
		this.notice_id = notice_id;
	}

}
