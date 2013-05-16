package com.ecust.action.hall.ajax;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.hall.HallBaseAction;
import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.model.temp.SocietyMemberTemp;

@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetMemberListOnAdminAction extends HallBaseAction {
	
	@Autowired
	private SocietyMemberDao societyMemberDao;
	
	private String return_HTML = "";
	
	private int return_count; //数量
	
	private long society_id;
	
	@Override
	@Action("getMemberListOnAdmin")
	public String execute() throws Exception {
		if(validateAdminAuthority(society_id)){ //二级权限
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";//用于生成图片路径
			
			List<SocietyMemberTemp> memberList = societyMemberDao.getMemberBySociety(society_id);
			return_count = memberList.size();
			SocietyMemberTemp member = new SocietyMemberTemp();
			for(int i = 0; i < return_count; i++){
				member = memberList.get(i);
				String ico = "";//用户图标
				String admin_btn = "";
				if(member.getMember_role() == 0){
					admin_btn = "<a href='javascript:;' class='hall_admin_setadmin_btn' u_id='" + member.getUser_id() + "' m_id='" + member.getMember_id() + "' id='1'>授管理权</a>";
				}else if(member.getMember_role() == 1){//管理员
					ico = "<img src='"+basePath+"user/studyhall/image/admin_ico.png' " +
							"class='member_img' width='10' height='12' />";
					admin_btn = "<a href='javascript:;' class='hall_admin_setadmin_btn' u_id='" + member.getUser_id() + "' m_id='" + member.getMember_id() + "' id='0'>解除管理</a>";
				}else if(member.getMember_role() == 2){//厅主
					ico = "<img src='"+basePath+"user/studyhall/image/creator_ico.png' " +
							"class='member_img' width='10' height='12' />";
					admin_btn = "已获权限";
				}else if((member.getMember_role() == 3)){//指导老师
					ico = "<img src='"+basePath+"user/studyhall/image/teacher_ico.png' " +
							"class='member_img' width='10' height='12' />";
					admin_btn = "已获权限";
				}
				String grade = "—";
				if(member.getUser_grade() != null){
					grade = member.getUser_grade() + "级";
				}
				return_HTML = return_HTML + "<tr>" +
						"<td align='center' width='50'><input type='checkbox' name='checkbox' value='checkbox' /></td>" +
						"<td align='center' width='70'>" + member.getMember_level() + "</td>" +
						"<td align='center' width='80'>" + ico + member.getUser_name() + "</td>" +
						"<td align='center' width='50'>" + member.getUser_gender() + "</td>" +
						"<td align='center' width='150'>" + member.getUser_college() + "</td>" +
						"<td align='center' width='80'>" + grade + "</td>" +
						"<td align='center' width=''><a href='javascript:;'>禁言</a>|" + admin_btn + "|<a href='javascript:;'>移除</a></td>" +
						"</tr>";
			}
		}else{
			PASS = false;
		}
		return SUCCESS;
	}

	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}

	public int getReturn_count() {
		return return_count;
	}

	public void setReturn_count(int return_count) {
		this.return_count = return_count;
	}

	public String getReturn_HTML() {
		return return_HTML;
	}

	public void setReturn_HTML(String return_HTML) {
		this.return_HTML = return_HTML;
	}

	
}
