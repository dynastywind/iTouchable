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
import com.ecust.dao.zone.FriendsDao;
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.model.temp.FriendsTemp;
import com.ecust.model.zone.FriendsGroup;
import com.ecust.util.Page;

/**
 * 好友列表
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results({@Result(type="json")})
@Action("getFriendsListByGroup")
public class GetFriendsListByGroupActoin extends BaseAction {

	@Autowired
	private FriendsDao friendsDao;
	
	@Autowired
	private FriendsGroupDao friendsGroupDao;
	
	private String returnList = "";//所有好友列表
	
	private String pageList;//页码导航
	
	private long group_id = 0;
	
	private int page = 0;//页码
	
	private int allFriendsCount = 0;
	
	@Override
	public String execute() throws Exception {
			long user_id = (Long) session.get("user");
			
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";//用于设置图片路径
		try {
			Page pageBean = new Page();
			if(group_id==0){
				pageBean = friendsDao.getAllFriendsTempByUserForPage(user_id, page);//查询所有好友
			}else if(group_id > 0){
				pageBean = friendsDao.getFriendsTempByGroupForPage(group_id, page);//查询分组好友
			}else{
				pageBean = friendsDao.getUngroupedFriendsTempForPage(user_id, page);
			}
			
			@SuppressWarnings("unchecked")
			List<FriendsTemp> friendsList = pageBean.getList();
			
			Iterator<FriendsGroup> iter = friendsGroupDao.getAllFriendsGroup(user_id).iterator();//读取好友分组
			String group_html = "";
			while(iter.hasNext()){
				FriendsGroup friendsGroup = iter.next();
				group_html = group_html + "<a href='javascript:;' id='"+ friendsGroup.getId() +"'>" + friendsGroup.getGroup_name() + "</a>";
			}
			
			for(int i = 0; i< friendsList.size();i++){
				FriendsTemp friends = (FriendsTemp) friendsList.get(i);
				returnList = returnList + "<li id='"+friends.getFriends_id()+"'><img src='"+ basePath + "user/userfile/10102212/img/10102212.jpg' class='freinds_list_img' width='50' height='50' />"
				 + " <div class='freinds_list_tab'><div class='friends-tab-1'>"
				 + "<span class='freinds_name_spn'>"+ friends.getUser_name() + "</span> "+ friends.getUser_gender() + " "+ friends.getUser_college() + ""
				 + "<a href='javascript:;' friends_id='"+friends.getUser_id()+"' class='freids_list_a'>给TA留言</a> <a href='javascript:;' friends_id='"+friends.getFriends_id()+"' group_id='"+group_id+"' class='freids_list_a freinds_move'>移动到</a> "
				 + "  <a href='javascript:;' friends_id='"+friends.getFriends_id()+"' class='freids_list_a freinds_delete'>删除</a><div friends_id='"+friends.getFriends_id()+"' class='freinds_move_div'><a href='javascript:;' id=''>未分组</a>"+ group_html +"</div></div> <div class='friends-tab-2'></div>"
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
				firstPage = "<a href='javascript:;' id='-1' class='page_a'>上一页</a> ";
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
					pageNum = pageNum + " <a href='javascript:;' id='"+(i+1)+"' class='page_a"+a_class+"'>"+(i+1)+"</a> ";
				}
			}
			//是否存在“下一页”
			if(pageBean.getCurrentPage()==pageBean.getTotalPage()){
				lastPage = " 下一页";
			}else{
				lastPage = " <a href='javascript:;' class='page_a' id='+1'>下一页</a>";
			}
			pageList = "共有"+pageBean.getAllRow()+"个，"+ pageBean.getTotalPage() +"页 " + firstPage + preHide + pageNum + nexHide + lastPage;//翻页
			
			allFriendsCount  = pageBean.getAllRow();
		} catch (Exception e) {
		}
		return SUCCESS;	
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}


	public String getReturnList() {
		return returnList;
	}

	public void setReturnList(String returnList) {
		this.returnList = returnList;
	}


	public String getPageList() {
		return pageList;
	}


	public void setPageList(String pageList) {
		this.pageList = pageList;
	}

	public long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(long group_id) {
		this.group_id = group_id;
	}

	public int getAllFriendsCount() {
		return allFriendsCount;
	}

	public void setAllFriendsCount(int allFriendsCount) {
		this.allFriendsCount = allFriendsCount;
	}

}
