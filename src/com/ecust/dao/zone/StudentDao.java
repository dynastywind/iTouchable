package com.ecust.dao.zone;

import com.ecust.model.zone.Student;
import com.ecust.util.Page;

/**
 * 学生角色
 * @author lbz
 *
 */
public interface StudentDao extends BaseDao<Student,Long>{
	
	/**
	 * 从数据库中获取一个学生
	 * @return
	 */
	public Student getStudent(String stu_id);
	
	/**
	 * 查询学生列表-分页
	 * @param course_id
	 * @param page
	 * @return Student(long id, String id_number, String name, String school,
						String college, String grade, boolean state, Date last_login,
						int point, int login_times)
	 */
	public Page getStudentList(long course_id, int page);
	
	
}
