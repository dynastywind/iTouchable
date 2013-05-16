package test.dao.zone;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.SocietyDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Society;

public class SocietyDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	UserDao userDao = (UserDao)fc.getBean("userDao");
	SocietyDao societyDao = (SocietyDao)fc.getBean("societyDao");
	
	@Test
	public void testSave(){
		Society society = new Society();
		society.setCreatedate(new Date());
		societyDao.save(society);
	}

}
