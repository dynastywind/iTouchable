package test.service.zone;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.service.zone.FriendsGroupService;

public class FriendsGroupServiceTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	FriendsGroupService friendsGroupService = (FriendsGroupService)fc.getBean("friendsGroupService");
	
	
	/**
	 * 删除分组，不删除好友
	 */
	@Test
	public void testDel(){
	}

}
