package com.ecust.action.zone.ajax;

import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.FriendsDao;
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.model.zone.Friends;


@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/user")
@Results(@Result(type="json"))
public class DelGroupAction extends BaseAction{

	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	@Autowired
	private FriendsDao friendsDao;
	
	private long group_id;
	
	@Action("delGroup")
	public String execute() throws Exception {
		Iterator<Friends> iter = friendsDao.getFriendsByGroup(group_id).iterator();
		while(iter.hasNext()){
			Friends friends = iter.next();
			friends.setFriendsgroup_own(null);
			friendsDao.update(friends);
		}
		friendsGroupDao.delete(group_id);
		return SUCCESS;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}
}
