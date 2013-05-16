package com.ecust.service.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Element;

import com.ecust.model.study.Chapter;
import com.ecust.model.study.CourseStudent;
import com.ecust.model.zone.Student;
import com.ecust.util.ConceptReport;
import com.ecust.util.GenerationChoice;
import com.ecust.util.GenerationRequest;
import com.ecust.util.TestReport;

public interface StudentServices {
	
	/**
	 * 更新学生信息的方法
	 * @param student 要更新的学生实例
	 */
	void updateInformation(Student student);
	
	/**
	 * 更新CourseStudent信息的方法
	 * @param courseStudent 要更新的CourseStudent实例
	 */
	void updateInformation(CourseStudent courseStudent);

	/**
	 * 根据课程号获取测试题的方法
	 * @param courseNo 课程号
	 * @return 测试题的存储地址
	 */
	public String getTest(String courseNo);

	/**
	 * 学习前检测学生对前面所学章节的掌握情况的方法
	 * @param id 接受检测的学生的学号
	 * @throws IOException 可能引发的输入输出异常
	 */
	public void checkBeforeLearn(String id) throws IOException;
	
	/**
	 * 学习新章节后进行巩固练习的方法
	 * @param id 进行练习的学生的学号
	 * @param chapter 章节对象（若是学前，则为最近学的章节；若为学后，则为所在章节）
	 * @throws IOException 可能引发的输入输出异常
	 */
	public void enhancePractice(String id, Chapter chapter) throws IOException;

	/**
	 * 增加已经学过的章节号
	 * @param id 学生ID
	 * @param chapterId 章节号ID
	 */
	public void addChapterLearned(long id, long chapterId);

	/**
	 * 试题生成方法
	 * @param gr 生成请求对象包含了所有的试题参数
	 * @throws IOException 输出试题XML文件时可能抛出的异常
	 */
	public void smartGeneration(GenerationRequest gr) throws IOException;

	/**
	 * 获取章节学习前和结束后提供的选择题测试的方法（学习前的测试题单独在2~3之间，测试的是该章节之前的概念；学习后的测试题难度在1~2之间，测试的是这一章节的概念）
	 * @param studentId 学生ID号
	 * @param chapter 章节对象（若是学前，则为最近学的章节；若为学后，则为所在章节）
	 * @param choice 生成的测试题类型（学前<TEST>或者学后<PRACTICE>）
	 * @throws IOException 输出试题XML文件时可能抛出的异常
	 */
	public void getTestSelection(String studentId, Chapter chapter, GenerationChoice choice) throws IOException;

	/**
	 * 删除临时测试实例的方法
	 * @param name 没有.xml后缀的文件名（同一时间一名学生在数据库中只能有一个临时测试对象）
	 */
	public void deleteTemporaryTest(String name);

	/**
	 * 更新选择题相关属性的方法
	 * @param cs 课程-学生对象
	 * @param userKey 学生答案XML文档的迭代对象
	 * @param standKey 标准答案XML文档的迭代对象
	 * @param s 学生实例
	 * @param exp 经验值
	 * @param concepts 概念哈希映射对象，概念名为键，相应的概念报告对象为值
	 */
	public void updateSelection(CourseStudent cs, Iterator<Element> userKey, Iterator<Element> standKey, Student s, int exp, Map<String, ConceptReport> concepts);

	/**
	 * 检查编程题正确与否的方法
	 * @param body 用户答案文档问题的主体节点
	 * @param standBody 标准答案文档问题的主体节点
	 * @param stuId 学生ID号
	 * @return 题目正确与否的返回值，true表示正确，false表示错误
	 * @throws IOException 生成临时可执行程序是可能抛出的异常
	 */
	public boolean checkProgram(Element body, Element standBody, long stuId) throws IOException;

	/**
	 * 计算并更新掌握度的方法
	 * @param s 学生实例
	 * @param courseId 课程号
	 * @param reader 缓冲输入XML流
	 * @throws Exception 解析文档时可能引发的各种异常
	 */
	public void calculateAndUpdateMaster(Student s, long courseId, BufferedReader reader) throws Exception;

	/**
	 * 检查学前练习和学后练习测试题正确与否的方法
	 * @param id 学生ID号
	 * @param courseId 课程号
	 * @param reader 学生答案XML缓冲流
	 * @throws Exception 解析文档时可能引发的各种异常
	 */
	public void checkPractice(String id, long courseId, BufferedReader reader) throws Exception;

	/**
	 * 检查标准试题答题情况的方法，标准试题中的题目不列入题库中，检查结束后不更新概念掌握情况
	 * @param report 答题情况报告对象
	 * @return 最终获得的分数
	 * @throws Exception 解析文档时可能引发的各种异常
	 */
	public int checkTest(TestReport report) throws Exception;

}
