package com.ecust.dao.zone;

import java.util.List;

import com.ecust.model.temp.SocietyMemberTemp;
import com.ecust.model.zone.SocietyMember;
import com.ecust.model.zone.User;

public interface SocietyMemberDao extends BaseDao<SocietyMember,Long>{

	/**
	 * 获得成员的简要信息，按加入角色排序
	 * @param society_id
	 * @return 成员的姓名、id、角色
	 */
	public List<SocietyMemberTemp> getSimpleMemberBySociety(long society_id);
	
	/**
	 * 获得成员的详细信息
	 * @param society_id
	 * @return member_id, member_date,member_level, member_silence, member_role,
	 * user_id, user_name, user_picture,user_gender, user_college, user_grad
	 */
	public List<SocietyMemberTemp> getMemberBySociety(long society_id);
	
	/**
	 * 判断是不是成员(包括厅主、管理员、指导教师)，拥有成员以上权限的用户
	 * @param user_id
	 * @param society_id
	 * @return
	 */
	public boolean isMember(long user_id,long society_id);
	
	/**
	 * 判断是不是管理员，拥有管理员以上权限的用户
	 * @param user_id
	 * @param society_id
	 * @return
	 */
	public boolean isAdminOrSuperior(long user_id,long society_id);
	
	/**
	 * 判断是不是厅主，拥有厅主以上权限的用户
	 * @param user_id
	 * @param society_id
	 * @return
	 */
	public boolean isLordOrSuperior(long user_id,long society_id);
	
	/**
	 * 获得用户在大厅的权限 
	 * @param user_id
	 * @param society_id
	 * @return 4:非大厅成员，无任何权限、0：普通成员、1：管理员、2：厅主、3：指导教师
	 */
	public int getMemberAuthority(long user_id,long society_id);
	
	/**
	 * 查询厅主信息
	 * @param society_id
	 * @return 厅主的id,name
	 */
	public User getLordBySociety(long society_id);
	
	/**
	 * 查询指导教师信息
	 * @param society_id
	 * @return 教师的id,name
	 */
	public User getTeacherBySociety(long society_id);
	
	
	/**
	 * 更新is_link字段
	 * @param member_id
	 * @param is_link
	 * @return
	 */
	public int updateSetLink(long member_id, boolean is_link);
	
	/**
	 * 设置或取消管理员
	 * @param member
	 * @param is_admin true:设为管理员
	 * @return
	 */
	public int upadteSetAdmin(long member_id, boolean is_admin);
	
	
}
