package com.ecust.action.hall.ajax;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.ActivityDao;
import com.ecust.dao.zone.CommentDao;
import com.ecust.model.temp.ActivityTemp;
import com.ecust.util.GetHTML;

/**
 * 大厅动态
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetActivityListAction extends BaseAction {

	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private CommentDao commentDao;
	
	private String movement_HTML="";
	
	private int page = 1;
	
	private long society_id;
	
	private long user_id;
	
	private String type;
	
	private int topic_type;
	
	@Override
	@Action("getActivityListOnSociety")
	public String execute() throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";//用于生成图片路径
		
		@SuppressWarnings("unchecked")
		List<ActivityTemp> list = activityDao.listActivityForPageByTypeOnSociety(user_id, society_id, type, topic_type, page).getList();
		movement_HTML = GetHTML.getTopicHTML(list, commentDao, basePath);
		
		return SUCCESS;
	}

	public String getMovement_HTML() {
		return movement_HTML;
	}

	public void setMovement_HTML(String movement_HTML) {
		this.movement_HTML = movement_HTML;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}
	
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTopic_type() {
		return topic_type;
	}

	public void setTopic_type(int topic_type) {
		this.topic_type = topic_type;
	}


}
