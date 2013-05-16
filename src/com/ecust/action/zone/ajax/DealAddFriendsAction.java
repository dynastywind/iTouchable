package com.ecust.action.zone.ajax;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.FriendsDao;
import com.ecust.dao.zone.MessageDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.temp.FriendsTemp;
import com.ecust.model.zone.Friends;
import com.ecust.model.zone.Message;
import com.ecust.model.zone.User;


@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class DealAddFriendsAction extends BaseAction {
	
	@Autowired
	private FriendsDao friendsDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessageDao messageDao;
	
	private long message_id;
	
	private String deal;
	
	private long group_id;
	
	@Action("dealAddfriends")
	public String execute() throws Exception {
		
		messageDao.setMessageRead(message_id);//更新为已读
		
		FriendsTemp friends = friendsDao.getFriendsByMessage(message_id);
		User friend = userDao.getMiniModelById("User", friends.getUser_id());
		User owner = userDao.getMiniModelById("User", friends.getOwn_id());
		
		Message message_new = new Message();//回复给申请者消息
		message_new.setCreate_date(new Date());
		message_new.setFrom_user(friend);
		message_new.setTo_user(owner);
		message_new.setMessage_type(4);//4代表系统消息
		
		if(deal.equals("yes")){
			message_new.setText("同意了您的好友请求！");
			friendsDao.updateLink(friends.getFriends_id(), true);
			if(!friendsDao.isFriends(friends.getUser_id(), friends.getOwn_id())){//当还不是好友关系时
				Friends friends_new = new Friends();
				friends_new.setDate(new Date());
				friends_new.setFriends(owner);
				friends_new.setOwner(friend);
				friends_new.setIs_Link(true);
				if(group_id>0){
					friendsDao.updateFriendsGroup(friends.getFriends_id(), group_id);//好友分组
				}
				
				friendsDao.save(friends_new);//保存申请接收者的好友关系
			}
			
		}else if(deal.equals("no")){
			message_new.setText("拒绝了您的好友请求！");
			friendsDao.updateLink(friends.getFriends_id(), false);
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
