package test.dao.study;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.study.ChapterDao;
import com.ecust.dao.zone.CourseDao;
import com.ecust.model.study.Chapter;

public class ChapterDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	ChapterDao chapterDao = (ChapterDao)fc.getBean("chapterDao");
	CourseDao courseDao = (CourseDao)fc.getBean("courseDao");
	
	@Test
	public void testSave() {
	}
	
	
	@Test
	public void testCount() {
		System.out.println("===========:"+chapterDao.countChapterByCourse(1));
	}
	
	@Test
	public void testGetCourse() {
		System.out.println(chapterDao.getCourseByChapter(1).getId());
	}
	
	@Test
	public void testInitSave(){
		Chapter c = new Chapter();
		c.setCourse(courseDao.getMiniModelById("Course", (long)1));
		String c_str[] = {"C语言程序设计预备知识","C语言程序设计基础","数据类型与表达式","顺序程序设计",
				"选择结构程序设计","循环结构程序设计","数组","函数","指针","预处理命令",
				"复杂数据类型","文件"};
		for(int i = 0; i<c_str.length; i++){
			c.setOrders(i);
			c.setName(c_str[i]);
			c.setDate(new Date());
			
			chapterDao.save(c);
		}
	}
}
