package com.ecust.dao.zone.impl;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.User;
import com.ecust.util.Page;

@Scope("prototype")
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User,Long> implements UserDao{

	public UserDaoImpl(){
		super(User.class);
	}
	//验证登录
	@Override
	public Boolean validateLogin(String stu_id, String password) {
		return validate("select count(*) from User where id_number=? and password=?",new Object[]{stu_id,password});
	}
		
	//检测是否帐号存在
	@Override
	public Boolean isExist(String stu_id){
		return validate("select count(*) from User where id_number=?",new Object[]{stu_id});
	}
	
	//判断是否是教师
	@Override
	public Boolean isTeacher(long user_id) {
		return validate("select count(*) from User where is_teacher = true and id=?",new Object[]{user_id});
	}
	
	@Override
	public Page searchUserByKeywordForPage(String keyword,int page) {
		String hql = "select new User(user.id, user.name, user.gender, user.college,user.state, user.picture) from User user where user.name like ?" +
				"or user.id_number like ?" +
				"or user.city like ?" +
				"or user.school like ?" +
				"or user.college like ?" +
				"or user.email like ?" +
				"or user.tel like ?" +
				"or user.gender like ?" +
				"or user.qq like ?";
		
		int pageSize = 8;
		int pageNum = 5;
		return listForPage(hql,new Object[]{"%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%"}, pageSize, page, pageNum);
	}

	@Override
	public Page searchFriendsByKeywordForPage(String keyword, long user_id,int page) {
		String hql = "select new com.ecust.model.temp.FriendsTemp(friends.id, friends.friends.id, friends.friends.name,friends.friends.gender,friends.friends.college,friends.friends.picture,friends.friends.state, friends.level) from Friends friends where (friends.friends.name like ?" +
				"or friends.friends.id_number like ?" +
				"or friends.friends.city like ?" +
				"or friends.friends.school like ?" +
				"or friends.friends.college like ?" +
				"or friends.friends.email like ?" +
				"or friends.friends.tel like ?" +
				"or friends.friends.gender like ?" +
				"or friends.friends.qq like ?) and friends.is_Link = true and friends.owner.id = ?";
		
		int pageSize = 8;
		int pageNum = 5;
		return listForPage(hql,new Object[]{"%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%",user_id}, pageSize, page, pageNum);
	}

	//根据number得到用户id
	@Override
	public long getIdByNumber(String number) {
		try {
			long id = getList("select new User(user.id) from User user where user.id_number=?", new Object[]{number}).get(0).getId();
			return id;
		} catch (Exception e) {
			return (Long) null;
		}
	}

	@Override
	public User getSimpleUserById(long id) {
		try {
			return getList("select new User(user.id,user.id_number, user.name, user.school,user.classes, user.rank, user.point, user.picture) from User user where user.id=?", new Object[]{id}).get(0);
		} catch (Exception e) {
			return null;
		}
	}
	
	//查询用户资料
	@Override
	public User getPersonInfoById(long user_id) {
		String hql = "select new User(u.is_teacher, u.id_number, u.name," +
				"u.gender, u.birth, u.school, u.college," +
				"u.city, u.grade, u.classes, u.email," +
				"u.tel, u.qq) from User u where u.id=?";
		try {
			return getList(hql, new Object[]{user_id}).get(0);
		} catch (Exception e) {
			return null;
		}
	}
	
	//更新用户资料
	@Override
	public int updatePersonInfo(long user_id, String name, String gender, Date birth,
			String school, String college, String city, String grade,
			String classes, String email, String tel, String qq) {
		String hql = "update User u set u.name = ?, u.gender = ?, u.birth = ?, u.school = ?, u.college = ?," +
				"u.city = ?, u.grade = ?, u.classes = ?, u.email = ?, u.tel = ?, u.qq = ? where u.id = ?";
		return getQuery(hql,new Object[]{name,gender,birth,school, college, city, grade, classes, email, tel, qq, user_id}).executeUpdate();
	}
	
	//更新用户密码
	@Override
	public int updateUserPassword(long user_id, String password) {
		String hql = "update User u set u.password = ? where u.id = ?";
		if(password != null && password.length()>0){ //密码不为空
			return getQuery(hql,new Object[]{password,user_id}).executeUpdate();
		}else{
			return 0;
		}
	}
	
	//根据id验证密码
	@Override
	public boolean isPassword(long user_id, String password) {
		return validate("select count(*) from User u where u.id = ? and u.password=?",new Object[]{user_id,password});
	}
	
	

	
}
