package com.ecust.dao.zone;

import com.ecust.model.temp.MessageTemp;
import com.ecust.model.zone.Message;
import com.ecust.util.Page;

public interface MessageDao extends BaseDao<Message,Long>{

	/**
	 * 按用户获得消息
	 * @param user_id
	 * @param page
	 * @return
	 */
	public Page getNoreadMessageByUserForPage(long user_id,int page);
	
	/**
	 * 按类型获得消息
	 * @param user_id
	 * @param types
	 * @param page
	 * @return
	 */
	public Page getMessageByType(long user_id,int types,int page);
	
	/**
	 * 是否存在聊天消息
	 * @param user_from
	 * @param user_to
	 * @return
	 */
	public boolean exitChatMessage(long user_from,long user_to);
	
	/**
	 * 从发出者获得两用户间的聊天消息
	 * @param user_from
	 * @param user_to
	 * @return
	 */
	public Message getChatMessageByTwoUser(long user_from,long user_to);
	
	
	/**
	 * 是否已发出好友申请，且对方还未处理
	 * @param user_from
	 * @param user_to
	 * @return
	 */
	public boolean isApplyForFriends(long user_from,long user_to);
	
	/**
	 * 标记已读
	 * @param message_id
	 * @return
	 */
	public int setMessageRead(long message_id);
	
	/**
	 * 按邀请信息获得大厅成员联系
	 * @param message_id
	 * @return long message_id, long from_user_id, long member_id
	 */
	public MessageTemp getMemberLinkByMessage(long message_id);
	
	/**
	 * 是否已发出大厅邀请
	 * @param society_id
	 * @param user_id
	 * @return
	 */
	public boolean isInviteMember(long society_id,long user_id);
}
