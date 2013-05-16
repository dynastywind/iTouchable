package com.ecust.dao.zone.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.TeacherDao;
import com.ecust.model.zone.Teacher;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("teacherDao")
public class TeacherDaoImpl extends BaseDaoImpl<Teacher,Long> implements TeacherDao{

	public TeacherDaoImpl(){
		super(Teacher.class);
	}

	//按课程查询教师-分页
	@Override
	public Page getTeacherByCourse(long course_id, int page) {
		String hql="select new Teacher(t.id, t.id_number, t.name, t.school," +
				"t.college, t.state, t.last_login, t.point," +
				"t.date, t.course_own, t.title) from Teacher t where t.course_own.id = ?";
		int pageSize = 8;
		int pageNum = 5;
		return listForPage(hql,new Object[]{course_id}, pageSize, page, pageNum);
	}
	
	
}
