package com.ecust.action.hall.ajax;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.MessageDao;
import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Message;

/**
 *
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class UpdateSetAdminAction  extends BaseAction {

	@Autowired
	private SocietyMemberDao memberDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserDao userDao;
	
	private long member_id;
	
	private long to_u_id;
	
	private boolean is_admin;
	
	private String society_name;
	
	@Override
	@Action("setAdmin")
	public String execute() throws Exception {
		Message message = new Message();
		
		message.setCreate_date(new Date());
		message.setFrom_user(userDao.getMiniModelById("User", (Long)session.get("user")));
		message.setTo_user(userDao.getMiniModelById("User", to_u_id));
		message.setMessage_type(4);//4代表系统消息
		
		if(is_admin){
			message.setText("已添加您为学习大厅“" + society_name + "”的管理员！");
		}else{
			message.setText("解除了您学习大厅“" + society_name + "”的管理员权限！");
		}
		messageDao.save(message);//保存到系统提示信息
		memberDao.upadteSetAdmin(member_id, is_admin);
		
		return SUCCESS;
	}


	public long getMember_id() {
		return member_id;
	}


	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}


	public long getTo_u_id() {
		return to_u_id;
	}


	public void setTo_u_id(long to_u_id) {
		this.to_u_id = to_u_id;
	}


	public boolean isIs_admin() {
		return is_admin;
	}


	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}


	public String getSociety_name() {
		return society_name;
	}


	public void setSociety_name(String society_name) {
		this.society_name = society_name;
	}

}
