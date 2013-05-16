package com.ecust.dao.zone.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.CourseDao;
import com.ecust.model.zone.Course;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl<Course,Long> implements CourseDao{

	public CourseDaoImpl() {
		super(Course.class);
	}

	//查询课程列表
	@Override
	public Page getCourseList(int page) {
		String hql="from Course c order by c.date DESC" ;
		int pageSize = 10;
		int pageNum = 5;
		return listForPage(hql,new Object[]{}, pageSize, page, pageNum);
	}

}
