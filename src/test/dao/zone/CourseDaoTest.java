package test.dao.zone;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.CourseDao;
import com.ecust.model.zone.Course;

/**
 * 
 * @author lbz
 *
 */
public class CourseDaoTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	CourseDao courseDao = (CourseDao)fc.getBean("courseDao");
	
	@Test
	public void testSave() {
		Course course = new Course();
		course.setName("C/C++程序设计");
		
		courseDao.save(course);
		
		
		
	}
	

}
