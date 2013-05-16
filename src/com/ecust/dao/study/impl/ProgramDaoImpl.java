package com.ecust.dao.study.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.ProgramDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.Program;

@Scope("prototype")
@Repository("programDao")
public class ProgramDaoImpl extends BaseDaoImpl<Program,Long> implements ProgramDao{

	public ProgramDaoImpl() {
		super(Program.class);
	}
	
	

}
