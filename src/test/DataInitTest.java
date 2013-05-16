package test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.study.ChapterDao;
import com.ecust.dao.study.CourseStudentDao;
import com.ecust.dao.zone.ActivityDao;
import com.ecust.dao.zone.AdminDao;
import com.ecust.dao.zone.CourseDao;
import com.ecust.dao.zone.FriendsDao;
import com.ecust.dao.zone.FriendsGroupDao;
import com.ecust.dao.zone.SocietyDao;
import com.ecust.dao.zone.SocietyMemberDao;
import com.ecust.dao.zone.SocietyNoticeDao;
import com.ecust.dao.zone.StudentDao;
import com.ecust.dao.zone.TeacherDao;
import com.ecust.dao.zone.UserDao;
import com.ecust.model.study.Chapter;
import com.ecust.model.study.CourseStudent;
import com.ecust.model.zone.Activity;
import com.ecust.model.zone.Admin;
import com.ecust.model.zone.Course;
import com.ecust.model.zone.Friends;
import com.ecust.model.zone.FriendsGroup;
import com.ecust.model.zone.Society;
import com.ecust.model.zone.SocietyMember;
import com.ecust.model.zone.SocietyNotice;
import com.ecust.model.zone.Student;
import com.ecust.model.zone.Teacher;
import com.ecust.model.zone.User;

/**
 * 初始化数据库
 * @author lbz
 *
 */
public class DataInitTest {

	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	UserDao userDao = (UserDao)fc.getBean("userDao");
	AdminDao adminDao = (AdminDao)fc.getBean("adminDao");
	StudentDao studentDao = (StudentDao)fc.getBean("studentDao");
	CourseDao courseDao = (CourseDao)fc.getBean("courseDao");
	TeacherDao teacherDao = (TeacherDao)fc.getBean("teacherDao");
	FriendsDao friendsDao = (FriendsDao)fc.getBean("friendsDao");
	SocietyDao societyDao = (SocietyDao)fc.getBean("societyDao");
	FriendsGroupDao friendsGroupDao = (FriendsGroupDao)fc.getBean("friendsGroupDao");
	ActivityDao activityDao = (ActivityDao)fc.getBean("activityDao");
	SocietyMemberDao societyMemberDao = (SocietyMemberDao)fc.getBean("societyMemberDao");
	SocietyNoticeDao societyNoticeDao = (SocietyNoticeDao)fc.getBean("societyNoticeDao");
	ChapterDao chapterDao = (ChapterDao)fc.getBean("chapterDao");
	CourseStudentDao csDao = (CourseStudentDao)fc.getBean("courseStudentDao");
	
