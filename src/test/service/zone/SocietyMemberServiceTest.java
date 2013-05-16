package test.service.zone;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.service.zone.SocietyMemberService;

public class SocietyMemberServiceTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	SocietyMemberService societyMemberService = (SocietyMemberService)fc.getBean("societyMemberService");
	
	
	
	
}
