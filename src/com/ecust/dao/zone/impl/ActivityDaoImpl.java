package com.ecust.dao.zone.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.ActivityDao;
import com.ecust.model.zone.Activity;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("activityDao")
public class ActivityDaoImpl extends BaseDaoImpl<Activity,Long> implements ActivityDao{

	public ActivityDaoImpl() {
		super(Activity.class);
	}

	//按类型查看全部或单个成员动态
		@Override
		public Page listActivityForPageByTypeOnSociety(long user_id,long society_id,String type,int topic_type, int page) {
			int pageSize = 8; //每页记录数
			int pageNum = 3; //显示的页码数量(奇数)
			
			String hql_u = "";
			if(user_id > 0){
				hql_u = "and activity.user_own.id = " + user_id;
			}
			
			String hql = "select new com.ecust.model.temp.ActivityTemp";
			if(type.equals("m")){ //学习动态
				hql = hql + "(activity.id, activity.date," +
						"activity.text, activity.share_count, activity.ip, activity.a_type," +
						"activity.user_own.id, activity.user_own.name, activity.user_own.picture)" +
						"from Activity activity join activity.course_own.society society where society.id = ? and activity.a_type = 'm' " + hql_u + " order by activity.date desc";
				
				return  listForPage(hql,new Object[]{society_id}, pageSize, page, pageNum);
			
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
						"from Activity activity where activity.society_own.id = ? and activity.a_type = 't' "+hql_t+" " + hql_u + " order by activity.date desc";
				
				return  listForPage(hql,new Object[]{society_id}, pageSize, page, pageNum);
			
			}else{ //所有动态
				hql = hql + "(activity.id, activity.date," +
						"activity.text, activity.share_count, activity.ip," +
						"activity.is_finish, activity.a_type," +
						"activity.is_essence, activity.in_zone," +
						"activity.user_own.id, activity.user_own.name, activity.user_own.picture)" +
						"from Activity activity left join activity.course_own.society society where (society.id = ? or activity.society_own.id = ?) " + hql_u + " order by activity.date desc";
				return  listForPage(hql,new Object[]{society_id,society_id}, pageSize, page, pageNum);
			}
			
		}

		//更新标记是否已解决
		@Override
		public int updateIsFinish(long id, boolean finish) {
			String hql = "update Activity a set a.is_finish = ? where a.id = ?";
			return getQuery(hql,new Object[]{finish,id}).executeUpdate();
		}
}
