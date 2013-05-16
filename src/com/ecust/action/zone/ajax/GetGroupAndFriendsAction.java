package com.ecust.action.zone.ajax;

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
public class GetGroupAndFriendsAction extends BaseAction {

	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	@Autowired
	private FriendsDao friendsDao;
	
	private String returnList = "";//返回list
	
	@Action("getGroupAndFriends")
	public String execute() throws Exception {
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";//用于生成图片路径
		
		long user_id = (Long) session.get("user");
		
		List<FriendsTemp> friends_list = new ArrayList<FriendsTemp>();
		
		/*================= 最近联系 ================*/
		returnList = "<ul id='friends_latest_contact'>";
		
		returnList = returnList + "</ul>";
				
		/*================= 当前在线 ================*/
		returnList = returnList + "<div class='_Friends_list_title'>好友列表</div>"+
			"<div class='_Friends_group_title'>当前在线(0)</div>"+
			"<ul class='_Friends_group_friends'>";
		
		returnList = returnList + "</ul>";
		
		/*================= 未分组 ================*/
		friends_list = friendsDao.getUngroupedFriendsTemp(user_id);
		Iterator<FriendsTemp> iter_ungrouped = friends_list.iterator();
		returnList = returnList + "<div class='_Friends_group_title'>未分组(0/"+friends_list.size()+")</div><ul class='_Friends_group_friends _Friends_hide'>";
		while(iter_ungrouped.hasNext()){
			FriendsTemp user_ungrouped = iter_ungrouped.next();
			returnList = returnList + "<li user_id = '" + user_ungrouped.getUser_id() + "'><img src='"+ basePath +"user/userfile/10103278/img/examle.jpg' width='20' height='20' class='_Friends_head_img' alt=''/>" +
					" <span>"+user_ungrouped.getUser_name()+"</span></li>";
		}
		returnList = returnList + "</ul>";
		
		/*================= 好友分组 ================*/
		Iterator<FriendsGroup> iter_group = friendsGroupDao.getAllFriendsGroup(user_id).iterator();//读取好友分组
		while(iter_group.hasNext()){
			FriendsGroup friendsGroup = iter_group.next();
			
			
			/*================= 该分组下的好友 ================*/
			friends_list = friendsDao.getFriendsTempByGroup(friendsGroup.getId());
			returnList = returnList + "<div class='_Friends_group_title'>"+friendsGroup.getGroup_name()+"(0/"+friends_list.size()+")</div><ul class='_Friends_group_friends _Friends_hide'>";
			Iterator<FriendsTemp> iter_friends = friends_list.iterator();
			while(iter_friends.hasNext()){
				FriendsTemp user_group = iter_friends.next();
				returnList = returnList + "<li user_id = '" + user_group.getUser_id() + "'><img src='"+ basePath +"user/userfile/10103278/img/examle.jpg' width='20' height='20' class='_Friends_head_img' alt=''/> " +
						"<span>"+ user_group.getUser_name() +"</span></li>";
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
