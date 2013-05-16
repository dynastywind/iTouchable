package com.ecust.dao.study.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.TemporaryTestDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.TemporaryTest;

@Scope("prototype")
@Repository("temporaryTestDao")
public class TemporaryTestDaoImpl extends BaseDaoImpl<TemporaryTest,String> implements TemporaryTestDao{

	public TemporaryTestDaoImpl() {
		super(TemporaryTest.class);
	}

}
