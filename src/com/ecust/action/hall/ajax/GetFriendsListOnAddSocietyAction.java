package com.ecust.action.hall.ajax;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.FriendsDao;
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.model.temp.FriendsTemp;
import com.ecust.model.zone.FriendsGroup;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetFriendsListOnAddSocietyAction extends BaseAction {

	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	@Autowired
	private FriendsDao friendsDao;
	
	private String returnList = "";
	
	@Override
	@Action("getFriendsListOnAddSociety")
	public String execute() throws Exception {
		long user_id = (Long) session.get("user");
		
		List<FriendsTemp> friends_list = new ArrayList<FriendsTemp>();
		
		/*================= 未分组 ================*/
		friends_list = friendsDao.getUngroupedFriendsTemp(user_id);
		Iterator<FriendsTemp> iter_ungrouped = friends_list.iterator();
		returnList = returnList + "<div class='society_friends_group_title'><span>未分组("+friends_list.size()+")</span><input type='checkbox' class='society_friends_group_check' value='0' /></div><ul class='society_friends_ul'>";
		if(iter_ungrouped.hasNext()){
			while(iter_ungrouped.hasNext()){
				FriendsTemp user_ungrouped = iter_ungrouped.next();
				returnList = returnList + "<li user_id='"+user_ungrouped.getUser_id()+"'><span>"+user_ungrouped.getUser_name()+"</span><input type='checkbox' class='society_friends_check' value='"+user_ungrouped.getUser_id()+"' /></li>";
			}
		}else{
			returnList = returnList + "<span style='color:#aaa;'>暂无好友</span>";
		}
		
		returnList = returnList + "</ul>";
		
		/*================= 好友分组 ================*/
		Iterator<FriendsGroup> iter_group = friendsGroupDao.getAllFriendsGroup(user_id).iterator();//读取好友分组
		while(iter_group.hasNext()){
			FriendsGroup friendsGroup = iter_group.next();
			
			
			/*================= 该分组下的好友 ================*/
			friends_list = friendsDao.getFriendsTempByGroup(friendsGroup.getId());
			returnList = returnList + "<div class='society_friends_group_title'><span>"+friendsGroup.getGroup_name()+"("+friends_list.size()+")</span><input type='checkbox' class='society_friends_group_check' value='0' /></div><ul class='society_friends_ul' style='display:none;'>";
			Iterator<FriendsTemp> iter_friends = friends_list.iterator();
			if(iter_friends.hasNext()){
				while(iter_friends.hasNext()){
					FriendsTemp user_group = iter_friends.next();
					returnList = returnList + "<li user_id='"+user_group.getUser_id()+"'><span>"+user_group.getUser_name()+"</span><input type='checkbox' class='society_friends_check' value='"+user_group.getUser_id()+"' /></li>";
				}
			}else{
				returnList = returnList + "<span style='color:#aaa;'>暂无好友</span>";
			}
			
			
			returnList = returnList + "</ul>";
			
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
