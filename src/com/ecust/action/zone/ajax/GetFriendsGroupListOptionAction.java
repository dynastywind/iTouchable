package com.ecust.action.zone.ajax;

import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.model.zone.FriendsGroup;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetFriendsGroupListOptionAction extends BaseAction {

	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	private String returnList = "";//返回list
	
	@Action("getFriendsGroupListOption")
	public String execute() throws Exception {
		
		long user_id = (Long) session.get("user");
		
		Iterator<FriendsGroup> iter = friendsGroupDao.getAllFriendsGroup(user_id).iterator();//读取好友分组
		while(iter.hasNext()){
			FriendsGroup friendsGroup = iter.next();
			returnList = returnList + "<option value='"+friendsGroup.getId()+"'>"+friendsGroup.getGroup_name()+"</option>";
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
