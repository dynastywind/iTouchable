package com.ecust.dao.study.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.ConceptDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.Concept;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("conceptDao")
public class ConceptDaoImpl extends BaseDaoImpl<Concept,Long> implements ConceptDao{

	public ConceptDaoImpl() {
		super(Concept.class);
	}


	//查询课程知识点列表
	@Override
	public Page getConceptList(long course_id, long chapter_id, int page) {
		String hql ="";
		if(chapter_id == 0){
			hql ="from Concept c where c.course.id=? order by c.name ASC" ;
		}else{
			hql ="from Concept c where c.chapter.id=? order by c.name ASC" ;
		}
		int pageSize = 8;
		int pageNum = 5;
		return listForPage(hql,new Object[]{course_id}, pageSize, page, pageNum);
	}


	//查询课程知识点
	@Override
	public List<Concept> getAllConcept(long course_id) {
		String hql = "select new Concept(c.id,c.name,c.weight) from Concept c where c.course.id = ? order by c.name ASC";
		return getList(hql, new Object[]{course_id});
	}


	@Override
	public List<Concept> getConceptByName(String name) {
		// TODO Auto-generated method stub
		String hql="from Concept where name=?";
		return getList(hql,new Object[]{name});
	}

	
}
