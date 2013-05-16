package com.ecust.util;

import java.util.Iterator;
import java.util.List;

import com.ecust.dao.zone.CommentDao;
import com.ecust.model.temp.ActivityTemp;
import com.ecust.model.temp.CommentTemp;

/**
 * 获得HTML的公共类
 * @author lbz
 *
 */
public final class GetHTML {

	
	/**
	 * 获得话题/动态 HTML:学习大厅
	 * @param list 话题的list
	 * @param commentService
	 * @param basePath 用于生成图片路径
	 * @return
	 */
	public static String getTopicHTML(List<ActivityTemp> list,CommentDao commentDao,String basePath){
		String topicHTML = "";
		
		Iterator<ActivityTemp> iter_activity = list.iterator();
		
		while(iter_activity.hasNext()){
			ActivityTemp activity = iter_activity.next();
			List<CommentTemp> commentList = commentDao.getCommentByActivity(activity.getActivity_id());
			String html_pre = "<div class='movement-list'><a href='#'><img src='" + basePath + "user/userfile/10102212/img/10102212.jpg' width='40' height='40' border='0' class='movement-img' /></a>" +
					"<img src='" + basePath + "user/studyhall/image/movement-pointer.gif' width='18' height='15' class='movement-pointer' />"+
					"<div class='movement-right-max'><div class='movement-right'>"+
						"<div class='movement-right-1'>"+activity.getUser_own_name()+"："+activity.getActivity_text()+"</div>"+
						"<div class='movement-right-2'>讨论:<a href='javascript:;' class='movement_comment_open'>"+commentList.size()+"</a> ｜ 参与人数:2 ｜ "+DateUtils.format(activity.getActivity_date(),DateUtils.FORMAT_MIDDLE)+"</div>"+
					"</div></div></div><div class='movement_comment_box'><div class='movement_comment_close'>×</div><div class='movement_comment_html' activity_id='"+activity.getActivity_id()+"'>";
			
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
				html_comment = html_comment + "<div class='movement_comment_list_div'><a href='#'><img src='" + basePath + "user/userfile/10102212/img/10102212.jpg' width='30' height='30' border='0' class='comment_pic' /></a>"+
				"<div class='movement-comment-point'></div><div class='comment-right'>" +
				"<div class='comment-text-box'>" + nameHtml + comment.getComment_text() + "<span class='comment_time_span'>"+DateUtils.format(comment.getCreatedate(),DateUtils.FORMAT_MIDDLE)+"</span> <a href='javascript:;'" +
						" class='movement-comment-back' activity_id='"+activity.getActivity_id()+"' at_id='"+comment.getUser_own_id()+"' at_name = '"+comment.getUser_own_name()+"'>[回应]</a>"+
					"</div></div></div>";
			}
			String html_nex = "</div><div class='movement-point'></div>" +
			"<!-- 参与讨论 --><div class='movement_comment_div'><img alt='' src='" + basePath + "user/userfile/10103278/img/examle.jpg'  width='30' height='30' class='add-comment-img' />" +
				  "<textarea name='textarea'  activity_id='"+activity.getActivity_id()+"'  class='comment-input'>参与讨论</textarea><div class='comment-back-btn' at_id='0' id='"+ activity.getActivity_id() +"'>发 布</div>"+
			"</div></div>";
			
			topicHTML = topicHTML + html_pre + html_comment + html_nex;
		}
		
		return topicHTML;
	}
	
}
