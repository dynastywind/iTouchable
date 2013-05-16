package com.ecust.action.hall.ajax;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.model.temp.SocietyMemberTemp;

@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetMemberListAction extends BaseAction {
	
	@Autowired
	private SocietyMemberDao societyMemberDao;
	
	private String member_HTML = "";
	
	private int member_count; //成员的数量
	
	private long society_id;
	
	@Override
	@Action("getMemberList")
	public String execute() throws Exception {
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";//用于生成图片路径
		
		List<SocietyMemberTemp> memberList = societyMemberDao.getSimpleMemberBySociety(society_id);
		member_count = memberList.size();
		SocietyMemberTemp member = new SocietyMemberTemp();
		for(int i = 0; i<member_count; i++){
			member = memberList.get(i);
			String ico = "";//用户图标
			if(member.getMember_role() == 1){//管理员
				ico = "<img src='"+basePath+"user/studyhall/image/admin_ico.png' " +
						"class='member_img' width='10' height='12' />";
			}else if(member.getMember_role() == 2){//厅主
				ico = "<img src='"+basePath+"user/studyhall/image/creator_ico.png' " +
						"class='member_img' width='10' height='12' />";
			}else if((member.getMember_role() == 3)){//指导老师
				ico = "<img src='"+basePath+"user/studyhall/image/teacher_ico.png' " +
						"class='member_img' width='10' height='12' />";
			}else{
				
			}
			member_HTML = member_HTML + "<div class='hall-member-list' user_id='"+member.getUser_id()+"'>"+ico+member.getUser_name()+"</div>";
		}
		return SUCCESS;
	}

	public String getMember_HTML() {
		return member_HTML;
	}

	public void setMember_HTML(String member_HTML) {
		this.member_HTML = member_HTML;
	}

	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}

	public int getMember_count() {
		return member_count;
	}

	public void setMember_count(int member_count) {
		this.member_count = member_count;
	}
	
}
