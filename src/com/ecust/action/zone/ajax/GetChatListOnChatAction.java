package com.ecust.action.zone.ajax;

import java.util.List;
import java.util.ListIterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.ChatDao;
import com.ecust.dao.zone.MessageDao;
import com.ecust.model.temp.ChatTemp;
import com.ecust.util.DateUtils;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetChatListOnChatAction extends BaseAction{

	@Autowired
	private ChatDao chatDao;
	
	@Autowired
	private MessageDao messageDao;
	
	private int page = 0;
	
	private long friends_id;
	
	private String chat_HTML = "";
	
	@Action("getChatListOnChat")
	public String execute() throws Exception {
		long user_id = (Long) session.get("user");
		try {
			long message_id = messageDao.getChatMessageByTwoUser(friends_id, user_id).getId();
			
			@SuppressWarnings("unchecked")
			List<ChatTemp> chatList = chatDao.getChatByMessageForPage(message_id, page).getList();
			ListIterator<ChatTemp> iter = chatList.listIterator(chatList.size());
			while(iter.hasPrevious()){
				ChatTemp  chat = iter.previous();
				if(chat.getFrom_user_id() == user_id){
					chat_HTML = chat_HTML + "<div class='_Message_other_list_div'>"+
							"<div class='_Message_time_left_div'>"+DateUtils.format(chat.getChat_date(),DateUtils.FORMAT_MIDDLE)+"</div>"+
								"<div class='_Message_point_img_left'></div>"+
							"<div class='_Message_other_text_div'>"+chat.getChat_text()+"</div>"+			
							"</div>";
				}else{
					chat_HTML = chat_HTML + "<div class='_Message_my_list_div'>"+
							"<div class='_Message_time_right_div'>"+DateUtils.format(chat.getChat_date(),DateUtils.FORMAT_MIDDLE)+"</div>"+
								"<div class='_Message_point_img'></div>"+
							"<div class='_Message_my_text_div'>"+chat.getChat_text()+"</div>"+			
							"</div>";
				}
				
			}
			
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getFriends_id() {
		return friends_id;
	}

	public void setFriends_id(long friends_id) {
		this.friends_id = friends_id;
	}

	public String getChat_HTML() {
		return chat_HTML;
	}

	public void setChat_HTML(String chat_HTML) {
		this.chat_HTML = chat_HTML;
	}

}
