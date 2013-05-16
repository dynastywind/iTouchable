package test.dao.study;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.study.CourseStudentDao;

public class CourseStudentDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	CourseStudentDao csDao = (CourseStudentDao)fc.getBean("courseStudentDao");
	
	@Test
	public void testGet() {
		System.out.println(csDao.getStudentByCourse(1, 1).getAllRow());
	}
	
	
}
