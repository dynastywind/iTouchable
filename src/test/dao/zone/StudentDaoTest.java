package test.dao.zone;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.StudentDao;
import com.ecust.model.zone.Student;

public class StudentDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	StudentDao studentDao = (StudentDao)fc.getBean("studentDao");
	
	@Test
	public void testSave() {
		Student student = new Student();
		student.setName("刘柏众");
		student.setCity("上海");
		student.setGender("男");
		student.setSchool("华东理工大学");
		student.setCollege("资源与环境工程学院");
		student.setClasses("环境102");
		student.setId_number("10103278");
		student.setPassword("1");
		
		
		studentDao.save(student);
		
		
		
	}
	
	@Test
	public void testSaveDataInit() {
		
		Student student = new Student();
		student.setName("刘柏众");
		student.setCity("上海");
		student.setGender("男");
		student.setSchool("华东理工大学");
		student.setCollege("资源与环境工程学院");
		student.setClasses("环境102");
		student.setId_number("10103278");
		student.setPassword("1");
		
		studentDao.save(student);
		
		for(int i = 0;i<30;i++){
			student.setName("示例" + i);
			student.setCity("上海");
			student.setGender("男");
			student.setSchool("华东理工大学");
			student.setCollege("资源与环境工程学院");
			student.setClasses("环境102");
			student.setId_number("101032"+i);
			student.setPassword("1");
			
			studentDao.save(student);
		}
	}
	

	@Test
	public void testDel(){
		studentDao.delete((long)1);
	}
}
