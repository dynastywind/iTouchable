package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecust.service.study.StudentServices;
import com.ecust.util.GenerationChoice;
import com.ecust.util.GenerationRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/applicationContext.xml")
public class GenerationTest {
	
	GenerationRequest gr=new GenerationRequest("10103278",GenerationChoice.MAN_MADE,"1000",(short)1,1,1,1);
	
	@Autowired
	private StudentServices studentServices;
	
	
	public void test(){
		try {
			studentServices.smartGeneration(gr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void checkTest(){
		try {
			studentServices.checkPractice("10103278", 1000, new BufferedReader(new FileReader(new File("D:\\testKey.xml"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
