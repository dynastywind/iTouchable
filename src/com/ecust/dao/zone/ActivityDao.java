package com.ecust.dao.zone;

import com.ecust.model.zone.Activity;
import com.ecust.util.Page;

public interface ActivityDao extends BaseDao<Activity,Long>{

	/**
	 * 按类型查看学习大厅全部或单个成员动态
	 * @param user_id 0:所有成员、>0:单个成员
	 * @param society_id 0:学习社区 >0:学习大厅
	 * @param type a:所有、 t:topic话题、m:movement学习动态
	 * @param topic_type 0:所有话题、1:待解决、2:已解决、3:精华话题
	 * @param page
	 * @return
	 */
	public Page listActivityForPageByTypeOnSociety(long user_id,long society_id,String type,int topic_type,int page);
	
	/**
	 * 更新标记是否已解决
	 * @param id
	 * @param finish
	 * @return
	 */
	public int updateIsFinish(long id,boolean finish);
}
