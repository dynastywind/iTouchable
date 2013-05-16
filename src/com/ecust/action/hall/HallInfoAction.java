package com.ecust.action.hall;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.dao.zone.SocietyDao;
import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.dao.zone.SocietyNoticeDao;
import com.ecust.model.temp.SocietyMemberTemp;
import com.ecust.model.temp.SocietyTemp;
import com.ecust.model.zone.SocietyNotice;
import com.ecust.util.DateUtils;

@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/user/hall")
@Results({@Result(name="success",location="/user/studyhall/info.jsp"),@Result(name="error",type="redirect",location="/index.jsp")})
public class HallInfoAction extends HallBaseAction{
	
	@Autowired
	private SocietyDao societyDao;
	
	@Autowired
	private SocietyNoticeDao noticeDao;
	
	@Autowired
	private SocietyMemberDao memberDao;
	
	private long society_id;
	
	private String info_HTML;
	
	private String notice_HTML;
	
	private String member_HTML;
	
	@Action("hallInfo")
	public String execute() throws Exception {
		if(validateMemberAuthority(society_id)){ //一级权限
			
			/*=========大厅资料==========*/
			
			SocietyTemp society = societyDao.getSocietyById(society_id);
			String teacher;
			try {
				teacher = memberDao.getTeacherBySociety(society_id).getName();
			} catch (Exception e) {
				teacher = "无";
			}
			String lord = memberDao.getLordBySociety(society_id).getName();
			String society_type = "";
			if(society.getSociety_type() == 1){
				society_type = "自然班";
			}else if(society.getSociety_type() == 2){
				society_type = "教学班";
			}else if(society.getSociety_type() == 3){
				society_type = "学习小组";
			}
			info_HTML = "<table width='600' border='0' cellspacing='0' cellpadding='2' class='hall_info_tab'> "+
			  "<tr>"+
			   " <td class='hall_info_left'>大厅名称：</td>"+
			    "<td>"+society.getSociety_name()+"</td>"+
			  "</tr>"+
			  "<tr>"+
			    "<td class='hall_info_left'>课程：</td>"+
			    "<td>"+society.getCourse_name()+"</td>"+
			  "</tr>"+
			  "<tr>"+
			    "<td class='hall_info_left'>指导教师：</td>"+
			    "<td>" + teacher + "</td>"+
			  "</tr>"+
			  "<tr>"+
			    "<td class='hall_info_left'>大厅类型：</td>"+
			    "<td>"+society_type+"</td>"+
			  "</tr>"+
			  "<tr>"+
			    "<td class='hall_info_left'>厅主：</td>"+
			    "<td>" + lord + "</td>"+
			  "</tr>"+
			  "<tr>"+
			    "<td class='hall_info_left'>创建时间：</td>"+
			    "<td>"+DateUtils.format(society.getSociety_createdate(),DateUtils.FORMAT_SHORT_CN)+"</td>"+
			  "</tr>"+
			  "<tr>"+
			    "<td class='hall_info_left'  valign='top'>介绍：</td>"+
			    "<td class='hall_intro_td'>"+society.getSociety_info()+"</td>"+
			 "</tr>"+
			"</table>";
			
			/*=========大厅公告==========*/
			
			List<SocietyNotice> notice_list = noticeDao.getAllNoticeBySociety(society_id);
			notice_HTML = "<div class='admin_notice_function'>共有" + notice_list.size() + "条公告 </div>";
			String writer_type = "";
			SocietyNotice notice = new SocietyNotice();
			for( int i = 0 ; i<notice_list.size() ; i++){
				notice = notice_list.get(i);
				if(notice.getWriter_type() == 1){
					writer_type = "厅主";
				}else if(notice.getWriter_type() == 2){
					writer_type = "管理员";
				}else if(notice.getWriter_type() == 3){
					writer_type = "指导老师";
				}
				notice_HTML = notice_HTML + "<div class='info_notice_list'>"+
						"<div class='info_notice_list_1'>&nbsp;&nbsp;" + notice.getContent() + "</div>"+
						"<div class='info_notice_list_2'>-By " + writer_type + " " + DateUtils.format(notice.getCreatedate(), DateUtils.FORMAT_SHORT) + "</div></div>";
			}
			
			/*=========大厅成员==========*/
			
			List<SocietyMemberTemp> member_list = memberDao.getMemberBySociety(society_id);
			member_HTML = "<div class='admin_member_function'>共有" + member_list.size() + "名成员</div>" +
					"<table width='630' border='0' cellspacing='1' cellpadding='0' class='info_member_tab'>" +
					"<tr>" +
						"<th scope='col' width='70' align='center'>大厅活跃度</th>" +
						"<th scope='col' width='80' align='center'>姓名</th>" +
						"<th scope='col' width='40' align='center'>性别</th>" +
						"<th scope='col' width='150' align='center'>学院</th>" +
						"<th scope='col' width='70' align='center'>年级</th>" +
						"<th scope='col' width='80' align='center'>加入时间</th>" +
						"<th scope='col' align='center'>&nbsp;</th>" +
					"</tr>";
			SocietyMemberTemp member = new SocietyMemberTemp();
			for( int i = 0; i < member_list.size(); i++){
				member = member_list.get(i);
				member_HTML = member_HTML + "<tr>" +
						"<td align='center'>99</td>" +
						"<td align='center'>" + member.getUser_name() + "</td>" +
						"<td align='center'>男</td>" +
						"<td align='center'>资源与环境工程学院</td>" +
						"<td align='center'>2010级</td>" +
						"<td align='center'>2012-2</td>" +
						"<td align='center'><a href='javascript:;'>主页</a>|<a href='javascript:;'>动态</a>|<a href='javascript:;'>加好友</a></td>" +
						"</tr>";
			}
			member_HTML = member_HTML + "</table>";
			
			return SUCCESS;
		}else{
			return ERROR;
		}
		
		
	}

	public String getInfo_HTML() {
		return info_HTML;
	}

	public void setInfo_HTML(String info_HTML) {
		this.info_HTML = info_HTML;
	}

	public String getNotice_HTML() {
		return notice_HTML;
	}

	public void setNotice_HTML(String notice_HTML) {
		this.notice_HTML = notice_HTML;
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

	

}
