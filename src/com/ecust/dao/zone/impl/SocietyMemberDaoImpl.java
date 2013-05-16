package com.ecust.dao.zone.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.model.temp.SocietyMemberTemp;
import com.ecust.model.zone.SocietyMember;
import com.ecust.model.zone.User;

@Scope("prototype")
@Repository("societyMemberDao")
public class SocietyMemberDaoImpl extends BaseDaoImpl<SocietyMember,Long> implements SocietyMemberDao {

	public SocietyMemberDaoImpl() {
		super(SocietyMember.class);
	}
	
	//获得成员的简要信息
	@SuppressWarnings("unchecked")
	@Override
	public List<SocietyMemberTemp> getSimpleMemberBySociety(long society_id) {
		String hql = "select new com.ecust.model.temp.SocietyMemberTemp(m.role,m.user_own.id,m.user_own.name) from SocietyMember" +
				" m where m.society_own.id = ? and m.link = true order by m.role DESC";
		return getTempList(hql, new Object[]{society_id});
	}
	
	//获得成员的详细信息
	@SuppressWarnings("unchecked")
	@Override
	public List<SocietyMemberTemp> getMemberBySociety(long society_id) {
		String hql = "select new com.ecust.model.temp.SocietyMemberTemp(m.id,m.date,m.level,m.silence,m.role," +
				"m.user_own.id,m.user_own.name,m.user_own.picture,m.user_own.gender, m.user_own.college, m.user_own.grade) from SocietyMember" +
				" m where m.society_own.id = ? and m.link = true order by m.role DESC";
		return getTempList(hql, new Object[]{society_id});
	}	

	//判断是不是成员
	@Override
	public boolean isMember(long user_id, long society_id) {
		return validate("select count(*) from SocietyMember m where m.user_own.id=? and m.society_own.id=?",new Object[]{user_id,society_id});
	}

	//判断是不是管理员
		@Override
		public boolean isAdminOrSuperior(long user_id, long society_id) {
			return validate("select count(*) from SocietyMember m where m.user_own.id=? and m.society_own.id=? and (m.role = 1 or m.role = 2 or m.role = 3)",new Object[]{user_id,society_id});
		}
	
	//判断是不是厅主
	@Override
	public boolean isLordOrSuperior(long user_id, long society_id) {
		return validate("select count(*) from SocietyMember m where m.user_own.id=? and m.society_own.id=? and (m.role = 2 or m.role = 3)",new Object[]{user_id,society_id});
	}

	//获得用户在大厅的权限 
	@Override
	public int getMemberAuthority(long user_id, long society_id) {
		String hql = "select m.role from SocietyMember m where m.user_own.id = ? and m.society_own.id = ? and m.link = true";
		try {
			return (Integer) getTempList(hql, new Object[]{user_id,society_id}).get(0);
		} catch (Exception e) {
			return 4;//大厅不存在或非成员
		}
	}

	//查询厅主信息
	@Override
	public User getLordBySociety(long society_id) {
		String hql = "select new User(m.user_own.id,m.user_own.name) from SocietyMember m where m.society_own.id= ? and m.role = 2";
		return (User) getTempList(hql, new Object[]{society_id}).get(0);
	}	
	
	//查询教师信息
	@Override
	public User getTeacherBySociety(long society_id) {
		try {
			String hql = "select new User(m.user_own.id,m.user_own.name) from SocietyMember m where m.society_own.id= ? and m.role = 3";
			return (User) getTempList(hql, new Object[]{society_id}).get(0);
		} catch (Exception e) {
			return null;
		}	
	}

	//更新is_link字段
	@Override
	public int updateSetLink(long member_id, boolean is_link) {
		String hql = "update SocietyMember m set m.link = ? where m.id = ?";
		return getQuery(hql, new Object[]{is_link,member_id}).executeUpdate();
	}

	//设置或取消管理员
	@Override
	public int upadteSetAdmin(long member_id, boolean is_admin) {
		String hql = "";
		if(is_admin){
			hql = "update SocietyMember m set m.role = 1 where m.id = ?";
		}else{
			hql = "update SocietyMember m set m.role = 0 where m.id = ?";
		}
		return getQuery(hql, new Object[]{member_id}).executeUpdate();
	}

}
