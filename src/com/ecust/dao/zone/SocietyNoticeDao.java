package com.ecust.dao.zone;

import java.util.List;

import com.ecust.model.zone.SocietyNotice;

public interface SocietyNoticeDao extends BaseDao<SocietyNotice,Long> {

	/**
	 * 查询所有公告
	 * @param society_id
	 * @return
	 */
	public List<SocietyNotice> getAllNoticeBySociety(long society_id);
	
	/**
	 * 将公告置顶
	 * @param notice_id
	 * @return
	 */
	public int setTop(long society_id,long notice_id,boolean is_top);
	
}
