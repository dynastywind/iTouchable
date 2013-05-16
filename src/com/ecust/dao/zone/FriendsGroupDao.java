package com.ecust.dao.zone;

import java.util.List;

import com.ecust.model.zone.FriendsGroup;

/**
 * 好友分组
 * @author lbz
 *
 */
public interface FriendsGroupDao extends BaseDao<FriendsGroup,Long>{

	/**
	 * 更新组名
	 * @param group_id
	 * @param name
	 * @return
	 */
	public int updateGroupName(long group_id,String name);
	
	/**
	 * 按用户查找分组列表
	 * @param user_id 用户id
	 * @return
	 */
	public List<FriendsGroup> getAllFriendsGroup(long user_id);
}
