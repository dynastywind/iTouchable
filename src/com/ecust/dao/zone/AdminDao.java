package com.ecust.dao.zone;

import com.ecust.model.zone.Admin;

public interface AdminDao extends BaseDao<Admin,Long>{

	/**
	 * 管理员登录
	 * @param account 用户名
	 * @param password 密码
	 * @return Boolean 是否登录成功
	 */
	public Boolean validateLogin(String account,String password);
	
	/**
	 * 帐号是否存在
	 * @param account
	 * @return Boolean
	 */
	public Boolean isExist(String account);
	
	/**
	 * 按帐号查询管理员信息
	 * @param count
	 * @return
	 */
	public Admin getAdminByCount(String account);
}
