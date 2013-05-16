package com.ecust.dao.zone;

import java.util.Date;

import com.ecust.model.zone.User;
import com.ecust.util.Page;

/**
 * 用户
 * @author lbz
 *
 */
public interface UserDao extends BaseDao<User,Long>{

	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return Boolean 是否登录成功
	 */
	public Boolean validateLogin(String stu_id,String password);
	
	/**
	 * 帐号是否存在
	 * @param stu_id
	 * @return Boolean
	 */
	public Boolean isExist(String stu_id);
	
	/**
	 * 判断是否是教师
	 * @param user_id
	 * @return
	 */
	public Boolean isTeacher(long user_id);
	
	/**
	 * 寻找新好友
	 * @param keyword
	 * @return
	 */
	public Page searchUserByKeywordForPage(String keyword,int page);
	
	/**
	 * 在好友中查找
	 * @param keyword
	 * @param user_id
	 * @return
	 */
	public Page searchFriendsByKeywordForPage(String keyword,long user_id,int page);
	
	/**
	 * 用户基本信息
	 * @param id
	 * @return id,id_number, name, school,classes, rank, point, picture
	 */
	public User getSimpleUserById(long id);
	
	/**
	 * 根据学号得到id
	 * @param number
	 * @return
	 */
	public long getIdByNumber(String number);
	
	/**
	 * 查询用户资料
	 * @param user_id
	 * @return is_teacher, id_number, name,gender, birth,school,college,
				city, grade, classes, email,tel, qq
	 */
	public User getPersonInfoById(long user_id);
	
	/**
	 * 更新用户资料
	 * @param name
	 * @param gender
	 * @param birth
	 * @param school
	 * @param college
	 * @param city
	 * @param grade
	 * @param classes
	 * @param email
	 * @param tel
	 * @param qq
	 * @return
	 */
	public int updatePersonInfo(long user_id, String name, String gender, Date birth,String school,String college,
			String city, String grade, String classes, String email,String tel, String qq);
	
	/**
	 * 更新用户密码
	 * @param user_id
	 * @param password
	 * @return
	 */
	public int updateUserPassword(long user_id,String password);
	
	/**
	 * 根据id验证密码
	 * @param user_id
	 * @param password
	 * @return
	 */
	public boolean isPassword(long user_id,String password);
}
