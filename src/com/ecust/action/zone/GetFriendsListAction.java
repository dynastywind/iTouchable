package com.ecust.action.zone;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ecust.action.common.BaseAction;

/**
 * 好友列表
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("base")
@Results({@Result(name="success",location="/user/friends.jsp")})
public class GetFriendsListAction extends BaseAction {

	@Action("getFriendsList")
	@Override
	public String execute() throws Exception {
		return	SUCCESS;
	}


}
