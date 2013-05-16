package com.ecust.dao.study;

import com.ecust.dao.zone.BaseDao;
import com.ecust.model.study.Question;
import com.ecust.util.Page;

public interface QuestionDao extends BaseDao<Question,String>{
	
	/**
	 * 查询题目列表-分页
	 * @param course_id 
	 * @param chapter_id 0所有
	 * @param type 类型：0所有，1练习题，1测试题
	 * @param sub_type 类型：0所有,1选择题，2填空题，3编程题，
	 * @param page
	 * @return
	 */
	public Page getProblemList(long course_id,long chapter_id, int type, int sub_type, int page);
}
