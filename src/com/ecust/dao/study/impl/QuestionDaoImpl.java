package com.ecust.dao.study.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.QuestionDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.Question;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("questionDao")
public class QuestionDaoImpl extends BaseDaoImpl<Question,String> implements QuestionDao{

	public QuestionDaoImpl() {
		super(Question.class);
	}

	
	//查询题目列表-分页
	@Override
	public Page getProblemList(long course_id, long chapter_id, int type, int sub_type,
			int page) {
		String hql = "select new com.ecust.model.temp.QuestionTemp(q.id,c.id, c.orders, c.name," +
						"q.difficulty, q.type,q.sub_type, q.content, q.explanation," +
						"q.date, q.right_count, q.error_count) from Question q join q.chapter c where q.course.id=? ";
		int pageSize = 8;
		int pageNum = 5;
		if(chapter_id > 0){
			hql = hql + " and c.id = ? " ;
		}
		switch(type){
		case 1:
			hql = hql + " and q.type = 1 " ; break;
		case 2:
			hql = hql + " and q.type = 2 " ; break;
		}
		switch(sub_type){
		case 1:
			hql = hql + " and q.sub_type = 1 " ; break;
		case 2:
			hql = hql + " and q.sub_type = 2 " ; break;
		case 3:
			hql = hql + " and q.sub_type = 3 "; break;
		}
		if(chapter_id > 0){
			return listForPage(hql,new Object[]{course_id,chapter_id}, pageSize, page, pageNum);
		}else{
			return listForPage(hql,new Object[]{course_id}, pageSize, page, pageNum);
		}
	}

}
