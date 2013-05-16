package com.ecust.dao.zone;

import com.ecust.model.zone.Course;
import com.ecust.util.Page;

public interface CourseDao  extends BaseDao<Course,Long>{

	/**
	 * 查询课程列表
	 * @param page
	 * @return
	 */
	public Page getCourseList(int page);
}
