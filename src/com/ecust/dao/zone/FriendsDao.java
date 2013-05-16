package com.ecust.dao.zone;

import java.util.List;

import com.ecust.model.temp.FriendsTemp;
import com.ecust.model.zone.Friends;
import com.ecust.util.Page;

public interface FriendsDao extends BaseDao<Friends,Long>{

	/**
	 * 更新好友分组
	 * @param friends_id
	 * @param group_id
	 * @return 更新的行数
	 */
	public int updateFriendsGroup(long friends_id,long group_id);
	
	/**
	 * 按用户获取所有好友的id
	 * @param user_id
	 * @return id的list
	 */
	@SuppressWarnings("rawtypes")
	public List getAllFriendsIdByUser(long user_id);
	
	/**
	 * 按用户获取所有好友列表（分页显示）
	 * @param user_id
	 * @param page 页码
	 * @return (部分字段）
	 */
	public Page getAllFriendsTempByUserForPage(long user_id, int page);
	
	/**
	 * 按分组获取好友列表
	 * @param group_id 分组id
	 * @param page 页码
	 * @return (部分字段）
	 */
	public Page getFriendsTempByGroupForPage(long group_id, int page);
	
	/**
	 * 按分组获取好友，不分页
	 * @param group_id
	 * @return (部分字段）
	 */
	public List<FriendsTemp> getFriendsTempByGroup(long group_id);
	
	/**
	 * 按分组获取好友，不分页
	 * @param group_id
	 * @return 全部字段
	 */
	public List<Friends> getFriendsByGroup(long group_id);
	
	/**
	 * 获取未分组的好友
	 * @param user_id
	 * @return (部分字段）
	 */
	public List<FriendsTemp> getUngroupedFriendsTemp(long user_id);
	
	/**
	 * 获取未分组的好友,分页
	 * @param user_id
	 * @return (部分字段）
	 */
	public Page getUngroupedFriendsTempForPage(long user_id,int page);
	
	/**
	 * 判断是否为好友关系
	 * @param user1
	 * @param user2
	 * @return 
	 */
	public Boolean isFriends(long user1,long user2);
	
	/**
	 * 查询发出申请信息的好友关系
	 * @param message_id
	 * @return id, own_id,friends_id
	 */
	public FriendsTemp getFriendsByMessage(long message_id);
	
	/**
	 * 设置是否确定关系
	 * @param is_link
	 * @return
	 */
	public int updateLink(long id,boolean is_link);
	
}

