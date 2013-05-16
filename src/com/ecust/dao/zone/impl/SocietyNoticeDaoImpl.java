package com.ecust.dao.zone.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.SocietyNoticeDao;
import com.ecust.model.zone.SocietyNotice;

@Scope("prototype")
@Repository("societyNoticeDao")
public class SocietyNoticeDaoImpl extends BaseDaoImpl<SocietyNotice,Long> implements SocietyNoticeDao {

	public SocietyNoticeDaoImpl() {
		super(SocietyNotice.class);
	}

	//查询所有公告
	@Override
	public List<SocietyNotice> getAllNoticeBySociety(long society_id) {
		String hql1="select new SocietyNotice(n.id, n.writer_type, n.writer_name,n.createdate, n.content,n.is_top) " +
				"from SocietyNotice n  where n.society_own.id = ? and n.is_top = true" ;
		List<SocietyNotice> list = getList(hql1, new Object[]{society_id});
		
		String hql2="select new SocietyNotice(n.id, n.writer_type, n.writer_name,n.createdate, n.content,n.is_top) " +
				"from SocietyNotice n  where n.society_own.id = ? and n.is_top = false order by n.createdate DESC" ;
		list.addAll(getList(hql2, new Object[]{society_id}));
		return  list;
	}

	//将公告置顶
	@Override
	public int setTop(long society_id,long notice_id,boolean is_top) {
		String hql = "update SocietyNotice n set n.is_top = false where n.society_own.id = ?";
		if(is_top){
			getQuery(hql, new Object[]{society_id}).executeUpdate();//当设置置顶时，重设所有,只能有一条被置顶
		}
		hql = "update SocietyNotice n set n.is_top = ? where n.id = ?";
		return getQuery(hql, new Object[]{is_top,notice_id}).executeUpdate();
	}

	
}
