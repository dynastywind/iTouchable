package com.ecust.service.study.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.Time;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.XPP3Reader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ecust.dao.study.ChapterDao;
import com.ecust.dao.study.ConceptDao;
import com.ecust.dao.study.CourseStudentDao;
import com.ecust.dao.study.FillDao;
import com.ecust.dao.study.ProgramDao;
import com.ecust.dao.study.SelectionDao;
import com.ecust.dao.study.TemporaryTestDao;
import com.ecust.dao.study.TestDao;
import com.ecust.dao.zone.StudentDao;
import com.ecust.model.study.Chapter;
import com.ecust.model.study.Concept;
import com.ecust.model.study.ConceptQuestion;
import com.ecust.model.study.CourseStudent;
import com.ecust.model.study.Fill;
import com.ecust.model.study.Master;
import com.ecust.model.study.Option;
import com.ecust.model.study.Program;
import com.ecust.model.study.Question;
import com.ecust.model.study.Selection;
import com.ecust.model.study.TemporaryTest;
import com.ecust.model.study.Test;
import com.ecust.model.zone.Student;
import com.ecust.service.study.StudentServices;
import com.ecust.util.ConceptReport;
import com.ecust.util.GenerationChoice;
import com.ecust.util.GenerationRequest;
import com.ecust.util.TestReport;

@Scope("singleton")
@Repository("studentServices")
public class StudentServicesImpl implements StudentServices{
	public static final int AUTO_SELECTION=3;//initiallized selection questions' quantity
	public static final int AUTO_FILL=1;//initiallized fill questions' number quantity
	public static final int AUTO_PROGRAM=1;//initiallized program questions' quantity
	public static final String PATH="D:\\";//path to save temporary test
	public static final int BASE_POINT=10;//basic point to be added to exp
	public static final int TEST_SUM=1;//total tests in database
	public static final String PROGRAM_ADDRESS="E:\\";//path to store program questions
	public static final String TEST_ADDRESS="E:\\";//path to run program questions
	private StudentDao studentDao;
	private CourseStudentDao courseStudentDao;
	private ChapterDao chapterDao;
	private ConceptDao conceptDao;
	private TestDao testDao;
	private SelectionDao selectionDao;
	private FillDao fillDao;
	private ProgramDao programDao;
	private TemporaryTestDao temporaryTestDao;
	
	/**
	 * 设置学生DAO实例
	 * @param studentDao 注入的学生DAO实例
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	/**
	 * 设置课程-学生DAO实例
	 * @param courseStudentDao 注入的课程-学生DAO实例
	 */
	public void setCourseStudentDao(CourseStudentDao courseStudentDao) {
		this.courseStudentDao = courseStudentDao;
	}

	/**
	 * 设置章节DAO实例
	 * @param conceptDao 注入的章节DAO实例
	 */
	public void setChapterDao(ChapterDao chapterDao) {
		this.chapterDao = chapterDao;
	}
	
	/**
	 * 设置概念-课程DAO实例
	 * @param conceptQuestionDao 注入的概念-课程DAO实例
	 */
	public void setConceptDao(ConceptDao conceptDao) {
		this.conceptDao = conceptDao;
	}
	
	/**
	 * 设置标准测试DAO实例
	 * @param testDao 注入的标准测试DAO实例
	 */
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
	
	/**
	 * 设置选择题DAO实例
	 * @param selectionDao 选择题DAO实例
	 */
	public void setSelectionDao(SelectionDao selectionDao) {
		this.selectionDao = selectionDao;
	}

	/**
	 * 设置填空题DAO实例
	 * @param fillDao 填空题DAO实例
	 */
	public void setFillDao(FillDao fillDao) {
		this.fillDao = fillDao;
	}

