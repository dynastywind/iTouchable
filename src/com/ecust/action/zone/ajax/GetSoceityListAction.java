package com.ecust.action.zone.ajax;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.SocietyDao;
import com.ecust.model.zone.Society;

@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetSoceityListAction extends BaseAction  {
	
	@Autowired
	private SocietyDao societyDao;
	
	private String society_HTML;
	
	@Override
	@Action("getSoceityList")
	public String execute() throws Exception {
		long user_id = (Long) session.get("user");
		/*===================== 自然班 ====================*/
		List<Society> t_list = societyDao.getSocietyByMember(user_id, 1);//自然班
		society_HTML = "<div class='center_studyhall_nav'><a href='javascript:;' class='center_studyhall_open'>自然班("+t_list.size()+")</a>" +
				"<a href='javascript:;' class='center_studyhall_add_btn'>创建</a></div><ul class='center_studyhall_nav_ul'>";
		for(int i = 0; i<t_list.size(); i++){
			society_HTML = society_HTML + "<li><a href='javascript:;' society_id='"+t_list.get(i).getId()+"'>"+t_list.get(i).getName()+"</a></li>";
		}
		
		/*===================== 教学班 ====================*/
		List<Society> c_list = societyDao.getSocietyByMember(user_id, 2);//教学班
		society_HTML = society_HTML + "</ul><div class='center_studyhall_nav'><a href='javascript:;' class='center_studyhall_open' >教学班("+c_list.size()+")</a>" +
				"<a href='javascript:;' class='center_studyhall_add_btn'>创建</a></div><ul class='center_studyhall_nav_ul'>";
		for(int i = 0; i<c_list.size(); i++){
			society_HTML = society_HTML + "<li><a href='javascript:;' society_id='"+c_list.get(i).getId()+"'>"+c_list.get(i).getName()+"</a></li>";
		}
		
		/*===================== 学习小组 ====================*/
		List<Society> g_list = societyDao.getSocietyByMember(user_id, 3);//学习小组
		society_HTML = society_HTML + "</ul><div class='center_studyhall_nav'><a href='javascript:;' class='center_studyhall_open' >学习小组("+g_list.size()+")</a>" +
				"<a href='javascript:;' class='center_studyhall_add_btn'>创建</a></div><ul class='center_studyhall_nav_ul'>";
		for(int i = 0; i<g_list.size(); i++){
			society_HTML = society_HTML + "<li><a href='javascript:;' society_id='"+g_list.get(i).getId()+"'>"+g_list.get(i).getName()+"</a></li>";
		}
		society_HTML = society_HTML + "</ul>";
		
		return SUCCESS;
	}

	public String getSociety_HTML() {
		return society_HTML;
	}

	public void setSociety_HTML(String society_HTML) {
		this.society_HTML = society_HTML;
	}
}
