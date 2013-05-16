package com.ecust.dao.study.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.ChapterDao;
import com.ecust.dao.zone.impl.BaseDaoImpl;
import com.ecust.model.study.Chapter;
import com.ecust.model.zone.Course;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("chapterDao")
public class ChapterDaoImpl extends BaseDaoImpl<Chapter,Long> implements ChapterDao{

	public ChapterDaoImpl() {
		super(Chapter.class);
	}

	// 查询课程章节数量
	@Override
	public int countChapterByCourse(long course_id) {
		String hql = "select count(*) from Chapter c where c.course.id = ?";
		return Integer.parseInt(getTempList(hql, new Object[]{course_id}).get(0).toString());
	}
	
	//查询课程章节列表
	@Override
	public Page getChapterList(long course_id,int page) {
		String hql="from Chapter c where c.course.id=? order by c.orders ASC" ;
		int pageSize = 8;
		int pageNum = 5;
		return listForPage(hql,new Object[]{course_id}, pageSize, page, pageNum);
	}

	// 按课程查询所有章节
	@Override
	public List<Chapter> getAllChapter(long course_id) {
		String hql = "select new Chapter(c.id, c.name) from " +
				"Chapter c where c.course.id = ? order by c.orders ASC";
		return getList(hql, new Object[]{course_id});
	}

	//查询章节的所属课程
	@Override
	public Course getCourseByChapter(long chapter_id) {
		String hql = "select new Course(c.course.id) from Chapter c where c.id = ? ";
		return (Course) getTempList(hql, new Object[]{chapter_id}).get(0);
	}	

}
