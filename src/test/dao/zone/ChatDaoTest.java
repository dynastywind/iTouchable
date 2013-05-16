package test.dao.zone;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.ChatDao;
import com.ecust.dao.zone.MessageDao;
import com.ecust.model.zone.Chat;
import com.ecust.model.zone.Message;

public class ChatDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	MessageDao messageDao = (MessageDao)fc.getBean("messageDao");
	ChatDao chatDao = (ChatDao)fc.getBean("chatDao");
	
	@After
	public void after(){
	}
	
	@Test
	public void testSaveMessage(){
		Chat chat = new Chat();
		Message message = new Message();
		
		message.setText("aaa");
		chat.setText("bb");
		
		messageDao.save(message);
		chatDao.save(chat);
	}

	@Test
	public void testDel(){
		//Chat chat =  chatDao.get((long)3);
		
	}
	
	@Test
	public void testMap(){
		Map<Long,Integer> member = new HashMap<Long,Integer>();
		Map<String,Object> session = new HashMap<String,Object>();
		member.put((long)1, 1);
		session.put("member", member);
		
		System.out.println(member.get((long)1));
	}
	
}
