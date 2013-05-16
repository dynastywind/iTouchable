package com.ecust.dao.zone.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.FriendsDao;
import com.ecust.model.temp.FriendsTemp;
import com.ecust.model.zone.Friends;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("friendsDao")
public class FriendsDaoImpl extends BaseDaoImpl<Friends,Long> implements FriendsDao{
	
	private FriendsDaoImpl(){
		super(Friends.class);
	}

	//更新好友分组
	@Override
	public int updateFriendsGroup(long friends_id, long group_id) {
		String hql = "update Friends friends set friends.friendsgroup_own.id = ? where friends.id = ?";
		return getQuery(hql, new Object[]{group_id,friends_id}).executeUpdate();
	}	
	
	@Override
	public Page getAllFriendsTempByUserForPage(long user_id,int page) {
		String hql="select new com.ecust.model.temp.FriendsTemp(friends.id, friends.friends.id, friends.friends.name,friends.friends.gender,friends.friends.college,friends.friends.picture,friends.friends.state, friends.level) from Friends friends  where friends.is_Link = true and friends.owner.id =? " ;
		int pageSize = 10;
		int pageNum = 5;
		return listForPage(hql,new Object[]{user_id}, pageSize, page, pageNum);
	}

	@Override
	public Page getFriendsTempByGroupForPage(long group_id, int page) {
		String hql="select new com.ecust.model.temp.FriendsTemp(friends.id, friends.friends.id, friends.friends.name,friends.friends.gender,friends.friends.college,friends.friends.picture,friends.friends.state, friends.level) from Friends friends  where friends.is_Link = true and friends.friendsgroup_own.id=?" ;
		int pageSize = 10;
		int pageNum = 5;
		return listForPage(hql,new Object[]{group_id}, pageSize, page, pageNum);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getAllFriendsIdByUser(long user_id) {
		String hql="select friends.friends.id from Friends friends  where friends.is_Link = true and friends.owner.id = ?" ;
		return getList(hql, new Object[]{user_id});
	}

	@SuppressWarnings("unchecked")
	public List<FriendsTemp> getFriendsTempByGroup(long group_id){
		String hql="select new com.ecust.model.temp.FriendsTemp(friends.id, friends.friends.id, friends.friends.name,friends.friends.gender,friends.friends.college,friends.friends.picture,friends.friends.state, friends.level) from Friends friends where friends.is_Link = true and friends.friendsgroup_own.id = ?";
		return getTempList(hql, new Object[]{group_id});
	}
	
	public List<Friends> getFriendsByGroup(long group_id){
		String hql="select friends from Friends friends where friends.is_Link = true and friends.friendsgroup_own.id = ?";
		return getList(hql, new Object[]{group_id});
	}
	
	@SuppressWarnings("unchecked")
	public List<FriendsTemp> getUngroupedFriendsTemp(long user_id){
		String hql="select new com.ecust.model.temp.FriendsTemp(friends.id, friends.friends.id, friends.friends.name,friends.friends.gender,friends.friends.college,friends.friends.picture,friends.friends.state, friends.level) from Friends friends  where friends.is_Link = true and friends.owner.id = ? and friends.friendsgroup_own is null" ;
		return getTempList(hql, new Object[]{user_id});
	}

	public Page getUngroupedFriendsTempForPage(long user_id,int page){
		String hql="select new com.ecust.model.temp.FriendsTemp(friends.id, friends.friends.id, friends.friends.name,friends.friends.gender,friends.friends.college,friends.friends.picture,friends.friends.state, friends.level) from Friends friends  where friends.is_Link = true and friends.owner.id = ? and friends.friendsgroup_own is null" ;
		int pageSize = 10;
		int pageNum = 5;
		return listForPage(hql,new Object[]{user_id}, pageSize, page, pageNum);
	}
	
	//判断是否是好友
	public Boolean isFriends(long user1,long user2){
		return validate("select count(*) from Friends friends where friends.is_Link = true and friends.owner.id=? and friends.friends.id=?",new Object[]{user1,user2});
	}

	//查询发出申请信息的好友关系
	@Override
	public FriendsTemp getFriendsByMessage(long message_id) {
		String hql = "select new com.ecust.model.temp.FriendsTemp(f.id,f.owner.id,f.friends.id) from Friends f where f.message.id = ?";
		return (FriendsTemp) getTempList(hql, new Object[]{message_id}).get(0);
	}

	//设置是否确定关系
	@Override
	public int updateLink(long id,boolean is_link) {
		String hql = "update Friends f set f.is_Link = ? where f.id = ?";
		return getQuery(hql, new Object[]{is_link,id}).executeUpdate();
	}
	
}
