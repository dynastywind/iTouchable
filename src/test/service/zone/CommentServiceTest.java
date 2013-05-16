package test.service.zone;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.service.zone.CommentService;

public class CommentServiceTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	CommentService commentService = (CommentService)fc.getBean("commentService");
	
	@Test
	public void testGetByTopic() {
	}

}