	@Test
	public void testSaveDataInit() {
		
		/* =============  初始管理员数据  =========== */
		Admin admin = new Admin();
		admin.setAccount("1");
		admin.setPassword("1");
		
		adminDao.save(admin);
		
		
		/* =============  初始化学生数据  =========== */
		
		Student student = new Student();
		student.setName("刘柏众");
		student.setCity("上海");
		student.setGender("男");
		student.setSchool("华东理工大学");
		student.setCollege("资源与环境工程学院");
		student.setClasses("环境102");
		student.setId_number("10103278");
		student.setGrade("2010");
		student.setDate(new Date());
		student.setPassword("1");
		
		studentDao.save(student);
		
		for(int i = 0;i<20;i++){
			student.setName("学生" + i);
			student.setCity("上海");
			student.setGender("男");
			student.setSchool("华东理工大学");
			student.setCollege("资源与环境工程学院");
			student.setClasses("环境102");
			student.setGrade("2010");
			student.setId_number("101032"+i);
			student.setDate(new Date());
			student.setPassword("1");
			
			studentDao.save(student);
		}
		/* =============  初始化课程数据  =========== */
		
		Course course = new Course();
		course.setName("C语言程序设计");
		course.setDate(new Date());
		
		courseDao.save(course);
		
		/* =============  初始化选课信息  =========== */
		
		for(int i = 0; i<15; i++){
			CourseStudent cs = new CourseStudent();
			cs.setCourse(course);
			cs.setStudent(studentDao.getMiniModelById("Student", (long)i));
			cs.setDate(new Date());
			csDao.save(cs);
		}
		
		
		
		/* =============  初始化课程章节数据  =========== */
		
		Chapter c = new Chapter();
		c.setCourse(courseDao.getMiniModelById("Course", (long)1));
		String c_str[] = {"C语言程序设计预备知识","C语言程序设计基础","数据类型与表达式","顺序程序设计",
				"选择结构程序设计","循环结构程序设计","数组","函数","指针","预处理命令",
				"复杂数据类型","文件"};
		for(int i = 0; i<c_str.length; i++){
			c.setOrders(i+1);
			c.setName(c_str[i]);
			c.setDate(new Date());
			
			chapterDao.save(c);
		}
		
		
		
		/* =============  初始化教师数据  =========== */
		
		Teacher teacher = new Teacher();
		for(int i = 0 ;i<3 ; i++){
			teacher.setSchool("华东理工大学");
			teacher.setName("老师"+i);
			teacher.setGender("男");
			teacher.setIs_teacher(true);
			teacher.setId_number("000"+i);
			teacher.setPassword("1");
			teacher.setCollege("信息学院");
			teacher.setTitle("讲师");
			teacher.setLast_login(new Date());
			teacher.setCourse_own(course);
			
			teacherDao.save(teacher);
		}
		
		/* =============  初始化好友关系数据  =========== */
		
		Friends friends = new Friends();
		Friends friends_new = new Friends();
		User owner = userDao.getMiniModelById("User", (long)1);
		FriendsGroup friendsGroup = new FriendsGroup();
		friendsGroup.setGroup_name("同学");
		friendsGroup.setUser_own(owner);
		
		friendsGroupDao.save(friendsGroup);
		for(int i=2;i<32;i++){
			User friend = userDao.getMiniModelById("User", (long)i);
			
			friends.setOwner(owner);
			friends.setFriends(friend);
			friends.setDate(new Date());
			friends.setIs_Link(true);
			friends.setFriendsgroup_own(friendsGroup);
			
			friends_new.setOwner(friend);
			friends_new.setFriends(owner);
			friends_new.setDate(new Date());
			friends_new.setIs_Link(true);
			
			friendsDao.save(friends);
			friendsDao.save(friends_new);
		}
		friendsGroup.setGroup_name("老师");
		friendsGroupDao.save(friendsGroup);
		for(int i=32;i<35;i++){
			User friend = userDao.getMiniModelById("User", (long)i);
			
			friends.setOwner(owner);
			friends.setFriends(friend);
			friends.setDate(new Date());
			friends.setIs_Link(true);
			friends.setFriendsgroup_own(friendsGroup);
			
			friends_new.setOwner(friend);
			friends_new.setFriends(owner);
			friends_new.setDate(new Date());
			friends_new.setIs_Link(true);
			
			friendsDao.save(friends);
			friendsDao.save(friends_new);
		}
		
		/* =============  初始化学习大厅数据  =========== */
		Society society = new Society();
		society.setName("C语言教学班");
		society.setCourse_own(course);
		society.setSociety_type(1);
		society.setCreatedate(new Date());
		society.setSociety_info("Java中HttpSession的invalidate()的方法是结束session 此方法是结束session中的全部值");
		societyDao.save(society);
		
		User user_c = userDao.getMiniModelById("User", (long)1);
		User user_t = userDao.getMiniModelById("User", (long)31);
		SocietyMember member = new SocietyMember();
		
		member.setLink(true);
		member.setSociety_own(society);
		
		member.setUser_own(user_c);
		member.setRole(2);
		societyMemberDao.save(member);//保存到厅主
		
		member.setRole(3);
		member.setUser_own(user_t);
		societyMemberDao.save(member);//保存到指导教师
		
		SocietyNotice notice = new SocietyNotice();
		for(int i = 0 ; i<8; i++){
			notice.setContent("该方法用主要用于注销调用该方法 会清空所有已定义的sessio而不是清空全部session的值也就是说 定义了一个名为后使用");
			notice.setCreatedate(new Date());
			notice.setWriter_name("刘柏众");
			notice.setWriter_type(1);
			notice.setSociety_own(society);
			
			societyNoticeDao.save(notice);
		}
		
		
		/* =============  初始化学习动态数据  =========== */
		Activity activity = new Activity();
		for( int i = 0; i<10; i++){
			activity.setA_type("m");
			activity.setCourse_own(course);
			activity.setDate(new Date());
			activity.setText("完成了《练习"+i+"》，并取得了80分的成绩！");
			activity.setUser_own(userDao.getMiniModelById("User", (long)2));
			
			activityDao.save(activity);
		}
	}
	
	
		
	
}
