package test.dao.zone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ecust.dao.zone.MessageDao;
import com.ecust.model.zone.Message;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:com/applicationContext.xml")
@Transactional
public class MessageDaoTest {

	
	@Autowired
	private MessageDao messageDao;
	
	@Test
	public void testGetFriends(){
		messageDao.get((long)1);
	}
	
	@Test
	public void testGetChat(){
		Message message = messageDao.get((long)18);
		System.out.println("=============:" + message.getChats().size());
	}
	
	@Test
	public void testIsInviteMember(){
		System.out.println(messageDao.isInviteMember(1, 3));
	}
}
