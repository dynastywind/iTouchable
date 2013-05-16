package test.dao.zone;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.ActivityDao;
import com.ecust.dao.zone.CommentDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.zone.Activity;
import com.ecust.model.zone.Comment;
import com.ecust.model.zone.User;

public class CommentDaoTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	CommentDao commentDao = (CommentDao)fc.getBean("commentDao");
	ActivityDao activityDao = (ActivityDao)fc.getBean("activityDao");
	UserDao userDao = (UserDao)fc.getBean("userDao");
	
	@Test
	public void testSave() {
		Comment comment = new Comment();
		User user_own = userDao.get((long)31);
		Activity topic_own = activityDao.get((long)1);
		
		comment.setCreatedate(new Date());
		comment.setUser_own(user_own);
		comment.setActivity_own(topic_own);
		comment.setComment_text("aaaaaaaaaaaaaa");
		
		commentDao.save(comment);
	}

	@Test
	public void testSaveInit() {
		Comment comment = new Comment();
		User user_own = userDao.get((long)31);
		Activity topic_own = activityDao.get((long)2);
		comment.setUser_own(user_own);
		comment.setActivity_own(topic_own);
		
	    for(int i = 0;i<20;i++){
	    	comment.setCreatedate(new Date());
			comment.setComment_text("aaaaacccccccc"+i);
			commentDao.save(comment);
	    }
		
	}
	
	@Test
	public void testDel(){
		commentDao.delete((long)1);
	}

}
