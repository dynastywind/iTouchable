package com.ecust.dao.study;

import com.ecust.dao.zone.BaseDao;
import com.ecust.model.study.CourseStudent;
import com.ecust.util.Page;

public interface CourseStudentDao extends BaseDao<CourseStudent,Long>{
	
	/**
	 * 查询选课学习列表-分页
	 * @param course_id
	 * @return
	 */
	public Page getStudentByCourse(long course_id, int page);
}
