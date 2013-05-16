package com.ecust.action.zone.ajax;

import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.CommentDao;
import com.ecust.model.temp.CommentTemp;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Namespace("/user")
@Results(@Result(type="json"))
public class GetCommentByTopicAction extends BaseAction {

	@Autowired
	private CommentDao commentDao;
	
	private long activity_id;
	
	private String returnCommentList = "";
	
	@Action("getCommentByTopic")
	public String execute() throws Exception {
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";//用于生成图片路径
		
		Iterator<CommentTemp> iter = commentDao.getCommentByActivity(activity_id).iterator();
		while(iter.hasNext()){
			CommentTemp comment = iter.next();
			String nameHtml = new String();
			if(comment.getUser_at_id() == 0){//是否是回复
				nameHtml = "<span class='comment-name'>"+ comment.getUser_own_name() +"</span>：";
			}else{
				nameHtml = "<span class='comment-name'>"+ comment.getUser_own_name() +"</span>：" + "</span>(回应<span class='comment-name'>" + comment.getUser_at_name() +"</span>) ";
			}
			returnCommentList = returnCommentList + "<div class='news_comment_list_div'><a href='#'><img src='" + basePath + "user/userfile/10102212/img/10102212.jpg' width='30' height='30' border='0' class='comment_pic' /></a>"+
					"<div class='new-comment-point'></div><div class='comment-right'>" +
					"<div class='comment-text-box'>"+ nameHtml + comment.getComment_text() + "<span class='comment_time_span'>"+comment.getCreatedate()+"</span> <a href='javascript:;' class='new-comment-back' topic_id='"+activity_id+"' at_id='"+comment.getUser_own_id()+"' at_name = '"+comment.getUser_own_name()+"'>[回应]</a>"+
						"</div></div></div>";
		}
		
		return SUCCESS;
	}

	public String getReturnCommentList() {
		return returnCommentList;
	}

	public void setReturnCommentList(String returnCommentList) {
		this.returnCommentList = returnCommentList;
	}

	public long getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(long activity_id) {
		this.activity_id = activity_id;
	}
}
