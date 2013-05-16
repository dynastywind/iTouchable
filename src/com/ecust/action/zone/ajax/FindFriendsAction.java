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
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.temp.FriendsTemp;
import com.ecust.model.zone.FriendsGroup;
import com.ecust.util.Page;


@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class FindFriendsAction extends BaseAction {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	private String keyword;//搜索的关键词
	
	private String result_html="";//返回用户列表
	
	private String pageList;
	
	private int page;
	
	@Action("findFriends")
	public String execute() throws Exception {
		long user_id = (Long) session.get("user");
		Page pageBean = userDao.searchFriendsByKeywordForPage(keyword, user_id, page);
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";//用于设置图片路径
		Iterator<FriendsGroup> iter1 = friendsGroupDao.getAllFriendsGroup(user_id).iterator();//读取好友分组
		String group_html = "";
		while(iter1.hasNext()){
			FriendsGroup friendsGroup = iter1.next();
			group_html = group_html + "<a href='javascript:;' id='"+ friendsGroup.getId() +"'>" + friendsGroup.getGroup_name() + "</a>";
		}
		
		@SuppressWarnings("unchecked")
		List<FriendsTemp> friendsList = pageBean.getList();
		for(int i = 0; i< friendsList.size();i++){
			FriendsTemp friends = (FriendsTemp) friendsList.get(i);
			result_html = result_html + "<li id='"+friends.getFriends_id()+"'><img src='"+ basePath + "user/userfile/10102212/img/10102212.jpg' class='freinds_list_img' width='50' height='50' />"
			 + " <div class='freinds_list_tab'><div class='friends-tab-1'>"
			 + "<span class='freinds_name_spn'>"+ friends.getUser_name() + "</span> "+ friends.getUser_gender() + " "+ friends.getUser_college() + ""
			 + "<a href='javascript:;' friends_id='"+friends.getUser_id()+"' class='freids_list_a'>给TA留言</a> <a href='javascript:;' friends_id='"+friends.getFriends_id()+"' class='freids_list_a freinds_move'>移动到</a> "
			 + "  <a href='javascript:;'  friends_id='"+friends.getFriends_id()+"' class='freids_list_a freinds_delete'>删除</a><div friends_id='"+friends.getFriends_id()+"' class='freinds_move_div'><a href='javascript:;' id=''>未分组</a>"+ group_html +"</div></div> <div class='friends-tab-2'></div>"
			 + " </div></li>";
		}
		
		String firstPage;//上一页html
		String lastPage;//下一页html
		String preHide = "";
		String nexHide = "";
		String pageNum = "";
		//是否存在“上一页”
		if(pageBean.getCurrentPage()==1){
			firstPage = "上一页 ";
		}else{
			firstPage = "<a href='javascript:;' id='-1' class='find_page_a'>上一页</a> ";
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
					a_class = " page_curr_a";
				}
				pageNum = pageNum + " <a href='javascript:;' id='"+(i+1)+"' class='find_page_a"+a_class+"'>"+(i+1)+"</a> ";
			}
		}
		//是否存在“下一页”
		if(pageBean.getCurrentPage()==pageBean.getTotalPage()){
			lastPage = " 下一页";
		}else{
			lastPage = " <a href='javascript:;' id='+1' class='find_page_a'>下一页</a>";
		}
		pageList = "共找到"+pageBean.getAllRow()+"个好友，分"+ pageBean.getTotalPage() +"页 " + firstPage + preHide + pageNum + nexHide + lastPage;//翻页
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