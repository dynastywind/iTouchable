package com.ecust.dao.study.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.CourseStudentDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.CourseStudent;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("courseStudentDao")
public class CourseStudentDaoImpl extends BaseDaoImpl<CourseStudent,Long> implements CourseStudentDao{

	public CourseStudentDaoImpl() {
		super(CourseStudent.class);
	}

		//查询选课学习列表-分页
		@Override
		public Page getStudentByCourse(long course_id,int page) {
			String hql="select new com.ecust.model.temp.CourseStudentTemp(c.id, c.student.id, c.student.name, c.student.classes," +
						"c.student.id_number, c.exp, c.active, c.timeInSolving," +
						"c.timesToSolve, c.date) from CourseStudent c where c.course.id = ? " ;
			int pageSize = 8;
			int pageNum = 5;
			return listForPage(hql,new Object[]{course_id}, pageSize, page, pageNum);
		}
}
