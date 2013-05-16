package test.service.zone;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.service.zone.FriendsService;

public class FriendsServiceTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	FriendsService friendsService = (FriendsService)fc.getBean("friendsService");
	
	
	
}
