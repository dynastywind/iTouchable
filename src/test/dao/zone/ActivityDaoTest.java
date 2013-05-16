package test.dao.zone;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.ActivityDao;

public class ActivityDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	ActivityDao activityDao = (ActivityDao)fc.getBean("activityDao");
	
	@Test
	public void testDel(){
		activityDao.delete((long)11);
	}

}
