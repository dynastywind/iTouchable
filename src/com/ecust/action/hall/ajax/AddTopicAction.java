package com.ecust.action.hall.ajax;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.hall.HallBaseAction;
import com.ecust.dao.zone.ActivityDao;
import com.ecust.dao.zone.SocietyDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Activity;
import com.ecust.model.zone.User;

@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results({@Result(type="json")})
public class AddTopicAction extends HallBaseAction{
	
	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private SocietyDao societyDao;
	
	@Autowired
	private UserDao userDao;
	
	private String topic_text = "";//发布的内容
	
	private long society_id;
	
	@Action("addTopicOnHall")
	public String execute() throws Exception{
		if(!topic_text.equals("")){
			Activity activity = new Activity();
			String ip = request.getRemoteAddr();
			long user_id = (Long) session.get("user");
			User user_own = userDao.getMiniModelById("User",user_id);//发布者
			activity.setIp(ip);
			activity.setText(topic_text);
			activity.setUser_own(user_own);
			activity.setSociety_own(societyDao.getMiniModelById("Society", society_id));
			activity.setA_type("t");
			activity.setIn_zone(false);
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

	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}



}
