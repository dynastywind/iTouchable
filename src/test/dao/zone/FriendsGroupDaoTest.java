package test.dao.zone;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.FriendsDao;
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.FriendsGroup;
import com.ecust.model.zone.User;
import com.ecust.service.zone.FriendsService;

public class FriendsGroupDaoTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	FriendsGroupDao friendsGroupDao = (FriendsGroupDao)fc.getBean("friendsGroupDao");
	UserDao userDao = (UserDao)fc.getBean("userDao");
	FriendsDao friendsDao = (FriendsDao)fc.getBean("friendsDao");
	FriendsService friendsService = (FriendsService)fc.getBean("friendsService");
	
	@Test
	public void testSave() {
		FriendsGroup friendsGroup = new FriendsGroup();
		User user = userDao.get((long)31);
		friendsGroup.setUser_own(user);
		friendsGroup.setGroup_name("同学");
		
		friendsGroupDao.save(friendsGroup);
	}
	
	//不需要
	@Test
	public void testSaveUngrouped(){
		FriendsGroup friendsGroup = new FriendsGroup();
		friendsGroup.setUser_own(null);
		friendsGroup.setGroup_name("未分组");
		
		friendsGroupDao.save(friendsGroup);
	}
	
	
	@Test
	public void testUpdate(){
		FriendsGroup friendsGroup = friendsGroupDao.get((long)9);
		
		friendsGroup.setGroup_name("aa");
		friendsGroupDao.update(friendsGroup);
		
	}

}
