package com.ecust.dao.zone.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.model.zone.FriendsGroup;

@Scope("prototype")
@Repository("friendsGroupDao")
public class FriendsGroupDaoImpl extends BaseDaoImpl<FriendsGroup,Long> implements FriendsGroupDao{
	
	public FriendsGroupDaoImpl(){
		super(FriendsGroup.class);
	}

	//更新组名
	@Override
	public int updateGroupName(long group_id, String name) {
		String hql = "update FriendsGroup g set g.group_name = ? where g.id = ?";
		return getQuery(hql, new Object[]{name,group_id}).executeUpdate();
	}
	
	//分组列表
	@Override
	public List<FriendsGroup> getAllFriendsGroup(long user_id){
		String hql = "select new FriendsGroup(friendsGroup.id,friendsGroup.group_name) from FriendsGroup friendsGroup where friendsGroup.user_own.id = ?";
		return getList(hql, new Object[]{user_id});
	}	

}
