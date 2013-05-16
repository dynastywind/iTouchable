package com.ecust.action.zone.ajax;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.ChatDao;
import com.ecust.model.temp.ChatTemp;
import com.ecust.util.Page;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetChatListOnMessageAction extends BaseAction {

	@Autowired
	private ChatDao chatDao;
	
	private String chat_HTML = "<div class='chat_history_box'>";
	
	private String pageList = "";
	
	private long message_id;
	
	private int page;
	
	@Action("getChatListOnMessage")
	public String execute() throws Exception {
		long user_id = (Long) session.get("user");
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		Page pageBean = chatDao.getChatByMessageForPage(message_id, page);
		@SuppressWarnings("unchecked")
		Iterator<ChatTemp> iter = pageBean.getList().iterator();
		while(iter.hasNext()){
			ChatTemp chat = iter.next();
			String from_float = "";
			String from_time = "";
			String from_close = "";
			String from_pointer = "";
			if(chat.getFrom_user_id() == user_id){
				from_float = " chat_history_float_right";
				from_time = " chat_history_time_right";
				from_close = " chat_history_delete_right";
				from_pointer = " chat_history_point_right";
			}
			chat_HTML = chat_HTML + "<div class='chat_history_list" + from_float + "' chat_id='"+chat.getChat_id()+"'>" +
					"<img src='" + basePath + "user/userfile/10103278/img/examle.jpg' alt='' class='chat_history_img" + from_float + "' border='0' height='40' width='40'>" +
					"<div class='chat_history_point" + from_pointer + "'></div>" +
					"<div class='chat_history_right_box" + from_float + "'>" +
					"<div class='chat_history_right" + from_float + "'>" +
					"<div class='chat_history_delete" + from_close + "' chat_id='"+chat.getChat_id()+"' message_id='"+message_id+"'></div>" +
					"<div class='chat_history_right_1 '>" + chat.getChat_text() + "</div>" +
					"<div style='clear:both'></div>" +
					"<div class='chat_history_right_2" + from_time + "'>" + date.format(chat.getChat_date()) + "</div>" +
					"</div></div></div>";
		}
		
		String firstPage;//上一页html
		String lastPage;//下一页html
		String preHide = "";
		String nexHide = "";
		String pageNum = "";
		//是否存在“上一页”
		if(pageBean.getCurrentPage()==1){
			firstPage = " 上一页 ";
		}else{
			firstPage = "<a href='javascript:;'id='-1' class='message_chat_page_a'>上一页</a>";
		}
		
		if(pageBean.getCurrentPage()>3){
			preHide = "...";
		}
		
		if(pageBean.getCurrentPage()<pageBean.getTotalPage()-2){
			nexHide = "...";
		}
		//页码数字
		for(int i = 0;i< pageBean.getTotalPage();i++){
			if(i>pageBean.getLower()-1 && i<pageBean.getUpper()-1){
				String a_class = "";
				if(pageBean.getCurrentPage() == (i+1)){
					a_class = " message_chat_page_curr_a";
				}
				pageNum = pageNum + "<a href='javascript:;' id='"+(i+1)+"' class='message_chat_page_a"+a_class+"'>"+(i+1)+"</a>";
			}
		}
		//是否存在“下一页”
		if(pageBean.getCurrentPage()==pageBean.getTotalPage()){
			lastPage = " 下一页 ";
		}else{
			lastPage = "<a href='javascript:;' id='+1' class='message_chat_page_a'>下一页</a>";
		}
		pageList = "共有"+pageBean.getAllRow()+"条消息，分"+ pageBean.getTotalPage() +"页" + firstPage + preHide + pageNum + nexHide + lastPage;//翻页
		
		chat_HTML = chat_HTML + "</div>";
		return SUCCESS;
	}
	
	public String getChat_HTML() {
		return chat_HTML;
	}

	public void setChat_HTML(String chat_HTML) {
		this.chat_HTML = chat_HTML;
	}

	public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getPageList() {
		return pageList;
	}

	public void setPageList(String pageList) {
		this.pageList = pageList;
	}

}