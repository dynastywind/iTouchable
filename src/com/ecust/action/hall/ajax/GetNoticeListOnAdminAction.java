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
public class GetNoticeListOnAdminAction  extends BaseAction {

	@Autowired
	private SocietyNoticeDao societyNoticeDao;
	
	private String notice_HTML = "";
	
	private int return_count;
	
	private long society_id;
	
	@Override
	@Action("getNoticeListOnAdmin")
	public String execute() throws Exception {
		List<SocietyNotice> notice_list = societyNoticeDao.getAllNoticeBySociety(society_id);

		return_count = notice_list.size();
		String writer_type = "";
		String is_top = "";
		SocietyNotice notice = new SocietyNotice();
		for( int i = 0 ; i<return_count ; i++){
			notice = notice_list.get(i);
			if(notice.getWriter_type() == 1){
				writer_type = "厅主";
			}else if(notice.getWriter_type() == 2){
				writer_type = "管理员";
			}else if(notice.getWriter_type() == 3){
				writer_type = "指导老师";
			}
			if(notice.isIs_top()){
				is_top = "取消";
			}else{
				is_top = "";
			}
			notice_HTML = notice_HTML + "<div class='info_notice_list'>"+
					"<div class='info_notice_list_1'>&nbsp;&nbsp;" + notice.getContent() + "</div>"+
					"<div class='info_notice_list_2'>-By " + writer_type + ":"+ notice.getWriter_name() +" " + DateUtils.format(notice.getCreatedate(), DateUtils.FORMAT_SHORT) + " " +
							" <a href='javascript:;' is_top='" + notice.isIs_top() + "' n_id='" + notice.getId() + "' class='admin_notice_top_btn'>" + is_top + "置顶</a>|<a href='javascript:;' n_id='" + notice.getId() + "' class='admin_notice_delete_btn'>删除</a></div></div>";
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

	public int getReturn_count() {
		return return_count;
	}

	public void setReturn_count(int return_count) {
		this.return_count = return_count;
	}
}
