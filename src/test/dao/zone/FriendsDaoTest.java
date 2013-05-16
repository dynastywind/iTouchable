package test.dao.zone;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.FriendsDao;
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.dao.zone.MessageDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Friends;
import com.ecust.model.zone.FriendsGroup;
import com.ecust.model.zone.Message;
import com.ecust.model.zone.User;

public class FriendsDaoTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	FriendsDao friendsDao = (FriendsDao)fc.getBean("friendsDao");
	UserDao userDao = (UserDao)fc.getBean("userDao");
	MessageDao messageDao = (MessageDao)fc.getBean("messageDao");
	FriendsGroupDao friendsGroupDao = (FriendsGroupDao)fc.getBean("friendsGroupDao");
	
	@Test
	public void testSaveInit() {
		Friends friends = new Friends();
		User owner = userDao.get((long)1);
		for(int i=20;i<28;i++){
			User friend = userDao.get((long)i);
			FriendsGroup friendsGroup = friendsGroupDao.get((long)7);
			
			
			friends.setOwner(owner);
			friends.setFriends(friend);
			friends.setDate(new Date());
			friends.setIs_Link(true);
			friends.setFriendsgroup_own(friendsGroup);
			
			
				
			friendsDao.save(friends);
			friendsGroupDao.update(friendsGroup);
		}
		
	}
	
	@Test
	public void testSave() {
		Friends friends = new Friends();
		User owner = userDao.get((long)31);
		User friend = userDao.get((long)20);
		FriendsGroup friendsGroup = friendsGroupDao.get((long)1);
		
		friends.setOwner(owner);
		friends.setFriends(friend);
		friends.setDate(new Date());
		friends.setIs_Link(true);
		friends.setFriendsgroup_own(friendsGroup);
		
		friendsDao.save(friends);
		friendsGroupDao.update(friendsGroup);
		
	}

	
	
	@Test
	public void testDel(){
		friendsDao.delete((long)1);
	}
	
	@Test
	public void testDddFriend(){
		Friends friends = new Friends();
		User user1 = userDao.get((long)2);
		User user2 = userDao.get((long)5);
		Message message = new Message();
		
		friends.setDate(new Date());
		friends.setOwner(user1);
		friends.setFriends(user2);
		friends.setMessage(message);
		
		message.setCreate_date(new Date());
		message.setFrom_user(user1);
		message.setTo_user(user2);
		message.setMessage_type(2);
		message.setFriends(friends);
		
		friendsDao.save(friends);
		messageDao.save(message);
		
		
	}
	
	@Test
	public void testGetMessage(){
		Message message = friendsDao.get((long)29).getMessage();
		System.out.println(message.getId());
	}
	
	//处理好友申请
	@Test
	public void testDealAddFriends(){
		Friends friends = friendsDao.get((long)1);
		Message message = friends.getMessage();
		
		message.setText("你是哪个啊？");
		
		friendsDao.update(friends);
	}
	
	@Test
	public void testUpdate(){
		System.out.println(friendsDao.updateFriendsGroup(1, 2));
	}
	
}
