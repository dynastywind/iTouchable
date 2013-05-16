package com.ecust.action.admin.ajax;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.study.ChapterDao;
import com.ecust.dao.zone.CourseDao;
import com.ecust.model.study.Chapter;

/**
 * 添加章节
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/admin")
@ParentPackage("json-default")
@Results({@Result(type="json")})
public class AddChapterAction extends BaseAction{
	
	@Autowired
	private ChapterDao chapterDao;
	
	@Autowired
	private CourseDao courseDao;
	
	private String chapter_name;
	
	@Action("addChapter")
	public String execute() throws Exception {
		long course_id =  1;
		
		Chapter chapter = new Chapter();
		chapter.setDate(new Date());
		chapter.setName(chapter_name);
		chapter.setOrders(chapterDao.countChapterByCourse(course_id)+1);//章节顺序+1
		chapter.setCourse(courseDao.getMiniModelById("Course", course_id));
		

		chapterDao.save(chapter);
		return SUCCESS;
		
	}

	public String getChapter_name() {
		return chapter_name;
	}

	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	
}
