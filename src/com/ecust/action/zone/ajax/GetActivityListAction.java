package com.ecust.action.zone.ajax;

import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.CommentDao;
import com.ecust.model.temp.ActivityTemp;
import com.ecust.model.temp.CommentTemp;
import com.ecust.service.zone.ActivityService;
import com.ecust.util.DateUtils;


@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetActivityListAction extends BaseAction {

	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private CommentDao commentDao;
	
	private int topic_type;//查询条件：0[all]所有话题、1[is_finish]已解决、2[not_finish]待解决、3[is_essence]精华讨论
	
	private String type; //话题或学习动态
	
	private long friends_id;
	
	private String returnList = "";//返回的html
	
	private int page = 1;
	
	@Action("getActivityList")
	public String execute() throws Exception {
		
		long user_id = (Long) session.get("user");//得到用户id
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";//用于生成图片路径
		
		@SuppressWarnings("unchecked")
		Iterator<ActivityTemp> iter_activity = activityService.listActivityForPageOnZone(user_id, friends_id, type, topic_type, page).getList().iterator();
		
		while(iter_activity.hasNext()){
			ActivityTemp activity = iter_activity.next();
			
			//如果是自己发布的问答，增加管理功能
			String html_admin = "";
			if(activity.getActivity_a_type().equals("t") && activity.getUser_own_id() == user_id){
				if(activity.isActivity_is_finish()){
					html_admin = "<a href='javascript:;' id='"+ activity.getActivity_id() +"' class='news_admin_del'>删除</a><a href='javascript:;' id='"+ activity.getActivity_id() +"' is_f='0' class='news_admin_mark'>标记为未解决</a>";
				}else{
					html_admin = "<a href='javascript:;' id='"+ activity.getActivity_id() +"' class='news_admin_del'>删除</a><a href='javascript:;' id='"+ activity.getActivity_id() +"' is_f='1' class='news_admin_mark'>标记为已解决</a>";
				}
			}
				
			List<CommentTemp> commentList = commentDao.getCommentByActivity(activity.getActivity_id());
			String html_pre = "<div class='news_list_div'><a href='#'><img src='" + basePath + "user/userfile/10102212/img/10102212.jpg' width='60' height='60' border='0' class='news_pic' /></a>"+
		"<div class='news_right_div'><div class='name_div'>"+ activity.getUser_own_name() +"<div class='time-div'>"+DateUtils.format(activity.getActivity_date())+"</div></div>"+
			"<div class='news_text_div'>"+ activity.getActivity_text() +"</div><div class='news_time_div'> " +
			"<a href='#'>转发(4)</a>&nbsp;<a href='#'>讨论("+commentList.size()+")</a> <a href='#'>参与人数(2)</a>" + html_admin + "</div> " +
			"<div class='comment-more'>查看全部讨论</div><div class='news_comment_contain_div' id='topic-comment-"+activity.getActivity_id()+"'>";
			
			Iterator<CommentTemp> iter_comment = commentList.iterator();
			String html_comment = "";
			while(iter_comment.hasNext()){
				CommentTemp comment = iter_comment.next();
				String nameHtml = new String();
				if(comment.getUser_at_id() == 0){//是否是回复
					nameHtml = "<span class='comment-name'>"+ comment.getUser_own_name() +"</span>：";
				}else{
					nameHtml = "<span class='comment-name'>"+ comment.getUser_own_name() +"</span>：" + "</span>(回应<span class='comment-name'>" + comment.getUser_at_name() +"</span>) ";
				}
				//评论部分
				html_comment = html_comment + "<div class='news_comment_list_div'><a href='#'><img src='" + basePath + "user/userfile/10102212/img/10102212.jpg' width='30' height='30' border='0' class='comment_pic' /></a>"+
				"<div class='new-comment-point'></div><div class='comment-right'>" +
				"<div class='comment-text-box'>" + nameHtml + comment.getComment_text() + "<span class='comment_time_span'>"+DateUtils.format(comment.getCreatedate(), DateUtils.FORMAT_MIDDLE)+"</span> <a href='javascript:;' class='new-comment-back' activity_id='"+activity.getActivity_id()+"' at_id='"+comment.getUser_own_id()+"' at_name = '"+comment.getUser_own_name()+"'>[回应]</a>"+
					"</div></div></div>";
			}
			String html_nex = "</div><a href='javascript:;' class='news_comment_hide'><img src='" + basePath + "user/image/new_comment_folk_ico.png' width='17' height='12' border='0' />折叠</a></div><div class='news-point'></div>" +
			"<!-- 参与讨论 --><div class='news_comment_div'><img alt='' src='" + basePath + "user/userfile/10103278/img/examle.jpg'  width='30' height='30' class='add-comment-img' />" +
				  "<textarea name='textarea'  activity_id='"+activity.getActivity_id()+"'  class='comment-input'>参与讨论</textarea><div class='comment-back-btn' at_id='0' id='"+ activity.getActivity_id() +"'>发 布</div>"+
			"</div></div>";
			
			returnList = returnList + html_pre + html_comment + html_nex;
		}
		
		return SUCCESS;
	}

	public int getTopic_type() {
		return topic_type;
	}

	public void setTopic_type(int topic_type) {
		this.topic_type = topic_type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReturnList() {
		return returnList;
	}

	public void setReturnList(String returnList) {
		this.returnList = returnList;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getFriends_id() {
		return friends_id;
	}

	public void setFriends_id(long friends_id) {
		this.friends_id = friends_id;
	}
}
