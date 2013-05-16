package com.ecust.dao.study.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.FillDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.Fill;

@Scope("prototype")
@Repository("fillDao")
public class FillDaoImpl extends BaseDaoImpl<Fill,Long> implements FillDao{

	public FillDaoImpl() {
		super(Fill.class);
	}
	
	

}
