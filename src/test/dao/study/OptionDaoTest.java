package test.dao.study;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.study.QuestionDao;

public class OptionDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	QuestionDao questionDao = (QuestionDao)fc.getBean("questionDao");
	
	@Test
	public void testSave() {
		
	}
	
	@Test
	public void testList(){
		System.out.println("===:"+ questionDao.getProblemList(1, 0, 0, 0, 1).getAllRow());
	}
	
	@Test
	public void testFloat() {
		String list = "1.1,2.2,3";
		String list_str[] = list.split(",");
		float a = Float.parseFloat(list_str[1]);
		System.out.println(a);
	}
	
	
}
