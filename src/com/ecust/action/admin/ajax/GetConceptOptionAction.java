package com.ecust.action.admin.ajax;

import java.util.Iterator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.study.ConceptDao;
import com.ecust.model.study.Concept;

/**
 * 章节列表
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/admin")
@ParentPackage("json-default")
@Results({@Result(type="json")})
public class GetConceptOptionAction extends BaseAction{
	
	@Autowired
	private ConceptDao conceptDao;
	
	private long course_id;
	
	private String html = "";
	
	@Action("getConceptOption")
	public String execute() throws Exception {
		
		Iterator<Concept> iter = conceptDao.getAllConcept(course_id).iterator();
		Concept concept = new Concept();
		int n = 1;
		while(iter.hasNext()){
			concept = iter.next();
			html = html + "<li id='" + concept.getId() + "' title='点击选择'>" + n + ". <span class='know_name' id="+concept.getWeight()+">" + concept.getName() + "<span></li>";
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
