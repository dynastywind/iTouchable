package com.ecust.action.admin.ajax;

import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.study.ChapterDao;
import com.ecust.model.study.Chapter;

/**
 * 章节列表
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/admin")
@ParentPackage("json-default")
@Results({@Result(type="json")})
public class GetChapterOptionAction extends BaseAction{
	
	@Autowired
	private ChapterDao chapterDao;
	
	private long course_id;
	
	private String html = "";
	
	@Action("getChapterOption")
	public String execute() throws Exception {
		
		Iterator<Chapter> iter = chapterDao.getAllChapter(course_id).iterator();
		Chapter chapter = new Chapter();
		int n = 1;
		while(iter.hasNext()){
			chapter = iter.next();
			html = html + "<option value='" + chapter.getId() + "'>第" + n + "章 " + chapter.getName() + "</option>";
			n++;
		}
		return SUCCESS;
		
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}


	public long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}


	
}
