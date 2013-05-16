package com.ecust.dao.zone.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.MessageDao;
import com.ecust.model.temp.MessageTemp;
import com.ecust.model.zone.Message;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl<Message,Long> implements MessageDao {

	public MessageDaoImpl() {
		super(Message.class);
	}

	@Override
	public Page getNoreadMessageByUserForPage(long user_id, int page) {
		String hql="from Message message where is_read = false and message.to_user.id=? order by message.id desc";
		int pageSize = 10;
		int pageNum = 5;
		return listForPage(hql,new Object[]{user_id}, pageSize, page, pageNum);
	}

	//按类型获得消息
	@Override
	public Page getMessageByType(long user_id, int types, int page) {
		String hql = "from Message message where message.message_type =? and message.to_user.id=? order by message.id desc";
		int pageSize = 10;
		int pageNum = 5;
		return listForPage(hql,new Object[]{types,user_id}, pageSize, page, pageNum);
	}


	//从发出者获得两用户间的聊天消息
	@Override
	public Message getChatMessageByTwoUser(long user_from, long user_to) {
		try {
			String hql = "select new Message(message.id) from Message message where message.message_type=1 and message.from_user.id=? and message.to_user.id=?";
			return getList(hql, new Object[]{user_from,user_to}).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	//是否存在聊天消息
	@Override
	public boolean exitChatMessage(long user_from, long user_to) {
		return validate("select count(*) from Message message where message.message_type=1 and message.from_user.id=? and message.to_user.id=?",new Object[]{user_from,user_to});
	}

	//是否已发出好友申请，且对方还未处理
	@Override
	public boolean isApplyForFriends(long user_from, long user_to) {
		return validate("select count(*) from Message message where message.message_type=2 and message.is_read = false and message.from_user.id=? and message.to_user.id=?",new Object[]{user_from,user_to});
	}

	//标记已读
	@Override
	public int setMessageRead(long message_id) {
		String hql = "update Message m set m.is_read = true where m.id = ?";
		return getQuery(hql, new Object[]{message_id}).executeUpdate();
	}
	
	//按邀请信息获得大厅成员联系
	@Override
	public MessageTemp getMemberLinkByMessage(long message_id) {
		String hql = "select new com.ecust.model.temp.MessageTemp(m.id, m.from_user.id, m.member.id) from Message m where m.id = ?";
		return (MessageTemp) getTempList(hql, new Object[]{message_id}).get(0);
	}

	//是否已发出大厅邀请
	@Override
	public boolean isInviteMember(long society_id, long user_id) {
		String hql = "select count(*) from Message m where m.message_type=3 and m.is_read = false and m.member.society_own.id=? and m.to_user.id=?";
		return validate(hql,new Object[]{society_id,user_id});
	}	

	
}
