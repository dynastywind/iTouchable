package com.ecust.dao.study.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.TestDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.Test;

@Scope("prototype")
@Repository("testDao")
public class TestDaoImpl extends BaseDaoImpl<Test, String> implements TestDao{
	public TestDaoImpl() {
		super(Test.class);
	}
	public String getAddress(String id) {
		// TODO Auto-generated method stub
		return get(id).getTestAddress();
	}
}
