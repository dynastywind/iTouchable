package com.ecust.action.zone.ajax;

import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.FriendsDao;
import com.ecust.dao.zone.MessageDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.User;
import com.ecust.util.Page;


@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class SearchFriendsAction extends BaseAction{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FriendsDao friendsDao;
	
	@Autowired
	private MessageDao messageDao;
	
	private String keyword;//搜索的关键词
	
	private String result_html="";//返回用户列表
	
	private String pageList;
	
	private int page;
	
	@Action("searchFriends")
	public String execute() throws Exception {
		Page pageBean = userDao.searchUserByKeywordForPage(keyword, page);//获得数据列表
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		@SuppressWarnings("unchecked")
		Iterator<User> iter = pageBean.getList().iterator();//好友
		
		long user_1 = (Long) session.get("user");//当前用户的id
		String btn_html = "";//“申请好友”或“留言”按钮
		while(iter.hasNext()){
			User user = iter.next();
			if(user_1 == user.getId()){
				btn_html = "";
			}
			else if(friendsDao.isFriends(user_1, user.getId())){
				btn_html = "<div class='message-friends-btn' id='"+user.getId()+"'>留言</div>";
			}else if(messageDao.isApplyForFriends(user_1, user.getId())){
				btn_html = "<div class='wait_friends_deal'>申请已发出</div>";
			}else{
				btn_html = "<div class='add-friends-btn' id='"+user.getId()+"'>+申请好友</div>";
			}
			result_html = result_html + "<div class='friends-result-list' id='"+user.getId()+"'>"+
					"<img class='friends-result-img' width='40' height='40' src='"+basePath+"user/userfile/10103278/img/examle.jpg' />"+
					"<div class='friends-result-list-1'><span class='friends-result-name'>"+user.getName()+"</span> "+user.getGender()+" "+user.getCollege()+""+btn_html+"</div>"+
					"<div class='friends-result-list-2'></div></div>";
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
			firstPage = "<a href='javascript:;'id='-1' class='friends-result-page-a'>上一页</a>";
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
					a_class = "friends-result-page_curr_a";
				}
				pageNum = pageNum + "<a href='javascript:;' id='"+(i+1)+"' class='friends-result-page-a "+a_class+"'>"+(i+1)+"</a>";
			}
		}
		//是否存在“下一页”
		if(pageBean.getCurrentPage()==pageBean.getTotalPage()){
			lastPage = " 下一页 ";
		}else{
			lastPage = "<a href='javascript:;' id='+1' class='friends-result-page-a'>下一页</a>";
		}
		pageList = "共找到"+pageBean.getAllRow()+"个结果，分"+ pageBean.getTotalPage() +"页" + firstPage + preHide + pageNum + nexHide + lastPage;//翻页
		return SUCCESS;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getResult_html() {
		return result_html;
	}

	public void setResult_html(String result_html) {
		this.result_html = result_html;
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