package com.ecust.action.zone.ajax;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.MessageDao;
import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.temp.MessageTemp;
import com.ecust.model.zone.Message;
import com.ecust.model.zone.User;


@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class DealHallInviteAction extends BaseAction {
	
	@Autowired
	private SocietyMemberDao memberDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserDao userDao;
	
	private long message_id;
	
	private String deal;
	
	private long group_id;
	
	@Action("dealHallInvite")
	public String execute() throws Exception {
		
		messageDao.setMessageRead(message_id);//更新为已读
		
		MessageTemp mTemp = messageDao.getMemberLinkByMessage(message_id);
		User from_u = userDao.getMiniModelById("User", mTemp.getFrom_user_id());
		User to_u = userDao.getMiniModelById("User",(Long)session.get("user"));
		
		Message message_new = new Message();//回复给发出者消息
		message_new.setCreate_date(new Date());
		message_new.setFrom_user(to_u);
		message_new.setTo_user(from_u);
		message_new.setMessage_type(4);//4代表系统消息
		
		if(deal.equals("yes")){
			message_new.setText("同意了学习大厅邀请！");
			memberDao.updateSetLink(mTemp.getMember_id(), true);
			
		}else if(deal.equals("no")){
			message_new.setText("拒绝了学习大厅邀请！");
		}
		
		messageDao.save(message_new);
		return SUCCESS;
	}

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	public String getDeal() {
		return deal;
	}

	public void setDeal(String deal) {
		this.deal = deal;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

}
