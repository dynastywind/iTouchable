package com.ecust.action.zone.ajax;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.ChatDao;
import com.ecust.dao.zone.MessageDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Chat;
import com.ecust.model.zone.Message;
import com.ecust.model.zone.User;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class AddChatAction extends BaseAction {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ChatDao chatDao;
	
	@Autowired
	private MessageDao messageDao;
	
	private String chat_text = "";
	
	private long friends_id;
	
	@Action("addChat")
	public String execute() throws Exception {
		if(!chat_text.equals("")){//消息内容不为空时才保存
			long user_id = (Long) session.get("user");
			Chat chat = new Chat();
			User from_user = userDao.getMiniModelById("User", user_id);
			User to_user = userDao.getMiniModelById("User",friends_id);
			
			chat.setDate(new Date());
			chat.setText(chat_text);
			chat.setFrom_user(from_user);
			chat.setTo_user(to_user);
			
			Message message_to = new Message();
			if(messageDao.exitChatMessage(user_id, friends_id)){//保存到“接收者”的消息记录
				message_to = messageDao.getChatMessageByTwoUser(user_id, friends_id);
			}else{
				message_to.setCreate_date(new Date());
				message_to.setFrom_user(from_user);
				message_to.setTo_user(to_user);
				message_to.setMessage_type(1);//1表示“好友留言”
				
				messageDao.save(message_to);//为空时保存
			}
			Message message_from = new Message();
			if(messageDao.exitChatMessage(friends_id, user_id)){//保存到“发送者”的消息记录
				message_from = messageDao.getChatMessageByTwoUser(friends_id, user_id);
			}else{
				message_from.setCreate_date(new Date());
				message_from.setFrom_user(to_user);
				message_from.setTo_user(from_user);
				message_from.setMessage_type(1);//1表示“好友留言”
				
				messageDao.save(message_from);//为空时保存
			}
			
			chat.addMessage(message_to);
			chat.addMessage(message_from);
			chatDao.save(chat);
		}
		
		return SUCCESS;
	}
	
	public String getChat_text() {
		return chat_text;
	}

	public void setChat_text(String chat_text) {
		this.chat_text = chat_text;
	}

	public long getFriends_id() {
		return friends_id;
	}

	public void setFriends_id(long friends_id) {
		this.friends_id = friends_id;
	}

}
