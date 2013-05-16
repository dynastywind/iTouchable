package com.ecust.dao.study.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.SelectionDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.Selection;

@Scope("prototype")
@Repository("selectionDao")
public class SelectionDaoImpl extends BaseDaoImpl<Selection,Long> implements SelectionDao{

	public SelectionDaoImpl() {
		super(Selection.class);
	}
	
	

}
