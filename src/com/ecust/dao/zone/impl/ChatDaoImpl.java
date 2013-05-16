package com.ecust.dao.zone.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.ChatDao;
import com.ecust.model.zone.Chat;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("chatDao")
public class ChatDaoImpl extends BaseDaoImpl<Chat,Long> implements ChatDao{

	public ChatDaoImpl() {
		super(Chat.class);
	}
	
	@Override
	public Page getChatByMessageForPage(long message_id,int page) {
		String hql="select new com.ecust.model.temp.ChatTemp(chat.id, chat.date, chat.text," +
			"chat.is_read, chat.from_user.id, chat.from_user.name," +
			"chat.from_user.picture, chat.to_user.id, chat.to_user.name," +
			"chat.to_user.picture) from Chat chat join chat.message_own message where message.id=? order by chat.date DESC";
		int pageSize = 10;
		int pageNum = 5;
		return listForPage(hql,new Object[]{message_id}, pageSize, page, pageNum);
	}

}
