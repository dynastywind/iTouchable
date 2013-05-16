package com.ecust.dao.zone.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.StudentDao;
import com.ecust.model.zone.Student;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("studentDao")
public class StudentDaoImpl extends BaseDaoImpl<Student,Long> implements StudentDao{

	public StudentDaoImpl(){
		super(Student.class);
	}
	
	//根据学号得到一个学生
	public Student getStudent(String stu_id){
		return (Student)getObject("from User where id_number=?", new Object[]{stu_id});
	}
	
	//查询学生列表-分页
	@Override
	public Page getStudentList(long course_id, int page) {
		String hql="select new Student(s.id, s.id_number, s.name, s.school," +
				"s.college, s.grade, s.state, s.last_login," +
				"s.point, s.login_times , s.date) from Student s " ;
		int pageSize = 9;
		int pageNum = 5;
		return listForPage(hql,new Object[]{}, pageSize, page, pageNum);
	}

}
	
	
