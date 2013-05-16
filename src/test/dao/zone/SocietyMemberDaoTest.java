package test.dao.zone;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.SocietyMemberDao;

public class SocietyMemberDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	SocietyMemberDao memberDao = (SocietyMemberDao)fc.getBean("societyMemberDao");
	
	@Test
	public void testGetRole() {
		System.out.println(memberDao.getMemberAuthority(2, 1));
		
	}
	
}
