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
import com.ecust.util.DateUtils;
import com.ecust.util.Page;

/**
 * 章节列表
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/admin")
@ParentPackage("json-default")
@Results({@Result(type="json")})
public class GetChapterListActio extends BaseAction{
	
	@Autowired
	private ChapterDao chapterDao;
	
	private long course_id;
	
	private String html = "";
	
	private String pageHTML;//页码导航
	
	private int page = 0;//页码
	
	@Action("getChapterList")
	public String execute() throws Exception {
		
		Page pageBean = chapterDao.getChapterList(course_id,page);
		
		@SuppressWarnings("unchecked")
		Iterator<Chapter> iter = pageBean.getList().iterator();
		Chapter chapter = new Chapter();
		int n = (pageBean.getCurrentPage()-1)*8; //编号
		while(iter.hasNext()){
			chapter = iter.next();
			n++;
			html = html + "<tr>" +
    					"<td width='19' height='30' class='tab_td_1'>" + n + "</td>" +
    					"<td width='30'><input type='checkbox' name='checkbox' value='checkbox' /></td>" +
    					"<td width='80'>第" + n + "章</td>" +
    					"<td width='200' align='left'>&nbsp;" + chapter.getName() + "</td>" +
    					"<td width='80'>" + 0 + "</td>" +
    					"<td width='180'>" + 0 + "</td>" +
    					"<td width='80'>" + 0 + "</td>" +
    					"<td width='80'>" + 0 + "</td>" +
    					"<td width='80'>" + 0 + "</td>" +
    					"<td width='130'>" + DateUtils.format(chapter.getDate(), DateUtils.FORMAT_LONG) + "</td>" +
    					"<td><a href='javascript:;' class='common_tab_a course_admin_a'>上移</a><a href='javascript:;' class='common_tab_a course_admin_a'>下移</a><a href='javascript:;' class='common_tab_a course_del_a'>删除</a></td>" +
  					"</tr>";
		}
		
		String firstPage;//上一页html
		String lastPage;//下一页html
		String preHide = "";
		String nexHide = "";
		String pageNum = "";
		//是否存在“上一页”
		if(pageBean.getCurrentPage()==1){
			firstPage = "上一页 ";
		}else{
			firstPage = "<a href='javascript:;' id='-1' class='page_a'>上一页</a> ";
		}
		
		if(pageBean.getTotalPage()>5){
			if(pageBean.getCurrentPage()>3){
				preHide = "...";
			}
			
			if(pageBean.getCurrentPage()<pageBean.getTotalPage()-2){
				nexHide = "...";
			}
		}
		
		//页码数字
		for(int i = 0;i< pageBean.getTotalPage();i++){
			if(i>pageBean.getLower()-1 && i<pageBean.getUpper()-1){
				String a_class = "";
				if(pageBean.getCurrentPage() == (i+1)){
					a_class = " page_curr_a";
				}
				pageNum = pageNum + " <a href='javascript:;' id='"+(i+1)+"' class='page_a"+a_class+"'>"+(i+1)+"</a> ";
			}
		}
		//是否存在“下一页”
		if(pageBean.getCurrentPage()==pageBean.getTotalPage()){
			lastPage = " 下一页";
		}else{
			lastPage = " <a href='javascript:;' class='page_a' id='+1'>下一页</a>";
		}
		pageHTML = "共有"+pageBean.getAllRow()+"条记录，"+ pageBean.getTotalPage() +"页 " + firstPage + preHide + pageNum + nexHide + lastPage;//翻页
		return SUCCESS;
		
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getPageHTML() {
		return pageHTML;
	}

	public void setPageHTML(String pageHTML) {
		this.pageHTML = pageHTML;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}


	
}
