package com.ecust.action.admin.ajax;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecust.action.common.BaseAction;
import com.ecust.dao.study.ChapterDao;
import com.ecust.dao.study.ConceptDao;
import com.ecust.dao.study.FillDao;
import com.ecust.dao.study.ProgramDao;
import com.ecust.dao.study.SelectionDao;
import com.ecust.model.study.ConceptQuestion;
import com.ecust.model.study.Fill;
import com.ecust.model.study.Option;
import com.ecust.model.study.Program;
import com.ecust.model.study.Selection;

/**
 * 添加章节
 * @author lbz
 *
 */
@SuppressWarnings("serial")
@Namespace("/admin")
@ParentPackage("json-default")
@Results({@Result(type="json")})
public class AddProblemAction extends BaseAction{
	
	@Autowired
	private ChapterDao chapterDao;
	
	@Autowired
	private ConceptDao conceptDao;
	
	@Autowired
	private FillDao fillDao;
	
	@Autowired
	private SelectionDao selectDao;
	
	@Autowired
	private ProgramDao progDao;
	
	private long chapter_id;
	
	private String content;
	
	private String tip;
	
	private short hard;	//难度
	
	private short types;	//类型：用于练习还是考试
	
	private short sub_type;
	
	private String key;	//答案
	
	private String know;	//知识点
	
	private String option;	//选择题选项
	
	private String option_weight;	//选项的权重
	
	private Character s_key;	//选择题答案
	
	private String p_key;	//编程题答案
	
	private String c_weight;	//知识点权重
	
	private String mainToUser;
	
	private String mainToTest;
	
	private String other;
	
	private String keyToTest;
	
	@Action("addProblem")
	public String execute() throws Exception {
		
		if(sub_type == 1){	//选择题
			Selection select = new Selection();
			List<Option> option_list = new ArrayList<Option>();
			try { //防止知识点为空
				String know_str[] = know.split(",");
				String c_weight_str[] = c_weight.split(",");
				for(int i = 0; i<know_str.length; i++){
					ConceptQuestion cq = new ConceptQuestion();
					cq.setQuestion(select);
					cq.setConcept(conceptDao.getMiniModelById("Concept", Long.parseLong(know_str[i])));
					cq.setWeight(Float.parseFloat(c_weight_str[i]));
					select.getConcept_qs().add(cq);
				}
			} catch (Exception e) {
			}
			try { //防止选项为空
				String option_str[] = option.split(",");
				String weight_str[] = option_weight.split(",");
				Character label = ' ';
				for(int i = 0; i<option_str.length; i++){	//选择题选项
					switch(i){
					case 0:
						label = 'A'; break;
					case 1:
						label = 'B'; break;
					case 2:
						label = 'C'; break;
					case 3:
						label = 'D'; 
					}
					Option opt = new Option();
					opt.setLabel(label);
					opt.setContent(option_str[i]);
					opt.setWeight(Float.parseFloat(weight_str[i]));
					
					option_list.add(opt);
				}
			} catch (Exception e) {
			}
			
			select.setChapter(chapterDao.getMiniModelById("Chapter", chapter_id));
			select.setContent(content);
			select.setCourse(chapterDao.getCourseByChapter(chapter_id));
			select.setDifficulty(hard);
			select.setExplanation(tip);
			select.setType(types);
			select.setKey(s_key);
			select.setSub_type(sub_type);
			select.setDate(new Date());
			select.setOptions(option_list);
			
			selectDao.save(select);
			
		}else if(sub_type == 2){	//填空题
			List<String> key_list = new ArrayList<String>();
			Fill fill = new Fill();
			try { //防止答案为空
				String key_str[] = key.split(",");
				for(int i = 0; i<key_str.length; i++){
					key_list.add(key_str[i]);
				}
			} catch (Exception e) {
			}
			try { //防止知识点为空
				String know_str[] = know.split(",");
				String c_weight_str[] = c_weight.split(",");
				for(int i = 0; i<know_str.length; i++){
					ConceptQuestion cq = new ConceptQuestion();
					cq.setQuestion(fill);
					cq.setConcept(conceptDao.getMiniModelById("Concept", Long.parseLong(know_str[i])));
					cq.setWeight(Float.parseFloat(c_weight_str[i]));
					fill.getConcept_qs().add(cq);
				}
			} catch (Exception e) {
			}
			
			fill.setChapter(chapterDao.getMiniModelById("Chapter", chapter_id));
			fill.setContent(content);
			fill.setCourse(chapterDao.getCourseByChapter(chapter_id));
			fill.setDifficulty(hard);
			fill.setExplanation(tip);
			fill.setKey(key_list);
			fill.setType(types);
			fill.setSub_type(sub_type);
			fill.setDate(new Date());
			fillDao.save(fill);
		}else if(sub_type == 3){	//编程题
			Program prog = new Program();
			try { //防止知识点为空
				String know_str[] = know.split(",");
				String c_weight_str[] = c_weight.split(",");
				for(int i = 0; i<know_str.length; i++){
					ConceptQuestion cq = new ConceptQuestion();
					cq.setQuestion(prog);
					cq.setConcept(conceptDao.getMiniModelById("Concept", Long.parseLong(know_str[i])));
					cq.setWeight(Float.parseFloat(c_weight_str[i]));
					prog.getConcept_qs().add(cq);
				}
			} catch (Exception e) {
			}
			prog.setChapter(chapterDao.getMiniModelById("Chapter", chapter_id));
			prog.setContent(content);
			prog.setCourse(chapterDao.getCourseByChapter(chapter_id));
			prog.setDifficulty(hard);
			prog.setExplanation(tip);
			prog.setKey(p_key);
			System.out.println("===================================:" + p_key);
			prog.setType(types);
			prog.setSub_type(sub_type);
			prog.setDate(new Date());
			
			prog.setKeyToTest(keyToTest);
			System.out.println("===========================1"+keyToTest);
			prog.setMainToTest(mainToTest);
			prog.setMainToUser(mainToUser);
			prog.setOther(other);
			
			progDao.save(prog);
		}
		
		return SUCCESS;
		
	}

	public long getChapter_id() {
		return chapter_id;
	}

	public void setChapter_id(long chapter_id) {
		this.chapter_id = chapter_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public short getHard() {
		return hard;
	}

	public void setHard(short hard) {
		this.hard = hard;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKnow() {
		return know;
	}

	public void setKnow(String know) {
		this.know = know;
	}

	public short getTypes() {
		return types;
	}

	public void setTypes(short types) {
		this.types = types;
	}

	public short getSub_type() {
		return sub_type;
	}

	public void setSub_type(short sub_type) {
		this.sub_type = sub_type;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Character getS_key() {
		return s_key;
	}

	public void setS_key(Character s_key) {
		this.s_key = s_key;
	}

	public String getP_key() {
		return p_key;
	}

	public void setP_key(String p_key) {
		this.p_key = p_key;
	}

	public String getOption_weight() {
		return option_weight;
	}

	public void setOption_weight(String option_weight) {
		this.option_weight = option_weight;
	}

	public String getC_weight() {
		return c_weight;
	}

	public void setC_weight(String c_weight) {
		this.c_weight = c_weight;
	}

	public String getMainToUser() {
		return mainToUser;
	}

	public void setMainToUser(String mainToUser) {
		this.mainToUser = mainToUser;
	}

	public String getMainToTest() {
		return mainToTest;
	}

	public void setMainToTest(String mainToTest) {
		this.mainToTest = mainToTest;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getKeyToTest() {
		return keyToTest;
	}

	public void setKeyToTest(String keyToTest) {
		this.keyToTest = keyToTest;
	}

	
}
