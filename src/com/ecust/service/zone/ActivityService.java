package com.ecust.service.zone;

import com.ecust.util.Page;



public interface ActivityService {
	
	/**
	 * 查询学习社区所有或单个好友的动态
	 * @param user_id 当前用户id
	 * @param friends_id 0:所有好友、>0:单个好友
	 * @param type a:所有、 t:topic话题、m:movement学习动态
	 * @param topic_type 0:所有话题、1:待解决、2:已解决、3:精华话题
	 * @param page
	 * @return
	 */
	public Page listActivityForPageOnZone(long user_id,long friends_id,String type,int topic_type, int page);
}
