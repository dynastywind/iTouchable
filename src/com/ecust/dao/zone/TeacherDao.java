package com.ecust.dao.zone;

import com.ecust.model.zone.Teacher;
import com.ecust.util.Page;

/**
 * 学生角色
 * @author lbz
 *
 */
public interface TeacherDao extends BaseDao<Teacher,Long>{
	
	/**
	 * 按课程查询教师-分页
	 * @param course_id
	 * @param page
	 */
	public Page getTeacherByCourse(long course_id, int page);
}
