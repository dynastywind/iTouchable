package test.service.zone;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.service.zone.SocietyNoticeService;

public class SocietyNoticeServiceTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	SocietyNoticeService societyNoticeService = (SocietyNoticeService)fc.getBean("societyNoticeService");
	

}
