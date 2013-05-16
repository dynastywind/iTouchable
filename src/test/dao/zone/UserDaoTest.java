package test.dao.zone;

import java.util.Date;
import java.util.Iterator;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Friends;
import com.ecust.model.zone.User;

public class UserDaoTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	UserDao userDao = (UserDao)fc.getBean("userDao");
	
	@Test
	public void testLogin() {
		String id="10103278";
		String password="1";
		int pass;
		if(userDao.isExist(id)){
			if(userDao.validateLogin(id, password)){
				pass = 1;//登录成功
			}else{
				pass = 0;//帐号或密码错误
			}
		}else{
			pass = 2;//帐号不存在
		}
		System.out.println(pass);
	}
	
	@Test
	public void testGetList(){
		User user = userDao.getMiniModelById("User", (long)1);
		System.out.println(user.getId());
	}

	@Test
	public void testDel(){
		userDao.delete((long)1);
	}
	
	@Test
	public void testGet(){
		User user = userDao.get((long)1);
		Iterator<Friends> iter = user.getFriends().iterator();
		while(iter.hasNext()){
			Friends friends = iter.next();
			System.out.println(friends.getId());
		}
		System.out.println("==========:"+user.getFriends().size());
	}
	
	@Test
	public void testUpdatePerson(){
		userDao.updatePersonInfo(3, "E一天", "男", new Date(), "", "", "", "", "", "", "", "");
	}
}
