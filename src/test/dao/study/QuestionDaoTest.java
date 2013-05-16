package test.dao.study;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecust.dao.study.ConceptDao;
import com.ecust.dao.study.ConceptQuestionDao;
import com.ecust.dao.study.FillDao;
import com.ecust.dao.study.QuestionDao;
import com.ecust.model.study.ConceptQuestion;
import com.ecust.model.study.Fill;

public class QuestionDaoTest {
	
	public static BeanFactory fc = new ClassPathXmlApplicationContext("com/applicationContext.xml");
	QuestionDao questionDao = (QuestionDao)fc.getBean("questionDao");
	FillDao fillDao = (FillDao)fc.getBean("fillDao");
	ConceptDao conceptDao = (ConceptDao)fc.getBean("conceptDao");
	ConceptQuestionDao cqDao = (ConceptQuestionDao)fc.getBean("conceptQuestionDao");
	
	
	@Test
	public void testSave() {
		Fill fill = new Fill();
		
		Set<ConceptQuestion> cq_set = new HashSet<ConceptQuestion>();
		for(int i = 0; i<5; i++){
			ConceptQuestion cq = new ConceptQuestion();
			cq.setWeight(i);
			cq_set.add(cq);
		}
		
		fill.setConcept_qs(cq_set);
		fill.setDate(new Date());
		
		Iterator<ConceptQuestion> iter = cq_set.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().getWeight());
		}
		
		//fillDao.save(fill);
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
