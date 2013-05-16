package com.ecust.dao.zone;

import com.ecust.model.zone.Chat;
import com.ecust.util.Page;

public interface ChatDao extends BaseDao<Chat,Long>{

	/**
	 * 得到聊天记录
	 * @param message_id
	 * @param page
	 * @return
	 */
	public Page getChatByMessageForPage(long message_id,int page);
}
