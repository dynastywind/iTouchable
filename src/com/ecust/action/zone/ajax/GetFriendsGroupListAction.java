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
import com.ecust.model.zone.FriendsGroup;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetFriendsGroupListAction extends BaseAction {

	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	@Autowired
	private FriendsDao friendsDao;
	
	private String returnList = "";//返回list
 
	@Action("getFriendsGroupList")
	public String execute() throws Exception {
		
		long user_id = (Long) session.get("user");
		int all_friends_count = friendsDao.getAllFriendsIdByUser(user_id).size();
		
		int ungrouped = friendsDao.getUngroupedFriendsTemp(user_id).size();
		
		Iterator<FriendsGroup> iter = friendsGroupDao.getAllFriendsGroup(user_id).iterator();//读取好友分组
		returnList = "<li id='0' class='comtact_curr_li'>所有好友("+all_friends_count+")</li><li id='-1' class=''>未分组("+ ungrouped +")</li>";
		
		int friends_count = 0;
		
		while(iter.hasNext()){
			FriendsGroup friendsGroup = iter.next();
			friends_count = friendsDao.getFriendsByGroup(friendsGroup.getId()).size();
			returnList = returnList + "<li id='"+friendsGroup.getId()+"'>"+friendsGroup.getGroup_name()+"("+friends_count+")" +
				"</li><span class='group_edit_ico'>编辑</span>" +
				"<div class='edit_div'><a href='javascript:;' id='"+friendsGroup.getId()+"' class='group_rename' title='重命名'>重命名</a>" +
					"<a href='javascript:;' class='group_delete' id='"+friendsGroup.getId()+"' title='删除该组'>删除该组</a></div>";
		}
		
		return SUCCESS;
	}

	public String getReturnList() {
		return returnList;
	}

	public void setReturnList(String returnList) {
		this.returnList = returnList;
	}

}
