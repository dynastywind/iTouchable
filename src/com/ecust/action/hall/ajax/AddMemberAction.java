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
import com.ecust.dao.zone.SocietyDao;
import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Message;
import com.ecust.model.zone.Society;
import com.ecust.model.zone.SocietyMember;
import com.ecust.model.zone.User;


@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class AddMemberAction extends BaseAction {

	@Autowired
	private SocietyDao societyDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private SocietyMemberDao societyMemberDao;
	
	private long society_id;
	
	private String society_member;
	
	private String society_name;
	
	@Override
	@Action("addMember")
	public String execute() throws Exception {
		if(society_member.length()>0){ //当类型和名称不为空

			Society society = societyDao.getMiniModelById("Society", society_id);
			User from_u = userDao.getMiniModelById("User", (Long)session.get("user"));
			SocietyMember member = new SocietyMember();
			member.setSociety_own(society);

			try {
				String member_id[] = society_member.split(",");
				User user = new User();
				Message message = new Message();
				for(int i = 0;i<member_id.length;i++){
					if(!messageDao.isInviteMember(society_id, Long.parseLong(member_id[i]))){//当还未发出好友请求
						user = userDao.getMiniModelById("User", Long.parseLong(member_id[i]));
						
						member.setUser_own(user); //保存成员列表
						member.setLink(false);
						member.setRole(0);//0代表普通成员
						societyMemberDao.save(member);
						
						message.setMessage_type(3);//3代表邀请信息
						message.setCreate_date(new Date());
						message.setFrom_user(from_u);
						message.setTo_user(user);
						message.setMember(member);
						message.setText("加入学习大厅：“"+society_name+"”");
						messageDao.save(message);//保存到邀请信息
					}else{
					}
				}
			} catch (Exception e) {
			}
			
		}
		
		
		return SUCCESS;
	}


	public String getSociety_member() {
		return society_member;
	}

	public void setSociety_member(String society_member) {
		this.society_member = society_member;
	}


	public long getSociety_id() {
		return society_id;
	}


	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}


	public String getSociety_name() {
		return society_name;
	}


	public void setSociety_name(String society_name) {
		this.society_name = society_name;
	}


}
