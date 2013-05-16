package com.ecust.action.hall.ajax;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.SocietyDao;
import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.model.temp.SocietyTemp;

@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetInfoOnAdminAction extends BaseAction {

	@Autowired
	private SocietyDao societyDao;
	
	@Autowired
	private SocietyMemberDao memberDao;
	
	private String returnList = "";
	
	private long society_id;
	
	@Override
	@Action("getInfoOnAdmin")
	public String execute() throws Exception {
		SocietyTemp society = societyDao.getSocietyById(society_id);
		String teacher;
		try {
			teacher = memberDao.getTeacherBySociety(society_id).getName();
		} catch (Exception e) {
			teacher = "";
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
		
		returnList = returnList + "<table width='600' border='0' cellspacing='0' cellpadding='2' class='hall_info_tab'>" +
					"<tr>" +
						"<td class='hall_info_left'>大厅名称：</td>" +
						"<td><input name='name' type='text' id='name' class='hall_input hall_input_text' value='C语言教学班' /></td>" +
					"</tr><tr>" +
						"<td class='hall_info_left'>课程：</td>" +
						"<td><select name='select' class='hall_input hall_input_select'>" +
							"<option value='C/C++程序设计'>C/C++程序设计</option>" +
						"</select></td>" +
					"</tr><tr>" +
						"<td class='hall_info_left'>指导教师：</td>" +
						"<td><select name='select' class='hall_input hall_input_select'>" +
							"<option value=''>张林老师</option>" +
							"</select></td>" +
					"</tr><tr>" +
						"<td class='hall_info_left'>大厅类型：</td>" +
						"<td><select name='select' class='hall_input hall_input_select'>" +
								"<option value=''>自然班</option>" +
								"<option value=''>教学班</option>" +
								"<option value=''>学习小组</option>" +
									"</select></td>" +
					"</tr><tr>" +
						"<td class='hall_info_left'>厅主：</td>" +
						"<td>刘柏众</td>" +
					"</tr><tr>" +
						"<td class='hall_info_left'>创建时间：</td>" +
						"<td>2013年2月12日</td>" +
					"</tr><tr>" +
						"<td class='hall_info_left'  valign='top'>介绍：</td>" +
						"<td class='hall_intro_td'><textarea name='textarea' class='hall_input hall_input_textarea'>为保证2013年2月22-24日期间奉贤校区本科生补考工作的顺利进行，经协商，在原有班次的基础上增加相应校车班次（其他班次见车队原有安排）！</textarea></td>" +
					"</tr></table>";
		
		return SUCCESS;
	}

	public String getReturnList() {
		return returnList;
	}

	public void setReturnList(String returnList) {
		this.returnList = returnList;
	}

	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}
}
