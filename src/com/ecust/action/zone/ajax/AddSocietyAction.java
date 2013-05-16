package com.ecust.action.zone.ajax;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.CourseDao;
import com.ecust.dao.zone.MessageDao;
import com.ecust.dao.zone.SocietyDao;
import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Message;
import com.ecust.model.zone.Society;
import com.ecust.model.zone.SocietyMember;
import com.ecust.model.zone.User;


@SuppressWarnings("serial")
@Namespace("/user")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class AddSocietyAction extends BaseAction {

	@Autowired
	private SocietyDao societyDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private SocietyMemberDao societyMemberDao;
	
	private int society_type;
	
	private String society_name;
	
	private String society_info;
	
	private long society_teacher;
	
	private long society_course;
	
	private String society_member;
	
	@Override
	@Action("addSociety")
	public String execute() throws Exception {
		if(society_type != 0 && society_name.length()>0){ //当类型和名称不为空
			long user_id = (Long) session.get("user");
			User creator = userDao.getMiniModelById("User", user_id);
			Society society = new Society();
			society.setName(society_name);
			society.setCreatedate(new Date());
			society.setSociety_info(society_info);
			society.setSociety_type(society_type);
			society.setCourse_own(courseDao.getMiniModelById("Course", society_course));
			societyDao.save(society);
			
			SocietyMember member = new SocietyMember();
			member.setLink(true);
			member.setSociety_own(society);
			member.setRole(2);//2代表厅主
			member.setUser_own(creator);
			societyMemberDao.save(member);//保存厅主到成员
			
			if(userDao.isTeacher(society_teacher)){ //是教师身份时
				member.setRole(3);//3代表指导老师
				member.setUser_own(userDao.getMiniModelById("User", society_teacher));
				societyMemberDao.save(member);//保存指导老师到成员
			}
			

			try {
				String member_id[] = society_member.split(",");
				User user = new User();
				Message message = new Message();
				for(int i = 0;i<member_id.length;i++){
					user = userDao.getMiniModelById("User", Long.parseLong(member_id[i]));
					
					member.setUser_own(user); //保存成员列表
					member.setLink(false);
					member.setRole(0);//0代表普通成员
					societyMemberDao.save(member);
					
					message.setMessage_type(3);//3代表邀请信息
					message.setCreate_date(new Date());
					message.setFrom_user(creator);
					message.setTo_user(user);
					message.setMember(member);
					message.setText("加入学习大厅：“"+society_name+"”");
					messageDao.save(message);//保存到邀请信息
				}
			} catch (Exception e) {
			}
			
		}
		
		
		return SUCCESS;
	}


	public int getSociety_type() {
		return society_type;
	}

	public void setSociety_type(int society_type) {
		this.society_type = society_type;
	}

	public String getSociety_name() {
		return society_name;
	}

	public void setSociety_name(String society_name) {
		this.society_name = society_name;
	}

	public String getSociety_info() {
		return society_info;
	}

	public void setSociety_info(String society_info) {
		this.society_info = society_info;
	}

	public String getSociety_member() {
		return society_member;
	}

	public void setSociety_member(String society_member) {
		this.society_member = society_member;
	}

	public long getSociety_teacher() {
		return society_teacher;
	}

	public void setSociety_teacher(long society_teacher) {
		this.society_teacher = society_teacher;
	}

	public long getSociety_course() {
		return society_course;
	}

	public void setSociety_course(long society_course) {
		this.society_course = society_course;
	}


}
