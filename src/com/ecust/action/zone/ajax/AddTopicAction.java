package com.ecust.action.zone.ajax;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.ActivityDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Activity;
import com.ecust.model.zone.User;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results({@Result(type="json")})
public class AddTopicAction extends BaseAction{
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private UserDao userDao;
	
	private String topic_text = "";//发布的内容

	@Action("addTopic")
	public String execute() throws Exception{
		if(!topic_text.equals("")){
			Activity activity = new Activity();
			String ip = request.getRemoteAddr();
			long user_id = (Long) session.get("user");
			User user_own = userDao.getMiniModelById("User",user_id);//发布者
			activity.setIp(ip);
			activity.setText(topic_text);
			activity.setA_type("t");
			activity.setUser_own(user_own);
			activity.setDate(new Date());
			activityDao.save(activity);
		}
		
		return SUCCESS;
	}

	public String getTopic_text() {
		return topic_text;
	}

	public void setTopic_text(String topic_text) {
		this.topic_text = topic_text;
	}



}
