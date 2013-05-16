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
import com.ecust.dao.zone.CommentDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Activity;
import com.ecust.model.zone.Comment;
import com.ecust.model.zone.User;


@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class AddCommentAction extends BaseAction {

	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ActivityDao activityDao;
	
	private String comment_text = "";
	
	private long activity_id;
	
	private long at_id;
	
	@Action("addComment")
	public String execute() throws Exception{
		if(!comment_text.equals("")){
			String ip = request.getRemoteAddr();//得到ip地址
			long user_id = (Long) session.get("user");
			User user_own = userDao.getMiniModelById("User", user_id);//得到发布者
			Activity activity_own = activityDao.getMiniModelById("Activity", activity_id);//得到评论的话题
			Comment comment = new Comment();
			comment.setComment_text(comment_text);
			comment.setIp(ip);
			comment.setUser_own(user_own);
			comment.setActivity_own(activity_own);
			try {
				User user_at = userDao.getSimpleUserById(at_id);
				comment.setUser_at_name(user_at.getName());
				comment.setUser_at_id(at_id);
			} catch (Exception e) {
			}
			comment.setCreatedate(new Date());

			commentDao.save(comment);
		}
		
		return SUCCESS;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}


	public long getAt_id() {
		return at_id;
	}

	public long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}

	public void setAt_id(long at_id) {
		this.at_id = at_id;
	}

	
}
