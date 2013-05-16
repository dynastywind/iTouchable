package com.ecust.dao.study;

import java.util.List;

import com.ecust.dao.zone.BaseDao;
import com.ecust.model.study.ConceptQuestion;

public interface ConceptQuestionDao extends BaseDao<ConceptQuestion,Long>{
	public List<ConceptQuestion> getById(long id);
}
