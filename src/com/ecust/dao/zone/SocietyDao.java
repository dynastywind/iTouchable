package com.ecust.dao.zone;

import java.util.List;

import com.ecust.model.zone.Course;
import com.ecust.model.temp.SocietyTemp;
import com.ecust.model.zone.Society;

public interface SocietyDao extends BaseDao<Society,Long> {

	/**
	 * 获得大厅课程信息
	 * @param society_id
	 * @return 课程的id、name
	 */
	public Course getCourse(long society_id);
	
	/**
	 * 获得大厅的全部信息
	 * @param society_id
	 * @return
	 */
	public SocietyTemp getSocietyById(long society_id);
	
	/**
	 * 获得大厅的简要信息
	 * @param society_id
	 * @return
	 */
	public SocietyTemp getSimpleSocietyById(long society_id);
	
	/**
	 * 按成员查询大厅
	 * @param user_id
	 * @param type 大厅类型 1-自然班、2-教学班、3-学习小组
	 * @return 大厅id、名称列表
	 */
	public List<Society> getSocietyByMember(long user_id,int type);
}
