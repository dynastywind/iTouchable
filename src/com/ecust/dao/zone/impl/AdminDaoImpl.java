package com.ecust.dao.zone.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.AdminDao;
import com.ecust.model.zone.Admin;

@Scope("prototype")
@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin,Long> implements AdminDao{

	public AdminDaoImpl() {
		super(Admin.class);
	}

	//验证登录
	@Override
	public Boolean validateLogin(String account, String password) {
		return validate("select count(*) from Admin where account=? and password=?",new Object[]{account,password});
	}
		
	//检测是否帐号存在
	@Override
	public Boolean isExist(String account){
		return validate("select count(*) from Admin where account=?",new Object[]{account});
	}

	//按帐号查询管理员信息
	@Override
	public Admin getAdminByCount(String account) {
		return getList("from Admin where account = ?",new Object[]{account}).get(0);
	}
	
		
}
