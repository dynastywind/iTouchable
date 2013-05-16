package com.ecust.action.zone.ajax;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.User;
import com.ecust.util.DateUtils;


@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetPersonInfoAction extends BaseAction {

	@Autowired
	private UserDao userDao;
	
	private String return_HTML = "";//返回的html
	
	@Action("getPersonInfo")
	public String execute() throws Exception {
		User user = userDao.getPersonInfoById((Long)session.get("user"));
		
		//性别
		String gender_m = "";
		String gender_w = "";
		if(user.getGender().equals("男")){
			gender_m = " checked='checked' ";
		}else if(user.getGender().equals("女")){
			gender_w = " checked='checked' ";
		}
		
		//生日
		String b_year = "";
		String b_month = "";
		String b_day = "";

		try {
			b_year = "<option value='" + DateUtils.getYear(user.getBirth()) + "'>" + DateUtils.getYear(user.getBirth()) + "</option>";
			b_month = "<option value='" + DateUtils.getMonth(user.getBirth()) + "'>" + DateUtils.getMonth(user.getBirth()) + "</option>";
			b_day = "<option value='" + DateUtils.getDay(user.getBirth()) + "'>" + DateUtils.getDay(user.getBirth()) + "</option>";
		} catch (Exception e) {
			b_year = "<option value='0'>请选择</option>";
			b_month = "<option value='0'>请选择</option>";
			b_day = "<option value='0'>请选择</option>";
		}
		
		String classes = "";
		if(user.getClasses() != null){
			classes = user.getClasses();
		}
		
		//联系方式
		String email = "";
		String qq = "";
		String tel = "";
		if(user.getEmail() != null){
			email = user.getEmail();
		}
		if(user.getQq() != null){
			qq = user.getQq();
		}
		if(user.getTel() != null){
			tel = user.getTel();
		}
		
		setReturn_HTML("<table border='0' cellspacing='0' cellpadding='0' class='setting_tab'>" +
				"<tr>" +
				"<th colspan='2'>基本信息</th>" +
			"</tr><tr>" +
				"<td id='margin_td' colspan='2'>&nbsp;</td>" +
			"</tr><tr>" +
				"<td align='center' width='150'>姓名：</td>" +
				"<td><input type='text' id='user_name' value='"  + user.getName() + "' id='' class='setting_input_text'/></td>" +
			"</tr><tr>" +
				"<td align='center' >性别：</td>" +
				"<td><input type='radio' id='user_gender' name='user_gender' " + gender_m + " value='男' /><label>男生</label><input type='radio' id='user_gender' name='user_gender' " + gender_w + " value='女'><label>女生</label></td>" +
			"</tr><tr>" +
				"<td align='center' >生日：</td>" +
				"<td><select id='user_year'>" + b_year + "</select><label>年</label>" +
					"<select id='user_month'>" + b_month + "</select><label>月</label>" +
					"<select id='user_day'>" + b_day + "</select><label>日</label>" +
				"</td>" +
			"</tr><tr>" +
				"<td id='margin_td' colspan='2'>&nbsp;</td>" +
			"</tr><tr>" +
				"<th colspan='2'>在读学校信息</th>" +
			"</tr><tr>" +
				"<td id='margin_td' colspan='2'>&nbsp;</td>" +
			"</tr><tr>" +
				"<td align='center' >学校：</td>" +
				"<td><select id='user_school'>" +
					"<option value='" + user.getSchool() + "'>" + user.getSchool() + "</option>" +
				"</select></td>" +
			"</tr><tr>" +
				"<td align='center' >学号：</td>" +
				"<td>10103278<span class='legalize_span'>√已认证</span></td>" +
			"</tr><tr>" +
				"<td align='center' >年级：</td>" +
				"<td><select id='user_grade'>" +
					"<option value='" + user.getGrade() + "'>" + user.getGrade() + "</option>" +
					"<option value='2009'>2009</option>" +
					"<option value='2010'>2010</option>" +
					"<option value='2011'>2011</option>" +
					"<option value='2012'>2012</option>" +
				"</select><label>级</label></td>" +
			"</tr><tr>" +
				"<td align='center' >学院：</td>" +
				"<td><select id='user_college'>" +
					"<option value='" + user.getCollege() + "'>" + user.getCollege() + "</option>" +
				"</select></td>" +
			"</tr><tr>" +
				"<td align='center' >班级：</td>" +
				"<td><input id='user_class' type='text'  value = '" + classes + "' class='setting_input_text'/></td>" +
			"</tr><tr>" +
				"<td align='center' >所在城市：</td>" +
				"<td><select>" +
					"<option id='user_city' value='" + user.getCity() + "'>" + user.getCity() + "</option>" +
				"</select></td>" +
			"</tr><tr>" +
				"<td id='margin_td' colspan='2'>&nbsp;</td>" +
			"</tr><tr>" +
				"<th colspan='2'>联系方式</th>" +
			"</tr><tr>" +
				"<td id='margin_td' colspan='2'>&nbsp;</td>" +
			"</tr><tr>" +
				"<td align='center' >常用邮箱：</td>" +
				"<td><input id='user_email' type='text' class='setting_input_text' value='" + email + "'/></td>" +
			"</tr><tr>" +
				"<td align='center' >QQ：</td>" +
				"<td><input type='text' id='user_qq' class='setting_input_text' value='" + qq + "'/></td>" +
			"</tr><tr><td align='center' >TEL：</td>" +
				"<td><input type='text'  id='user_tel' class='setting_input_text' value='" + tel + "'/></td>" +
			"</tr></table><div class='setting_save_btn'>保存修改</div>");
		
		return SUCCESS;
	}

	public String getReturn_HTML() {
		return return_HTML;
	}

	public void setReturn_HTML(String return_HTML) {
		this.return_HTML = return_HTML;
	}


}
