package com.ecust.action.zone.ajax;

import java.util.Iterator;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.ChatDao;
import com.ecust.model.zone.Chat;
import com.ecust.model.zone.Message;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class DelChatAction extends BaseAction {

	@Autowired
	private ChatDao chatDao;
	
	private long message_id;
	
	private long chat_id;
	
	@Action("delChatAction")
	public String execute() throws Exception {
		Chat chat = chatDao.get(chat_id);
		Set<Message> messageSet = chat.getMessage_own();
		Iterator<Message> iter = messageSet.iterator();
		Message message = new Message();
		while(iter.hasNext()){//寻找出message
			Message m = iter.next();
			if(m.getId() == message_id){
				message = m;
			}
		}
		messageSet.remove(message);
		if(messageSet.size() > 0){
			chat.setMessage_own(messageSet);
			chatDao.update(chat);//解除与指定message的关联
		}else{
			chatDao.delete(chat);//删除聊天消息
		}
		
		return SUCCESS;
	}

	public long getChat_id() {
		return chat_id;
	}

	public void setChat_id(long chat_id) {
		this.chat_id = chat_id;
	}

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}
}