	/**
	 * 设置编程题DAO实例
	 * @param programDao 编程题DAO实例
	 */
	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
	}

	/**
	 * 设置临时测试DAO实例
	 * @param temporaryTestDao 注入的临时测试DAO实例
	 */
	public void setTemporaryTestDao(TemporaryTestDao temporaryTestDao) {
		this.temporaryTestDao = temporaryTestDao;
	}
	
	@Override
	public String getTest(String courseNo){//generate standard test
		int no=(int)(Math.random()*TEST_SUM)+1;
		NumberFormat format=NumberFormat.getIntegerInstance();
		format.setMinimumIntegerDigits(2);
		return testDao.get(courseNo+format.format(no)).getTestAddress();
	}
	
	@Override
	public void checkBeforeLearn(String id) throws IOException{//generate check test for recently learned chapter
		Student s=studentDao.getStudent(id);
		getTestSelection(s.getId_number(),s.getRecentLearn(),GenerationChoice.TEST);
	}
	
	@Override
	public void enhancePractice(String id, Chapter chapter) throws IOException{//id=courseNo+chapter's order
		Student s=studentDao.getStudent(id);
		getTestSelection(s.getId_number(),chapter,GenerationChoice.PRACTICE);
	}
	
	@Override
	public void addChapterLearned(long id, long chapterId){//add a latest learned chapter into database
		Student s=studentDao.get(id);
		s.getChapters().add(chapterDao.get(chapterId));
		updateInformation(s);
	}
	
	@Override
	public void smartGeneration(GenerationRequest gr) throws IOException{
		Student s=studentDao.getStudent(gr.getId());
		DocumentFactory factory=new DocumentFactory();
		Document document=factory.createDocument();
		Element root=document.addElement("test");
		root.addAttribute("id", ""+gr.getId());
		Element[] element=new Element[3];
		element[0]=root.addElement("selection");
		element[0].addElement("description").setText("选择题");
		element[1]=root.addElement("fill");
		element[1].addElement("description").setText("填空题");
		element[2]=root.addElement("program");
		element[2].addElement("description").setText("编程题");
		if(gr.getChoice().equals(GenerationChoice.AUTO)){
			getQuestions(s, element, gr.getCourseNo(), (short)0, AUTO_SELECTION, AUTO_FILL, AUTO_PROGRAM);
		}else{
			getQuestions(s, element, gr.getCourseNo(), gr.getChapter(), gr.getSelection(), gr.getFill(), gr.getProgram());
		}
		output(""+gr.getId(),document);
	}
	
	@Override
	public void getTestSelection(String studentId, Chapter chapter, GenerationChoice choice) throws IOException{//get selections for practice or test
		DocumentFactory factory=new DocumentFactory();
		Document doc=factory.createDocument();
		Element root=doc.addElement("test");
		root.addAttribute("id", studentId);
		Element e=root.addElement("selection");
		Set<String> question=new HashSet<String>();
		for(Iterator<Concept> i=chapter.getConcepts().iterator();i.hasNext();){
			Set<ConceptQuestion> set=i.next().getConcept_q();//根据章节拥有的概念获得概念问题对象集合
			int counter=0;
			for(Iterator<ConceptQuestion> index=set.iterator();index.hasNext();){//从中抽取三个对象
				if(counter>3){
					break;
				}
				String qId=""+index.next().getQuestion().getId();
				if(choice==GenerationChoice.TEST){//如果是学前测试，难度设定为2~3
					if(qId.substring(7,8).equals("1")&&(qId.substring(6,7).equals("2")||qId.substring(6,7).equals("3"))){
						question.add(qId);
						counter++;
					}
				}
				else if(choice==GenerationChoice.PRACTICE){//如果是学后巩固练习，难度设定为1~2
					if(qId.substring(7,8).equals("1")&&(qId.substring(6,7).equals("1")||qId.substring(6,7).equals("2"))){
						question.add(qId);
						counter++;
					}
				}
			}
		}
		getSelection(question.toArray(new String[question.size()]),e);
		output(studentId,doc);//输出文件名为学生ID号
	}
	
	/**
	 * 输出XML文件方法
	 * @param name 文件名（没有.xml后缀）
	 * @param document XML文档对象
	 * @throws IOException 输出试题XML文件时可能抛出的异常
	 */
	public void output(String name, Document document) throws IOException{
		OutputFormat format=new OutputFormat(" ",true,"UTF-8");
		FileWriter fw=new FileWriter(PATH+name+".xml");
		XMLWriter writer=new XMLWriter(fw,format);
		writer.write(document);
		writer.close();
		fw.close();
		temporaryTestDao.save(new TemporaryTest(name,PATH+name+".xml"));
	}
	
	/**
	 * 获取智能测试中的选择题的方法
	 * @param id 要抽取的选择题的ID号数组
	 * @param selection 选择题节点
	 */
	public void getSelection(String[] id, Element selection){
		for(int i=0;i<id.length;i++){
			Selection question=selectionDao.get(Long.parseLong(id[i]));
			Element q=selection.addElement("question-body");
			addAttribute(q,question,3);
			q.setText(question.getContent());
			List<Option> list=question.getOptions();
			for(Iterator<Option> it=list.iterator();it.hasNext();){
				Option o=it.next();
				q.addElement("option").setText(o.getLabel()+o.getContent());
			}
			q.addElement("key").addElement("value").setText(""+question.getKey());
			q.addElement("explanation").setText(question.getExplanation());
		}
	}
	
	/**
	 * 获得智能测试中的填空题的方法
	 * @param id 要抽取的填空题的ID号数组
	 * @param fill 填空题节点
	 */
	public void getFill(String[] id, Element fill){
		for(int i=0;i<id.length;i++){
			Fill question=fillDao.get(Long.parseLong(id[i]));
			Element q=fill.addElement("question-body");
			addAttribute(q,question,2);
			q.setText(question.getContent());
			List<String> list=question.getKey();
			int j=0;
			for(Iterator<String> it=list.iterator();it.hasNext();){
				Element key=q.addElement("key");
				key.addAttribute("blank", ""+(++j));
				String[] s=it.next().split(",");
				for(int k=0;k<s.length;k++){
					key.addElement("value").setText(s[k]);
				}
			}
			q.addElement("explanation").setText(question.getExplanation());
		}
	}
	
	/**
	 * 获得智能测试中的编程题的方法
	 * @param id 要抽取的编程题的ID号数组
	 * @param program 编程题节点
	 */
	public void getProgram(String[] id, Element program){
		for(int i=0;i<id.length;i++){
			Program question=programDao.get(Long.parseLong(id[i]));
			Element q=program.addElement("question-body");
			addAttribute(q,question,10);
			q.setText(question.getContent());
			q.addElement("other").setText(question.getOther());
			q.addElement("mainToUser").setText(question.getMainToUser());
			q.addElement("mainToTest").setText(question.getMainToTest());
			q.addElement("key").addElement("value").setText(question.getKey());
			q.addElement("explanation").setText(question.getExplanation());
		}
	}
	
	/**
	 * 增加节点属性的方法
	 * @param q 问题节点
	 * @param question 问题实例
	 * @param points 问题分值
	 */
	public void addAttribute(Element q, Question question, int points){//add attributes
		q.addAttribute("id", ""+question.getId());
		q.addAttribute("chapter", ""+question.getChapter().getOrders());
		q.addAttribute("type", ""+question.getSub_type());
		q.addAttribute("difficulty", ""+question.getDifficulty());
		q.addAttribute("points", ""+points);
	}
	
	public void deleteTemporaryTest(String name){
		temporaryTestDao.delete(name);
		File f=new File(PATH+name+".xml");
		if(f.exists()){
			f.delete();
		}
	}
	
	/**
	 * 根据哈希映射的值进行排序的方法，获得按照掌握度从低到高排序的链表对象
	 * @param map 需要排序的映射
	 * @return 排序完成的链表
	 */
	public List<Map.Entry<String,Float>> sort(Map<String,Float> map){//sort according to Map's value
		ArrayList<Map.Entry<String,Float>> list = new ArrayList<Map.Entry<String,Float>>(map.entrySet());
		Collections.sort(list,new Comparator<Map.Entry<String,Float>>(){
			public int compare(Entry<String,Float> arg0,
						Entry<String,Float> arg1) {
					return (int)(arg0.getValue()-arg1.getValue());
			}
		});
		return list;
	}
	
	/**
	 * 格式化章节号的方法
	 * @param chapter 短整数形式的章节号
	 * @return 规定格式的字符串形式的章节号
	 */
	public String formatChapter(short chapter){
		if(chapter==(short)0){
			return "";
		}else{
			NumberFormat format=NumberFormat.getNumberInstance();
			format.setMaximumIntegerDigits(2);
			return format.format(chapter);
		}
	}
	
	/**
	 * 获取智能测试题的方法，由smartGeneration调用
	 * @param s 学生实例
	 * @param element XML文档节点数组
	 * @param courseNo 课程号
	 * @param chapter 章节号（0表示跨章节选题）
	 * @param seleSum 选择题数目
	 * @param fillSum 填空题数目
	 * @param progSum 编程题数目
	 */
	public void getQuestions(Student s, Element[] element, String courseNo, short chapter, int seleSum, int fillSum, int progSum){
		Map<String,Float> lower=new HashMap<String,Float>();
		Map<String,Float> higer=new HashMap<String,Float>();
		Map<String,GenerationChoice> difficulty=new HashMap<String,GenerationChoice>();
		Set<Master> master=s.getMaster();
		for(Iterator<Master> it=master.iterator();it.hasNext();){
			Master m=it.next();
			Concept c=m.getConcept();
			String conceptName=c.getName();
			float user=m.getMaster();//学生掌握度
			float all=c.getMaster();//平均掌握度
			if(user<=all){//如果学生掌握度不大于平均掌握度
				lower.put(conceptName,user);//该概念加入低级map中
				if((all-user)/all>0.5){//如果低于平均掌握度50%以上
					difficulty.put(conceptName,GenerationChoice.JUNIOR);//难度设为简单
				}else if((all-user)/all>0.2){//如果低于平均掌握度20%以上
					difficulty.put(conceptName,GenerationChoice.SENIOR);//难度设为中等
				}else{
					difficulty.put(conceptName,GenerationChoice.HARD);//难度设为高级
				}
			}else{
				higer.put(conceptName,user);
				difficulty.put(conceptName,GenerationChoice.HARD);
			}
		}
		
		if(lower.size()<3){//if less than 3 concepts, add the lowest ones in higher map
			List<Map.Entry<String,Float>> sortList2=sort(higer);
			for(int i=0;i<3-lower.size();i++){
				Entry<String,Float> e=sortList2.get(i);
				lower.put(e.getKey(), e.getValue());
			}
		}//至此，lower中至少有三个概念，且按照掌握度自小到大排列
		
		Chapter recentChapter=s.getRecentLearn();
		if(recentChapter!=null){
			Set<Concept> concepts=recentChapter.getConcepts();//concepts in recently learned chapter
			for(Iterator<Concept> it=concepts.iterator();it.hasNext();){
				Concept c=it.next();
				String key=c.getName();
				if(!lower.containsKey(key)){
					for(Iterator<Master> i=s.getMaster().iterator();i.hasNext();){
						Master m=i.next();
						if(m.getConcept().getName().equals(key)){
							lower.put(key, m.getMaster());
						}
					}
				}
			}//至此加入了最近学习的一章中的概念，如果该概念与lower中重复，则略过
		}
		
		for(Iterator<String> i=lower.keySet().iterator();i.hasNext();){//开始根据概念选题
			String[] sele=new String[seleSum];
			String[] fill=new String[fillSum];
			String[] prog=new String[progSum];
			int c=0,f=0,p=0;
			int diff=1;
			String key=i.next();
			Set<ConceptQuestion> conceptSet=conceptDao.getConceptByName(key).get(0).getConcept_q();
			switch(difficulty.get(key)){//匹配难度
			case JUNIOR:
				diff=1;break;
			case SENIOR:
				diff=2;break;
			case HARD:
				diff=3;break;
			default:
				break;
			}
			for(Iterator<ConceptQuestion> it=conceptSet.iterator();it.hasNext();){
				String id=""+it.next().getQuestion().getId();
				if(id.substring(6, 7).equals(""+diff)){
					if(id.substring(7,8).equals("1")&&c!=seleSum){//是选择题且选择题未选满
						sele[c++]=id;
					}
					else if(id.substring(7,8).equals("2")&&f!=fillSum){//是判断题且判断题未选满
						fill[f++]=id;
					}
					else if(id.substring(7,8).equals("3")&&p!=progSum){//是程序题且程序题未选满
						prog[p++]=id;
					}
				}
				if(c==seleSum&&f==fillSum&&p==progSum){//已经选完题目，此时每个知识点有相应数量的不同类型的题目
					break;
				}
			}
			getSelection(sele,element[0]);
			getFill(fill,element[1]);
			getProgram(prog,element[2]);
		}
	}
	
	public void updateSelection(CourseStudent cs, Iterator<Element> userKey, Iterator<Element> standKey, Student s, int exp, Map<String, ConceptReport> concepts){
		/*
		 * userKey stands for an iteration of questions from the report.xml
		 * standKey stands for an iteration of standard keys' values from the temporaryTest's XML file
		 */
		for(;userKey.hasNext();){
			Element body=userKey.next();
			Element stand=standKey.next();
			String id=body.attributeValue("id");//question's id
			Selection q=selectionDao.get(Long.parseLong(id));
			Set<ConceptQuestion> set=q.getConcept_qs();
			String userAns=body.element("key").element("value").getTextTrim();//student's answer
			System.out.println(q.getOptions().get(0).getContent());
			if(stand.element("key").element("value").getTextTrim().equalsIgnoreCase(userAns)){
				q.setRight_count(q.getRight_count()+1);
				cs.setRights(cs.getRights()+1);
				exp+=BASE_POINT*Integer.parseInt(body.attributeValue("difficulty"));
			}else{
				q.setError_count(q.getError_count()+1);
				cs.setWrongs(cs.getWrongs()+1);
				exp+=BASE_POINT/2;
			}
			for(Iterator<ConceptQuestion> i=set.iterator();i.hasNext();){//concepts in this question
				ConceptQuestion cq=i.next();
				String name=cq.getConcept().getName();
				if(!concepts.keySet().contains(name)){
					ConceptReport cr=new ConceptReport(name);
					concepts.put(name,cr);
				}
				for(Iterator<Option> op=q.getOptions().iterator();op.hasNext();){
					Option o=op.next();
					if((""+o.getLabel()).equalsIgnoreCase(userAns)){
						ConceptReport r=concepts.get(name);
						r.getWhole().add(o.getWeight());//option's weight to reflect a master value
						r.getWeight().add(cq.getWeight());//concept's weight in a question
						r.getConfidence().add(Float.parseFloat(body.element("confidence").getTextTrim()));
						break;
					}
				}
			}
			updateInformation(cs);
		}
		updateMaster(s,exp,concepts);
	}
	
	public boolean checkProgram(Element body, Element standBody, long stuId) throws IOException{
		String id=body.attributeValue("id");//program-id
		String testKey=standBody.element("testKey").element("value").getTextTrim();
		BufferedReader pReader=new BufferedReader(new FileReader(new File(PROGRAM_ADDRESS+id+".c")));
		final String testFile=TEST_ADDRESS+stuId;
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File(testFile+".c"),true));
		String pr=null;
		while((pr=pReader.readLine())!=null){
			bw.write(pr);
		}
		pReader.close();
		bw.write(body.element("key").element("value").getTextTrim());
		bw.close();
		String[] s=new String[]{"gcc","-w",testFile+".c","-o",testFile+".exe"};
		Runtime.getRuntime().exec(s);
		File f;
		int times=0;
		do{
			times++;
			f=new File(testFile+".exe");
		}while(!f.exists()||times<100);
		s=new String[]{"cmd","/c",testFile};
		BufferedReader br=new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(s).getInputStream()));
		String txt=null;
		String testKeyString="";
		while((txt=br.readLine())!=null){
			testKeyString+=txt;
		}
		br.close();
		f.delete();
		f=new File(testFile+".c");
		f.delete();
		if(testKey.equals(testKeyString.trim())){
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void calculateAndUpdateMaster(Student s, long courseId, BufferedReader reader) throws Exception{
		StringBuffer buffer=new StringBuffer();
		String line=null;
		while((line=reader.readLine())!=null){
			buffer.append(line);
		}
		String xml=buffer.toString();
		Document standardKey=new XPP3Reader().read(new File(temporaryTestDao.get(s.getId_number()).getAddress()));
		Document userKey=new XPP3Reader().read(new StringReader(xml));
		Map<String,ConceptReport> concepts=new HashMap<String,ConceptReport>();
		int exp=BASE_POINT/2;
		//here begins the check for selections
		CourseStudent cs=null;//
		for(Iterator<CourseStudent> it=s.getCourses().iterator();it.hasNext();){
			CourseStudent c=it.next();
			if(c.getCourse().getId()==courseId){
				cs=c;
				break;
			}
		}
		Iterator<Element> userIt=((List<Element>)userKey.selectNodes("//question[@type='1']")).iterator();
		Iterator<Element> standIt=((List<Element>)standardKey.selectNodes("//selection")).iterator();
		updateSelection(cs,userIt,standIt,s,exp,concepts);
		//here begins the check for fills
		userIt=((List<Element>)userKey.selectNodes("//question[@type='2']")).iterator();
		standIt=((List<Element>)standardKey.selectNodes("//fill")).iterator();
		for(;userIt.hasNext();){
			List<Boolean> rights=new LinkedList<Boolean>();
			Element body=userIt.next();
			Element standKey=standIt.next();
			String id=body.attributeValue("id");//question's id
			Fill q=fillDao.get(Long.parseLong(id));
			Set<ConceptQuestion> set=q.getConcept_qs();
			int totalBlank=0;
			Iterator<Element> uKey=((List<Element>)(body.selectNodes("./key"))).iterator();
			Iterator<Element> sKey=((List<Element>)(standKey.selectNodes("./key"))).iterator();
			for(;uKey.hasNext();){
				totalBlank++;
				String key=uKey.next().element("value").getTextTrim();
				String[] blanKeys=sKey.next().getTextTrim().split(",");
				for(String blanKey:blanKeys){
					if(key.equals(blanKey)){
						q.setRight_count(q.getRight_count()+1);
						cs.setRights(cs.getRights()+1);
						rights.add(new Boolean("true"));
						exp+=BASE_POINT*Integer.parseInt(body.attributeValue("difficulty"));
						break;
					}
				}
			}
			if(rights.size()!=totalBlank){
				q.setError_count(q.getError_count()+1);
				cs.setWrongs(cs.getWrongs()+1);
				exp+=BASE_POINT/2;
			}
			for(Iterator<ConceptQuestion> i=set.iterator();i.hasNext();){
				ConceptQuestion cq=i.next();
				String name=cq.getConcept().getName();
				if(!concepts.keySet().contains(name)){
					ConceptReport cr=new ConceptReport(name);
					concepts.put(name,cr);
				}
				ConceptReport r=concepts.get(name);
				r.getWhole().add((float)1.0/totalBlank*(rights.size()));
				r.getWeight().add(cq.getWeight());
				r.getConfidence().add(Float.parseFloat(body.element("confidence").getText()));
			}
			updateInformation(cs);
		}
		//here begins the check for programs
		userIt=((List<Element>)userKey.selectNodes("//question[@type='3']")).iterator();
		standIt=((List<Element>)standardKey.selectNodes("//program/question-body")).iterator();
		for(;userIt.hasNext();){
			Element body=userIt.next();
			Element standBody=standIt.next();
			String id=body.attributeValue("id");
			Program q=programDao.get(Long.parseLong(id));
			Set<ConceptQuestion> set=q.getConcept_qs();
			boolean isCorrect=checkProgram(body,standBody,s.getId());
			if(isCorrect){
				q.setRight_count(q.getRight_count()+1);
				cs.setRights(cs.getRights()+1);
				exp+=BASE_POINT*Integer.parseInt(body.attributeValue("difficulty"));
			}else{
				q.setError_count(q.getError_count()+1);
				cs.setWrongs(cs.getWrongs()+1);
				exp+=BASE_POINT/2;
			}
			for(Iterator<ConceptQuestion> i=set.iterator();i.hasNext();){
				ConceptQuestion cq=i.next();
				String name=cq.getConcept().getName();
				if(!concepts.keySet().contains(name)){
					ConceptReport cr=new ConceptReport(name);
					concepts.put(name,cr);
				}
				ConceptReport r=concepts.get(name);
				if(isCorrect){
					r.getWhole().add(1.0f);
				}else{
					r.getWhole().add(0.0f);
				}
				r.getWeight().add(cq.getWeight());
				r.getConfidence().add(Float.parseFloat(body.element("confidence").getText()));
			}
		}
		//update information changed
		updateMaster(s,exp,concepts);
	}
	
	@SuppressWarnings("unchecked")
	public void checkPractice(String id, long courseId, BufferedReader reader) throws Exception{
		Student s=studentDao.getStudent(id);
		StringBuffer buffer=new StringBuffer();
		String line=null;
		while((line=reader.readLine())!=null){
			buffer.append(line);
		}
		String xml=buffer.toString();
		Document userKey=new XPP3Reader().read(new StringReader(xml));
		Element userRoot=userKey.getRootElement();
		Document standardKey=new XPP3Reader().read(new File(temporaryTestDao.get(userRoot.attributeValue("id")).getAddress()));
		Map<String,ConceptReport> concepts=new HashMap<String,ConceptReport>();
		int exp=BASE_POINT/2;
		Iterator<Element> userIt=((List<Element>)userKey.selectNodes("//question-body[@type=1]")).iterator();
		Iterator<Element> standIt=((List<Element>)standardKey.selectNodes("//question-body")).iterator();
		CourseStudent cs=null;//
		for(Iterator<CourseStudent> it=s.getCourses().iterator();it.hasNext();){
			CourseStudent c=it.next();
			if(c.getCourse().getId()==courseId){
				cs=c;
				break;
			}
		}
		updateSelection(cs,userIt,standIt,s,exp,concepts);
	}
	
	/**
	 * 更新概念掌握度的方法
	 * @param s 学生实例
	 * @param exp 经验值
	 * @param concepts 概念哈希映射对象，概念名为键，相应的概念报告对象为值
	 */
	public void updateMaster(Student s, int exp, Map<String, ConceptReport> concepts){
		s.setExp(s.getExp()+exp);//update exp
		for(Iterator<String> it=concepts.keySet().iterator();it.hasNext();){//one concept's master
			String name=it.next();
			ConceptReport cr=concepts.get(name);
			List<Float> whole=cr.getWhole();
			List<Float> weight=cr.getWeight();
			List<Float> confidence=cr.getConfidence();
			float sumOfWeight=0.0f;
			float confi=0.0f;
			for(int i=0;i<weight.size();i++){
				sumOfWeight+=weight.get(i);
				confi+=confidence.get(i);
			}
			confi/=confidence.size();
			for(int i=0;i<weight.size();i++){
				weight.set(i, weight.get(i)/sumOfWeight);
				whole.set(i, whole.get(i)*weight.get(i));
			}
			for(int i=1;i<whole.size();i++){
				float x=whole.get(0),y=whole.get(i);
				if(x>=0&&y>=0){
					whole.set(0, x+y*(1-x));
				}
				else if(x<0&&y<0){
					whole.set(0, x+y*(1+x));
				}
				else{
					whole.set(0, (x+y)/(1-Math.min(Math.abs(x), Math.abs(y))));
				}
			}
			for(Iterator<Master> iter=s.getMaster().iterator();iter.hasNext();){//update master
				Master master=iter.next();
				Concept concept=master.getConcept();
				if(concept.getName().equals(name)){
					float result=confi*whole.get(0)+(1-confi)*master.getMaster();
					System.out.println(result);
					master.setMaster(result);//更新学生个人掌握度
					concept.setMaster((concept.getMaster()*concept.getAnswer_sum()+result)/(concept.getAnswer_sum()+1));//更新平均掌握度
					break;
				}
			}
		}
		updateInformation(s);//persist student instance
	}
	
	@SuppressWarnings("unchecked")
	public int checkTest(TestReport report) throws Exception{
		BufferedReader reader=report.getReader();
		StringBuffer buffer=new StringBuffer();
		String line=null;
		while((line=reader.readLine())!=null){
			buffer.append(line);
		}
		String xml=buffer.toString();
		Document userKey=new XPP3Reader().read(new StringReader(xml));//学生答案文档
		Document standardKey=new XPP3Reader().read(new File(testDao.getAddress(userKey.getRootElement().attributeValue("id"))));//标准答案文档
		float totalScore=0.0f;
		float sePoints=Float.parseFloat(standardKey.selectSingleNode("//selection/question-body/@points").getText());
		float fiPoints=Float.parseFloat(standardKey.selectSingleNode("//fill/question-body/@points").getText());
		float prPoints=Float.parseFloat(standardKey.selectSingleNode("//program/question-body/@points").getText());
		Iterator<Element> seKeyUser=((List<Element>)userKey.selectNodes("//question[@type='1']/key/value")).iterator();
		Iterator<Element> fiKeyUser=((List<Element>)userKey.selectNodes("//question[@type='2']")).iterator();
		Iterator<Element> prKeyUser=((List<Element>)userKey.selectNodes("//question[@type='3']")).iterator();
		Iterator<Element> seKeyStand=((List<Element>)standardKey.selectNodes("//selection/question-body/key/value")).iterator();
		Iterator<Element> fiKeyStand=((List<Element>)standardKey.selectNodes("//fill/question-body")).iterator();
		Iterator<Element> prKeyStand=((List<Element>)standardKey.selectNodes("//program/question-body")).iterator();
		for(;seKeyUser.hasNext();){
			if(seKeyUser.next().getTextTrim().equals(seKeyStand.next().getTextTrim())){
				totalScore+=sePoints;
			}
		}
		for(;fiKeyUser.hasNext();){
			int rightBlank=0,totalBlank=0;
			Iterator<Element> uKey=((List<Element>)(fiKeyUser.next().selectNodes("./key"))).iterator();
			Iterator<Element> sKey=((List<Element>)(fiKeyStand.next().selectNodes("./key"))).iterator();
			for(;uKey.hasNext();){
				totalBlank++;
				String key=uKey.next().element("value").getTextTrim();
				String[] str=sKey.next().element("value").getTextTrim().split(",");
				for(String s:str){
					if(key.equals(s)){
						rightBlank++;
						break;
					}
				}
			}
			totalScore+=(fiPoints*(rightBlank/totalBlank));
		}
		for(;prKeyUser.hasNext();){
			if(checkProgram(prKeyUser.next(),prKeyStand.next(),report.getStudent().getId())){
				totalScore+=prPoints;
			}
		}
		Test t=testDao.get(userKey.getRootElement().attributeValue("id"));
		int times=t.getDoneTimes();
		t.setAverageScore((t.getAverageScore()*times+totalScore)/(times+1));
		t.setDoneTimes(times+1);
		t.setTime(new Time((t.getTime().getTime()*times+report.getTime().getTime())/(times+1)));
		testDao.save(t);
		CourseStudent cs=null;//
		for(Iterator<CourseStudent> it=report.getStudent().getCourses().iterator();it.hasNext();){
			CourseStudent c=it.next();
			if(c.getCourse().getId()==report.getCourseId()){
				cs=c;
				break;
			}
		}
		int stuTimes=cs.getTimesToSolve();
		cs.setTimeInSolving(new Time((cs.getTimeInSolving().getTime()*stuTimes+report.getTime().getTime())/(stuTimes+1)));
		cs.setTimesToSolve(stuTimes+1);
		updateInformation(cs);
		return Math.round(totalScore);
	}

	@Override
	public void updateInformation(CourseStudent courseStudent) {
		// TODO Auto-generated method stub
		courseStudentDao.update(courseStudent);
	}

	@Override
	public void updateInformation(Student student) {
		// TODO Auto-generated method stub
		studentDao.update(student);
	}
	
}
