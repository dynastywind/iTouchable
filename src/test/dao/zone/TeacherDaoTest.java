package test.dao.zone;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.TeacherDao;

public class TeacherDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	TeacherDao teacherDao = (TeacherDao)fc.getBean("teacherDao");
	
	@Test
	public void testGet() {
		System.out.println(teacherDao.getTeacherByCourse(1, 1));
	}
	
}
