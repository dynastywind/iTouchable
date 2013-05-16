package com.ecust.dao.study;

import java.util.List;

import com.ecust.dao.zone.BaseDao;
import com.ecust.model.study.Concept;
import com.ecust.util.Page;

public interface ConceptDao extends BaseDao<Concept,Long>{
	
	/**
	 * 查询课程知识点列表-分页
	 * @param course_id
	 * @param chapter_id 当chapter_id=0时，返回该课程所有知识点
	 * @param page
	 * @return
	 */
	public Page getConceptList(long course_id,long chapter_id,int page);
	
	/**
	 * 查询课程知识点
	 * @param course_id
	 * @return
	 */
	public List<Concept> getAllConcept(long course_id);
	
	/**
	 * 根据概念名查找概念
	 * @param name 概念名
	 * @return 概念链表
	 */
	public List<Concept> getConceptByName(String name);
}
