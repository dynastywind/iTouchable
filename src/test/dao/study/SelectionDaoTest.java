package test.dao.study;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.study.SelectionDao;
import com.ecust.model.study.Option;
import com.ecust.model.study.Selection;

public class SelectionDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	SelectionDao selectDao = (SelectionDao)fc.getBean("selectionDao");
	
	@Test
	public void testSave() {
		Selection select = new Selection();
		select.setDate(new Date());
		Option opt = new Option();
		
		opt.setContent("111");
		
		List<Option> opt_list = new ArrayList<Option>();
		opt_list.add(opt);
		
		select.setOptions(opt_list);
		
		selectDao.save(select);
	}
	
	
}
