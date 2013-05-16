package com.ecust.action.hall.ajax;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.hall.HallBaseAction;
import com.ecust.dao.zone.SocietyDao;
import com.ecust.dao.zone.SocietyNoticeDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.SocietyNotice;


@SuppressWarnings("serial")
@Namespace("/student")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class AddNoticeAction extends HallBaseAction {

	@Autowired
	private SocietyDao societyDao;
	
	@Autowired
	private SocietyNoticeDao noticeDao;
	
	@Autowired
	private UserDao userDao;
	
	private String text;
	
	private long society_id;
	
	@Override
	@Action("addNotice")
	public String execute() throws Exception {
		if(text.length()>0 && validateAdminAuthority(society_id)){ //当内容不为空，二级权限
			SocietyNotice notice = new SocietyNotice();
			
			notice.setContent(text);
			notice.setCreatedate(new Date());
			notice.setWriter_name(userDao.getSimpleUserById((Long) session.get("user")).getName());
			notice.setWriter_type(getRoleSession(society_id));
			notice.setSociety_own(societyDao.getMiniModelById("Society", society_id));
			
			noticeDao.save(notice);
		}
		
		
		return SUCCESS;
	}

	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



}
