package com.ecust.dao.study;

import java.util.List;

import com.ecust.dao.zone.BaseDao;
import com.ecust.model.study.Chapter;
import com.ecust.model.zone.Course;
import com.ecust.util.Page;

public interface ChapterDao extends BaseDao<Chapter,Long>{
	
	/**
	 * 查询课程章节数量
	 * @param course_id
	 * @return
	 */
	public int countChapterByCourse(long course_id);
	
	/**
	 * 查询课程章节列表-分页
	 * @param course_id
	 * @param page
	 * @return
	 */
	public Page getChapterList(long course_id,int page);
	
	/**
	 * 按课程查询所有章节
	 * @param course_id
	 * @return
	 */
	public List<Chapter> getAllChapter(long course_id);
	
	/**
	 * 查询章节的所属课程
	 * @param chapter_id
	 * @return 课程new Course(id)
	 */
	public Course getCourseByChapter(long chapter_id);
}
