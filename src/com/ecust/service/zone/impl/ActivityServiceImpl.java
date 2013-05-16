package com.ecust.service.zone.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecust.dao.zone.ActivityDao;
import com.ecust.dao.zone.FriendsDao;
import com.ecust.service.zone.ActivityService;
import com.ecust.util.Page;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityDao activityDao;
	
	@Autowired
	private FriendsDao friendsDao;


	
	//查询学习社区所有或单个好友的动态
	@Override
	public Page listActivityForPageOnZone(long user_id,long friends_id,String type,int topic_type, int page){
		
		int pageSize = 8; //每页记录数
		int pageNum = 3; //显示的页码数量(奇数)
		
		@SuppressWarnings("unchecked")
		List<Long> friendsList = friendsDao.getAllFriendsIdByUser(user_id);//得到好友的id
		friendsList.add(user_id);//加上自己的id
		
		String hql_f = "";
		if(friends_id == 0){
			hql_f = "activity.user_own.id in(:args0)"; //所有好友
		}else{
			hql_f = "activity.user_own.id = ?"; //单个好友
		}
		
		
		String hql = "select new com.ecust.model.temp.ActivityTemp";
		if(type.equals("m")){ //学习动态
			hql = hql + "(activity.id, activity.date," +
					"activity.text, activity.share_count, activity.ip, activity.a_type," +
					"activity.user_own.id, activity.user_own.name, activity.user_own.picture)" +
					"from Activity activity where activity.a_type = 'm' and " + hql_f + " order by activity.date desc";
			
		}else if(type.equals("t")){ //话题
			String hql_t = "";
			if(topic_type == 0){
				//得到所有话题
				hql_t ="";
			}else if(topic_type == 1){
				//得到待解决话题
				hql_t = "and activity.is_finish = false ";
			}else if(topic_type == 2){
				//得到已解决话题
				hql_t = "and activity.is_finish = true ";
			}else if(topic_type == 3){
				//得到精华话题
				hql_t = "and activity.is_essence = true ";
			}	
			
			hql = hql + "(activity.id, activity.date," +
					"activity.text, activity.share_count, activity.ip," +
					"activity.is_finish, activity.a_type," +
					"activity.is_essence, activity.in_zone," +
					"activity.user_own.id, activity.user_own.name, activity.user_own.picture)" +
					"from Activity activity where activity.a_type = 't' "+hql_t+" and " + hql_f + " order by activity.date desc";
			
		}else{ //所有动态
			hql = hql + "(activity.id, activity.date," +
					"activity.text, activity.share_count, activity.ip," +
					"activity.is_finish, activity.a_type," +
					"activity.is_essence, activity.in_zone," +
					"activity.user_own.id, activity.user_own.name, activity.user_own.picture)" +
					"from Activity activity where " + hql_f + " order by activity.date desc";
		}
		
		if(friends_id == 0){//所有好友
			return  activityDao.listForPageIn(hql,new Object[]{friendsList}, pageSize, page, pageNum);
		}else if(friendsList.contains(friends_id)){//单个好友，且是当前用户的好友时
			return  activityDao.listForPage(hql,new Object[]{friends_id}, pageSize, page, pageNum);
		}else{
			return null;
		}
		
	}


}
