package com.ecust.dao.study.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.ConceptQuestionDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.ConceptQuestion;

@Scope("prototype")
@Repository("conceptQuestionDao")
public class ConceptQuestionDaoImpl extends BaseDaoImpl<ConceptQuestion,Long> implements ConceptQuestionDao{

	public ConceptQuestionDaoImpl() {
		super(ConceptQuestion.class);
	}

	public List<ConceptQuestion> getById(long id){
		String hql = "select c from ConceptQuestion as c where c.id like ?%";
		return getList(hql, new Object[]{id});
	}
}
