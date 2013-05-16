package test.dao.zone;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.zone.SocietyDao;
import com.ecust.dao.zone.SocietyNoticeDao;
import com.ecust.model.zone.SocietyNotice;

public class SocietyNoticeDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	SocietyDao societyDao = (SocietyDao)fc.getBean("societyDao");
	SocietyNoticeDao societyNoticeDao = (SocietyNoticeDao)fc.getBean("societyNoticeDao");
	
	
	@Test
	public void testSave(){
		SocietyNotice societyNotice = new SocietyNotice();
		societyNotice.setContent("为保证2013年2月22-24日期间奉贤校区本科生补考工作的顺利进行，经协商，在原有班次的基础上增加相应校车班次（其他班次见车队原有安排）！");
		societyNotice.setSociety_own(societyDao.getMiniModelById("Society", (long)1));
		societyNotice.setWriter_type(2);
		societyNotice.setCreatedate(new Date());
		
		societyNoticeDao.save(societyNotice);
	}

	@Test
	public void testTop(){
		societyNoticeDao.setTop(1,10, true);
	}
}
