package com.ecust.dao.zone.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.SocietyDao;
import com.ecust.model.zone.Course;
import com.ecust.model.temp.SocietyTemp;
import com.ecust.model.zone.Society;

@Scope("prototype")
@Repository("societyDao")
public class SocietyDaoImpl extends BaseDaoImpl<Society,Long> implements SocietyDao {

	public SocietyDaoImpl() {
		super(Society.class);
	}
	
	//获得大厅课程信息
	@Override
	public Course getCourse(long society_id) {
		String hql = "select new Course(course.id,course.name) from Society society join society.course_own course where society.id = ? ";
		return (Course) getTempList(hql, new Object[]{society_id}).get(0);
	}

	//获得大厅的全部信息
	@Override
	public SocietyTemp getSocietyById(long society_id) {
		String hql = "select new com.ecust.model.temp.SocietyTemp(s.id, s.name, s.society_type,"+
			"s.society_info, s.createdate, s.course_own.id,"+
			"s.course_own.name) from Society s where s.id = ? ";
		return (SocietyTemp) getTempList(hql, new Object[]{society_id}).get(0);
	}
	
	//获得大厅的简要信息
	@Override
	public SocietyTemp getSimpleSocietyById(long society_id) {
		String hql = "select new com.ecust.model.temp.SocietyTemp(s.id, s.name, s.society_type,"+
				"s.course_own.name) from Society s where s.id = ? ";
			return (SocietyTemp) getTempList(hql, new Object[]{society_id}).get(0);
	}

	@Override
	public List<Society> getSocietyByMember(long user_id, int type) {
		String hql = "select new Society(s.id,s.name) from Society s join s.member m where m.user_own.id = ? and s.society_type = ? ";
		return getList(hql, new Object[]{user_id,type});
	}


}
