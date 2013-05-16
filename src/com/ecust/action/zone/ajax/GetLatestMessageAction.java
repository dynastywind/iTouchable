package com.ecust.action.zone.ajax;

import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.MessageDao;
import com.ecust.model.zone.Message;
import com.ecust.util.DateUtils;
import com.ecust.util.Page;


/**
 * 获得最新消息
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetLatestMessageAction extends BaseAction {

	@Autowired
	private MessageDao messageDao;
	
	private String latestMessageHTML = "";
	
	private int page;
	
	private int types;
	
	private String pageList;
	
	@Action("getLatestMessage")
	public String execute() throws Exception {
		long user_id  = (Long)session.get("user");//得到当前用户id
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		Page pageBean = new Page();
		if(types == 0){
			pageBean = messageDao.getNoreadMessageByUserForPage(user_id, page);
		}else{
			pageBean = messageDao.getMessageByType(user_id, types, page); 
		}
		
		@SuppressWarnings("unchecked")
		Iterator<Message> iter = pageBean.getList().iterator();
		while(iter.hasNext()){
			Message message = iter.next();
			String messageHTML = new String();
			
			if(message.getMessage_type() == 1){ //1代表好友留言
				messageHTML = "<div class='message_chat_list message-list'>"+
				"<img alt='' src='" + basePath +"user/userfile/10103278/img/examle.jpg' width='40' height='40' class='message-list-img' />"+
				"<div class='message_chat_right message-list-right' message_id='"+message.getId()+"'>"+
					"<div class='message_chat_right_1'><span class='message_chat_name'>"+message.getFrom_user().getName()+"</span>：1314124124142</div>"+
					"<div class='message_chat_right_2'>"+DateUtils.format(message.getCreate_date())+"</div></div></div></div>";
			}else if(message.getMessage_type() == 2){	//2代表消息的类型为“好友申请”
				String btn_html = new String();
				if(message.isIs_read() == false){
					btn_html = "<div class='add-friends-pass-btn' id='"+message.getId()+"' deal='yes'>同意</div>" +
								"<div class='add-friends-pass-btn add-friends-refuse-btn' id='"+message.getId()+"' deal='no'>拒绝</div>";
				}else if(message.getFriends().isIs_Link() == true){
					btn_html = "<div class='add-friends-deal-result'>已同意</div>";
				}else{
					btn_html = "<div class='add-friends-deal-result'>已拒绝</div>";
				}
				messageHTML = "<div class='message-list message-list' id='"+message.getId()+"'>" +
						"<img alt='' src='"+basePath+"user/userfile/10103278/img/examle.jpg' class='message-list-img' width='40' height='40' />" +
						"<div class='message-list-right'>" +
						"<div class='message-list-1'><span class='message-list-name'>"+message.getFrom_user().getName()+"</span>：申请加为好友"+btn_html+"</div>" +
						"<div class='message-list-2'>留言："+message.getText() + "  " + DateUtils.format(message.getCreate_date())+"</div>" +
						"</div></div>";
			}else if(message.getMessage_type() ==3){ //3代表“邀请信息”
				String btn_html = new String();
				if(message.isIs_read() == false){
					btn_html = "<div class='add-society-pass-btn' id='"+message.getId()+"' deal='yes'>同意</div>" +
								"<div class='add-society-pass-btn add-society-refuse-btn' id='"+message.getId()+"' deal='no'>拒绝</div>";
				}else if(message.getMember().isLink() == true){
					btn_html = "<div class='add-friends-deal-result'>已同意</div>";
				}else{
					btn_html = "<div class='add-friends-deal-result'>已拒绝</div>";
				}
				messageHTML = "<div class='message-list message-list' id='"+message.getId()+"'>" +
						"<img alt='' src='"+basePath+"user/userfile/10103278/img/examle.jpg' class='message-list-img' width='40' height='40' />" +
						"<div class='message-list-right'>" +
						"<div class='message-list-1'><span class='message-list-name'>"+message.getFrom_user().getName()+"</span>：邀请您"+message.getText() + "  "+btn_html+"</div>" +
						"<div class='message-list-2'>  " + DateUtils.format(message.getCreate_date())+"</div>" +
						"</div></div>";
			}else if(message.getMessage_type() ==4){ //3代表“系统消息”
				messageHTML = "<div class='message-list' id='"+message.getId()+"'>" +
						"<img alt='' src='"+basePath+"image/system_img.gif' class='message-list-img' width='40' height='40' />" +
						"<div class='message-list-right'>" +
						"<div class='message-list-1'>系统提示： <span class='message-list-name'>"+message.getFrom_user().getName()+"</span> "+message.getText()+"</div>" +
						"<div class='message-list-2'>"+DateUtils.format(message.getCreate_date())+"</div>" +
						"</div></div>";
			}
			
			
			latestMessageHTML = latestMessageHTML + messageHTML;
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
			firstPage = "<a href='javascript:;'id='-1' class='message-page-a'>上一页</a>";
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
					a_class = "message-page_curr_a";
				}
				pageNum = pageNum + "<a href='javascript:;' id='"+(i+1)+"' class='message-page-a "+a_class+"'>"+(i+1)+"</a>";
			}
		}
		//是否存在“下一页”
		if(pageBean.getCurrentPage()==pageBean.getTotalPage()){
			lastPage = " 下一页 ";
		}else{
			lastPage = "<a href='javascript:;' id='+1' class='message-page-a'>下一页</a>";
		}
		pageList = "共有"+pageBean.getAllRow()+"条消息，分"+ pageBean.getTotalPage() +"页" + firstPage + preHide + pageNum + nexHide + lastPage;//翻页
		
		return SUCCESS;
	}

	public String getLatestMessageHTML() {
		return latestMessageHTML;
	}


	public void setLatestMessageHTML(String latestMessageHTML) {
		this.latestMessageHTML = latestMessageHTML;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getTypes() {
		return types;
	}


	public void setTypes(int types) {
		this.types = types;
	}


	public String getPageList() {
		return pageList;
	}


	public void setPageList(String pageList) {
		this.pageList = pageList;
	}
}
