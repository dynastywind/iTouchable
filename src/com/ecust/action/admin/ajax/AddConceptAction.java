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
import com.ecust.dao.study.ConceptDao;
import com.ecust.dao.zone.CourseDao;
import com.ecust.model.study.Concept;

/**
 * 添加章节
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/admin")
@ParentPackage("json-default")
@Results({@Result(type="json")})
public class AddConceptAction extends BaseAction{
	
	@Autowired
	private ConceptDao conceptDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private ChapterDao chapterDao;
	
	private String name;
	
	private long course_id;
	
	private long chapter_id;
	
	private int weight;
	
	@Action("addConcept")
	public String execute() throws Exception {
		Concept concept = new Concept();
		concept.setChapter(chapterDao.getMiniModelById("Chapter", chapter_id));
		concept.setCourse(courseDao.getMiniModelById("Course", course_id));
		concept.setDate(new Date());
		concept.setName(name);
		concept.setWeight(weight);
		
		conceptDao.save(concept);
		return SUCCESS;
		
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}

	public long getChapter_id() {
		return chapter_id;
	}

	public void setChapter_id(long chapter_id) {
		this.chapter_id = chapter_id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
