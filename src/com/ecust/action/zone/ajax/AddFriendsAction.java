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
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.dao.zone.MessageDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Friends;
import com.ecust.model.zone.FriendsGroup;
import com.ecust.model.zone.Message;
import com.ecust.model.zone.User;

/**
 * 好友申请
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/user")
@Results(@Result(type="json"))
public class AddFriendsAction extends BaseAction{

	@Autowired
	private FriendsDao friendsDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	@Autowired
	private UserDao userDao;
	
	private long friends_id;
	
	private long group_id;
	
	private String msg_text;//申请留言
	
	@Action("addFriends")
	public String execute() throws Exception {
		long user_id = (Long) session.get("user");
		if(!messageDao.isApplyForFriends(user_id, friends_id)){//当还未发出好友申请时才保存
			User user_own = userDao.getMiniModelById("User",user_id);//申请发出者
			User user_friends = userDao.getMiniModelById("User",friends_id);//好友
			
			Friends friends = new Friends();
			Message message = new Message();
			
			try {
				FriendsGroup friendsGroup = friendsGroupDao.getMiniModelById("FriendsGroup",group_id);
				friends.setFriendsgroup_own(friendsGroup);
			} catch (Exception e) {}
			
			friends.setDate(new Date());
			friends.setOwner(user_own);
			friends.setFriends(user_friends);
			
			friends.setMessage(message);
			message.setCreate_date(new Date());
			message.setFrom_user(user_own);
			message.setTo_user(user_friends);
			message.setMessage_type(2);
			message.setText(msg_text);
			message.setFriends(friends);
			
			friendsDao.save(friends);
			messageDao.save(message);
		}
		
		return SUCCESS;
	}

	public long getFriends_id() {
		return friends_id;
	}

	public void setFriends_id(long friends_id) {
		this.friends_id = friends_id;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public String getMsg_text() {
		return msg_text;
	}

	public void setMsg_text(String msg_text) {
		this.msg_text = msg_text;
	}
}
