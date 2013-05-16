package test.service.zone;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.service.zone.MessageService;

public class MessageServiceTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	MessageService messageService = (MessageService)fc.getBean("messageService");
	

}
