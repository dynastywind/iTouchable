package com.ecust.action.hall.ajax;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.zone.SocietyNoticeDao;
import com.ecust.model.zone.SocietyNotice;
import com.ecust.util.DateUtils;

/**
 * 获得前3条公告
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/user/hall")
@ParentPackage("json-default")
@Results(@Result(type="json"))
public class GetTopNoticeAction  extends BaseAction {

	@Autowired
	private SocietyNoticeDao societyNoticeDao;
	
	private String notice_HTML = "";
	
	private long society_id;
	
	@Override
	@Action("getTopNotice")
	public String execute() throws Exception {
		List<SocietyNotice> list = societyNoticeDao.getAllNoticeBySociety(society_id);
		int top = 3;
		if(list.size()<3){
			top = list.size();
		}
		SocietyNotice societyNotice = new SocietyNotice();
		String is_top = "";
		for(int i = 0; i<top; i++){
			societyNotice = list.get(i);
			String writer_type = "厅主";
			if(societyNotice.getWriter_type() == 2){
				writer_type = "管理员";
			}
			if(societyNotice.isIs_top()){
				is_top = "置顶";
			}else{
				is_top = "";
			}
			notice_HTML = notice_HTML + "<div class='hall-notice-list'>" +
					"<div class='hall-notice-text'>&nbsp;&nbsp;"+list.get(i).getContent()+"</div>" +
					"<div class='hall-notice-person'>-By "+writer_type + " " + is_top + " "+DateUtils.format(societyNotice.getCreatedate(),DateUtils.FORMAT_SHORT)+"</div></div>";
		}
		
		return SUCCESS;
	}

	public String getNotice_HTML() {
		return notice_HTML;
	}

	public void setNotice_HTML(String notice_HTML) {
		this.notice_HTML = notice_HTML;
	}

	public long getSociety_id() {
		return society_id;
	}

	public void setSociety_id(long society_id) {
		this.society_id = society_id;
	}
}
