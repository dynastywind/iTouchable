package com.ecust.action.zone;

import java.util.HashMap;
import java.util.Map;

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

/**
 * 登录到学习大厅
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@ParentPackage("base")
@Namespace("/user/hall")
@Results({@Result(name="success",location="/user/studyhall/studyHall.jsp"),@Result(name="error",type="redirect",location="/index.jsp")})
public class StudyHallAction extends BaseAction{
	
	@Autowired
	private SocietyDao societyDao;
	
	@Autowired
	private SocietyMemberDao societyMemberDao;
	
	private long c;
	
	private SocietyTemp society;
	
	@Action("studyHall")
	public String execute() throws Exception {
		long user_id = (Long) session.get("user");
		int role = societyMemberDao.getMemberAuthority(user_id, c);
		if(role < 4){ //大厅存在且当前用户拥有成员以上权限
			society = societyDao.getSimpleSocietyById(c);
			@SuppressWarnings("unchecked")
			Map<Long,Integer> member = (Map<Long, Integer>) session.get("member");//将已登录的大厅和角色权限保存到session
			if(member == null){
				member = new HashMap<Long,Integer>();
			}
			member.put(c, role);//可同时重复登录多个大厅
			session.put("member", member);
			return SUCCESS;
		}else{
			return ERROR;
		}
		
	}

	public long getC() {
		return c;
	}

	public void setC(long c) {
		this.c = c;
	}

	public SocietyTemp getSociety() {
		return society;
	}

	public void setSociety(SocietyTemp society) {
		this.society = society;
	}
	
}
