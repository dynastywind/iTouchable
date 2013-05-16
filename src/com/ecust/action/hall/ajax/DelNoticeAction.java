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
 * 获得前3条公告
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class DelNoticeAction  extends BaseAction {

	@Autowired
	private SocietyNoticeDao societyNoticeDao;
	
	private long notice_id;
	
	@Override
	@Action("delNotice")
	public String execute() throws Exception {
		societyNoticeDao.delete(notice_id);
		return SUCCESS;
	}

	public long getNotice_id() {
		return notice_id;
	}


	public void setNotice_id(long notice_id) {
		this.notice_id = notice_id;
	}

}
