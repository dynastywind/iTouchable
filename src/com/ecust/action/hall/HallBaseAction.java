package com.ecust.action.hall;

import java.util.Map;

import com.ecust.action.common.BaseAction;

/**
 * 大厅成员
 * @author lbz
 *
 */
@SuppressWarnings("serial")
public class HallBaseAction extends BaseAction{
	
	/**
	 * 验证一级权限：普通
	 * @return
	 */
	protected boolean validateMemberAuthority(long society_id){
		int role = getRoleSession(society_id);
		if(role < 4){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 验证二级权限：管理员、厅主、指导教师
	 * @return
	 */
	protected boolean validateAdminAuthority(long society_id){
		int role = getRoleSession(society_id);
		if(role == 1 || role == 2 || role ==3){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 验证三级权限：厅主、指导教师
	 * @return
	 */
	protected boolean validateLordAuthority(long society_id){
		int role = getRoleSession(society_id);
		if(role == 2 || role ==3){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 验证四级权限：指导教师
	 * @return
	 */
	protected boolean validateTeacherAuthority(long society_id){
		int role = getRoleSession(society_id);
		if(role ==3){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得保存在session中的权限值
	 * @param society_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected int getRoleSession(long society_id){
		try {
			Map<Long,Integer> member = (Map<Long, Integer>) session.get("member");
			return member.get(society_id);
		} catch (Exception e) {
			return 4;//非成员
		}
	}

}
